package ru.topjava.basejava.storage.objectStreamStorage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.topjava.basejava.model.AbstractSection;
import ru.topjava.basejava.model.Resume;
import ru.topjava.basejava.utils.JsonDeserializeAdapter;
import ru.topjava.basejava.utils.JsonSerializeAdapter;

import java.io.*;

public class JsonSerializeStrategy implements StorageStrategy {
    @Override
    public void doWrite(Resume r, OutputStream outputStream) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder
                .setPrettyPrinting()
                .serializeNulls()
                .registerTypeAdapter(AbstractSection.class, new JsonSerializeAdapter<>());
        Gson gson = builder.create();
        OutputStreamWriter osw = new OutputStreamWriter(outputStream);
        osw.write(gson.toJson(r));
        osw.close();
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder
                .setPrettyPrinting()
                .serializeNulls()
                .registerTypeAdapter(AbstractSection.class, new JsonDeserializeAdapter<>());
        Gson gson = builder.create();
        Reader isr = new InputStreamReader(inputStream);
        Resume result = gson.fromJson(isr,Resume.class);
        isr.close();
        return result;
    }
}
