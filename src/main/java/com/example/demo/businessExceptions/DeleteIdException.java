package com.example.demo.businessExceptions;

import com.example.demo.controller.MainController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteIdException extends Exception {

    private static final Logger logger = LogManager.getLogger(MainController.class);

        public DeleteIdException() {
            logger.error("Ошибка удаления юзера по ID");
        }
}
