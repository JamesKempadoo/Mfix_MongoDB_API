package com.sparta.academy.mfix_mongodb_api.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

public class CustomConsoleHandler {
	public static ConsoleHandler getConsoleHandler() {
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.ALL);
		//consoleHandler.setFilter(new CustomFilter());
		return consoleHandler;
	}
}
