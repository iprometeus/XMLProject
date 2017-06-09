package com.epam.lab;

import com.epam.lab.comparator.CandyComparator;
import com.epam.lab.domain.Candy;
import com.epam.lab.service.CandyService;
import com.epam.lab.service.impl.CandyServiceImpl;
import com.epam.lab.util.XMLUtil;
import com.epam.lab.wrapper.Sweets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class App {

    private static final String XML_FILE_NAME = "sweets.xml";
    private static final String XML_GENERATED_FILE_NAME = "sweets_generated.xml";
    private static final String XSD_FILE_NAME = "sweets.xsd";
    private static final String XSL_FILE_NAME = "sweets.xsl";
    private static final String HTML_FILE_NAME = "sweets.html";

    private static Logger logger = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {

        Sweets sweets = XMLUtil.xmlToObject(XML_FILE_NAME, XSD_FILE_NAME, Sweets.class);
        sweets.getCandies().sort(new CandyComparator());
        logger.info(sweets);

        XMLUtil.xmlToHTML(XML_FILE_NAME, XSL_FILE_NAME, HTML_FILE_NAME);

        CandyService candyService = new CandyServiceImpl();
        candyService.addAll(sweets.getCandies());

        List<Candy> candies = candyService.getAll();
        candies.stream().forEach(logger::info);

        XMLUtil.objectToXML(XML_GENERATED_FILE_NAME, new Sweets(candies));
    }
}