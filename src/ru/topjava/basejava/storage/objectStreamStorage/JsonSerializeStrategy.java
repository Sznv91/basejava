package ru.topjava.basejava.storage.objectStreamStorage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.topjava.basejava.model.AbstractSection;
import ru.topjava.basejava.model.Resume;
import ru.topjava.basejava.utils.JsonAbstractSectionAdapter;

import java.io.*;

public class JsonSerializeStrategy implements StorageStrategy {

    private static Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(AbstractSection.class, new JsonAbstractSectionAdapter())
            .create();

    @Override
    public void doWrite(Resume r, OutputStream outputStream) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(outputStream);
        osw.write(GSON.toJson(r));
        osw.close();
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        Reader isr = new InputStreamReader(inputStream);
        Resume result = GSON.fromJson(isr, Resume.class);
        isr.close();
        return result;
    }
}
