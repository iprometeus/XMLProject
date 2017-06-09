package com.epam.lab.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

public class SweetsValidationEventHandler implements ValidationEventHandler {

    private static Logger logger = LogManager.getLogger(SweetsValidationEventHandler.class.getName());

    @Override
    public boolean handleEvent(ValidationEvent event) {
        logger.error(String.format("\nEVENT. SEVERITY: %d. MESSAGE: %s. LINKED EXCEPTION: %s.\nLOCATOR. LINE NUMBER: %d." +
                        " COLUMN NUMBER: %d. OFFSET: %d. OBJECT: %s. NODE: %s. URL: %s.", event.getSeverity(), event.getMessage(),
                event.getLinkedException(), event.getLocator().getLineNumber(), event.getLocator().getColumnNumber(),
                event.getLocator().getOffset(), event.getLocator().getObject(), event.getLocator().getNode(), event.getLocator().getURL()));
        return true;
    }
}