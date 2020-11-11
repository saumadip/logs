package com.liveperson.me.logs.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "lp_logs")
@Data
@SuperBuilder
@NoArgsConstructor
public class Log extends LogSuperModel {

	@Override
	public String toString() {
		return String.format("Log[id=%s,version=%d,timestamp=%s, message='%s']",
						id.getId(),id.getVersion(),getTimestamp(), getMessage());
	}
}
