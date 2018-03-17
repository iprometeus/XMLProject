package com.epam.lab.util;

import com.epam.lab.handler.SweetsValidationEventHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class XMLUtil {

    private static Logger logger = LogManager.getLogger(XMLUtil.class.getName());

    public static <T> T xmlToObject(String fileName, String xsdFileName, Class<T> clazz) {
        try {
            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);

            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(xsdFileName));

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            unmarshaller.setSchema(schema);
            unmarshaller.setEventHandler(new SweetsValidationEventHandler());

            T result = clazz.cast(unmarshaller.unmarshal(file));
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static void objectToXML(String fileName, Object obj) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(obj, new File(fileName));
        } catch (JAXBException e) {
            logger.error(e.getMessage());
        }
    }

    public static void xmlToHTML(String xmlFileName, String xslFileName, String htmlFileName) {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Source xslDoc = new StreamSource(xslFileName);
            Source xmlDoc = new StreamSource(xmlFileName);

            OutputStream htmlFile = new FileOutputStream(htmlFileName);
            Transformer transformer = tFactory.newTransformer(xslDoc);
            transformer.transform(xmlDoc, new StreamResult(htmlFile));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}