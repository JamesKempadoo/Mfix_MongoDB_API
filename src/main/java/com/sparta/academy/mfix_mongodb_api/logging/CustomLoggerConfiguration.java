package com.sparta.academy.mfix_mongodb_api.logging;


import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLoggerConfiguration {
	public static void configureLogger(Logger logger, Level selectedLevel) {
		logger.setUseParentHandlers(false); //Don't use any logging from the root logger
		logger.addHandler(CustomFileHandler.getFileHandler());
		logger.addHandler(CustomConsoleHandler.getConsoleHandler());
		logger.setLevel(selectedLevel);
	}
}
