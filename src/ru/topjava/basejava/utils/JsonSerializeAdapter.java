package ru.topjava.basejava.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class JsonSerializeAdapter<T> implements JsonSerializer<T> {

    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE = "INSTANCE";

    @Override
    public JsonElement serialize(T element, Type type, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty(CLASSNAME,element.getClass().getName());
        object.add(INSTANCE,context.serialize(element));
        return object;
    }
}
