package ru.topjava.basejava.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.Writer;

public class XmlParser {
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public XmlParser(Class... classesToBeBound) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(classesToBeBound);

            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            unmarshaller = ctx.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void  marshall(Object instance, Writer writer) {
        try {
            marshaller.marshal(instance,writer);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public <T> T unmarshal(Reader reader) {
        try {
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }

    }
}
