package ru.topjava.basejava.storage.objectStreamStorage;

import ru.topjava.basejava.model.*;
import ru.topjava.basejava.utils.XmlParser;

import java.io.*;

public class XmlSerializeStrategyStorage implements StorageStrategy {

    private XmlParser parser;

    @Override
    public void doWrite(Resume r, OutputStream outputStream) throws IOException {
        Writer writer = new OutputStreamWriter(outputStream);
        parser = new XmlParser(Resume.class, CompanySection.class, ListSection.class,
                Organization.class, Organization.Position.class, TextSection.class);
        parser.marshall(r,writer);
        writer.close();
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        Reader reader = new InputStreamReader(inputStream);
        Resume result = parser.unmarshal(reader);
        reader.close();
        return result;
    }
}
