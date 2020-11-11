package com.liveperson.me.logs.repositories;

import com.liveperson.me.logs.models.Log;

import com.liveperson.me.logs.models.LogId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, LogId> {

}
