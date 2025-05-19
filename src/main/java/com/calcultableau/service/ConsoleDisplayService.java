package com.calcultableau.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ConsoleDisplayService implements DisplayService {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleDisplayService.class);

    @Override
    public void afficherMessage(String message) {
        logger.info(message);
    }
}