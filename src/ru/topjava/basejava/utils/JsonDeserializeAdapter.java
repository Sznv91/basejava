package ru.topjava.basejava.utils;

import com.google.gson.*;

import java.lang.reflect.Type;

public class JsonDeserializeAdapter<T> implements JsonDeserializer<T> {
    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        JsonPrimitive primitive = object.getAsJsonPrimitive("CLASSNAME");
        String classname = primitive.getAsString();

        try {
            Class instance = Class.forName(classname);
            return jsonDeserializationContext.deserialize(object.get("INSTANCE"), instance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
