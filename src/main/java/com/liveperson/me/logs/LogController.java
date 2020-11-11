package com.liveperson.me.logs;

import com.liveperson.me.logs.exception.ResourceAlreadyExistsException;
import com.liveperson.me.logs.exception.ResourceNotFoundException;
import com.liveperson.me.logs.models.Log;
import com.liveperson.me.logs.models.LogId;
import com.liveperson.me.logs.repositories.LogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@EnableAutoConfiguration
@RequestMapping("/logs")
public class LogController {

	private final LogRepository logRepository;

	@Autowired
	LogController(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	@GetMapping
	ResponseEntity<List<Log>> getLogs() {
		List<Log> logs = logRepository.findAll();
		return ResponseEntity.ok(logs);
	}

	@PostMapping
	public ResponseEntity<Log> save(@RequestBody final String message){
		String id = UUID.randomUUID().toString();
		final Log log = Log.builder()
				.id(new LogId(id, 0))
				.message(message)
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.build();
        Log fromDb = logRepository.save(log);
        return ResponseEntity.ok(fromDb);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Log> update(@PathVariable(value = "id") final String id,
                                      @RequestParam(value = "version", required = false) final Long version,
                                      @RequestBody final String message){

        long internalVersion = version == null ? 0L : version;
        final LogId logId = new LogId(id, internalVersion);

        final Log log = logRepository
                .findById(logId)
                .orElseThrow(ResourceNotFoundException::new);

        if(log.getMessage().equals(message)){
            throw new ResourceAlreadyExistsException();
        }

        final Log updateLog = Log.builder()
                .id(new LogId(id, internalVersion + 1))
                .message(message)
                .timestamp(String.valueOf(System.currentTimeMillis())).build();
        logRepository.save(updateLog);
		return ResponseEntity.ok(updateLog);
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public ResponseEntity<String> logCount() {
		return ResponseEntity.ok(logRepository.count() + "");
	}
}
