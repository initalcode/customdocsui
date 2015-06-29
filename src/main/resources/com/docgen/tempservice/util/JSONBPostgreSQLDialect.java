package com.docgen.tempservice.util;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL82Dialect;

public class JSONBPostgreSQLDialect extends PostgreSQL82Dialect {

	public JSONBPostgreSQLDialect() {
		super();
		registerColumnType(Types.JAVA_OBJECT, "jsonb");
	}

}

