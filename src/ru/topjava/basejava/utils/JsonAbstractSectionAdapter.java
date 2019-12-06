package ru.topjava.basejava.utils;

import com.google.gson.*;

import java.lang.reflect.Type;

public class JsonAbstractSectionAdapter implements JsonSerializer, JsonDeserializer {

    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE = "INSTANCE";

    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
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

    @Override
    public JsonElement serialize(Object element, Type type, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        object.addProperty(CLASSNAME,element.getClass().getName());
        object.add(INSTANCE,context.serialize(element));
        return object;
    }
}
