package com.liveperson.me.logs.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lp_logs")
@Data
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class LogSuperModel {

	@Id
	protected LogId id;
	protected String message;
    protected String timestamp;
}
