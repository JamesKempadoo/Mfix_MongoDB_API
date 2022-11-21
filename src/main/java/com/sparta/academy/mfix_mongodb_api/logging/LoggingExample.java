package com.sparta.academy.mfix_mongodb_api.logging;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingExample {
    //add logger at start of class
    private static final Logger logger = LoggerSingleton.getSingleton().getLogger();

    private void test(){
        //can change logging level with first parameter
        //second parameter = message
        logger.log(Level.INFO, "Method has started");
    }
}
