package ru.topjava.basejava.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.topjava.basejava.model.AbstractSection;

public class JsonParser {

    //GsonBuilder builder = new GsonBuilder();
    //    builder
    //            .setPrettyPrinting()
    //            .serializeNulls()
    //            .registerTypeAdapter(AbstractSection.class, new JsonAbstractSectionAdapter());
    //Gson gson = builder.create();

   private static Gson GSON = new GsonBuilder()
           .setPrettyPrinting()
           .serializeNulls()
           .registerTypeAdapter(AbstractSection.class, new JsonAbstractSectionAdapter())
           .create();

    public static void marshal() {
    }


    public static void unmarshal() {

    }
}
