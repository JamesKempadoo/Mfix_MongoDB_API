package com.sparta.academy.mfix_mongodb_api.logging;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class CustomFilter implements Filter {
	@Override
	public boolean isLoggable(LogRecord record) {
		return record.getMessage().contains("Hello");
	}
}
