package com.liveperson.me.logs;

import com.liveperson.me.logs.exception.ResourceAlreadyExistsException;
import com.liveperson.me.logs.models.Log;
import com.liveperson.me.logs.models.LogId;
import com.liveperson.me.logs.repositories.LogRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LogsApplicationTests {

	@Mock
	private LogRepository logRepository;
	@InjectMocks
	private LogController logController;

	@Test
	public void getLogs() {
		String id = UUID.randomUUID().toString();
		final Log log = Log.builder()
				.id(new LogId(id, 0))
				.message("test")
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.build();
		List<Log> logs = Arrays.asList(log);
		when(logRepository.findAll()).thenReturn(logs);
		ResponseEntity<List<Log>> receivedLog = logController.getLogs();
		verify(logRepository,times(1)).findAll();
		assertEquals(log,receivedLog.getBody().get(0));
	}

	@Test
	public void save() {
		String id = UUID.randomUUID().toString();
		final Log log = Log.builder()
				.id(new LogId(id, 0))
				.message("test")
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.build();
		when(logRepository.save(Mockito.any())).thenReturn(log);

		ResponseEntity<Log> savedLog = logController.save("test");

		verify(logRepository,times(1)).save(Mockito.any());
	}

	@Test
	public void update() {
		String id = UUID.randomUUID().toString();
		final Log log = Log.builder()
				.id(new LogId(id, 0))
				.message("test")
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.build();
		when(logRepository.findById(new LogId(id, 0))).thenReturn(Optional.of(log));
		when(logRepository.save(Mockito.any())).thenReturn(log);

		ResponseEntity<Log> savedLog = logController.update(id,0L,"test2");

		verify(logRepository,times(1)).save(Mockito.any());
		assertEquals("test2",savedLog.getBody().getMessage());
		assertEquals(1L,savedLog.getBody().getId().getVersion());
		assertEquals(id,savedLog.getBody().getId().getId());
	}

	@Test(expected = ResourceAlreadyExistsException.class)
	public void updateSameMessage() {
		String id = UUID.randomUUID().toString();
		final Log log = Log.builder()
				.id(new LogId(id, 0))
				.message("test")
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.build();
		when(logRepository.findById(new LogId(id, 0))).thenReturn(Optional.of(log));

		ResponseEntity<Log> savedLog = logController.update(id,0L,"test");
	}
}
