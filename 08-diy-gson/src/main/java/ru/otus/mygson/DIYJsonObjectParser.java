package ru.otus.mygson;

import ru.otus.model.Pair;
import ru.otus.exception.ClassParseException;

import java.lang.reflect.Field;
import java.util.*;

public class DIYJsonObjectParser {
    private final Class<?> clazz;
    private final Object object;
    private Map<String, Pair<Type, Object>> fields = new HashMap<>();

    public DIYJsonObjectParser(Object object) {
        clazz = object.getClass();
        this.object = object;
    }

    public DIYJsonObjectParser parse() throws Exception {
        try {
            parseMetaInfo(object);
        }
        catch (IllegalAccessException e) {
            throw new ClassParseException(e);
        }
        return this;
    }

    private void parseMetaInfo(Object object) throws IllegalAccessException {
        for(Field field: clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object fieldValue = field.get(object);
            Pair<Type, Object> typeValuePair = new Pair<>(new Type(field.getType()), fieldValue);
            fields.putIfAbsent(field.getName(), typeValuePair);
        }
    }

    public String buildJson() throws Exception {
        StringBuilder json = new StringBuilder();
        json.append("{");
        for(Map.Entry<String, Pair<Type, Object>> field: fields.entrySet()) {
            String name = field.getKey();
            Type type = field.getValue().getLeft();
            Object value = field.getValue().getRight();

            if (type.isSimple()) {
                json.append(buildStringForPrimitiveValue(name, value));
            }
            else if (type.isJavaStandardType()) {
                json.append(buildStringForJavaStandardType(name, value));
            }
            else if (type.isCollection()) {
                json.append(buildStringForCollectionType(name, value));
            }
            else if (type.isCustomObject()) {
                json.append(buildStringForCustomObject(name, value));
            }
            json.append(",");
        }
        json.deleteCharAt(json.length() - 1); // убрать запутую, добавленную на последней итерации
        json.append("}");
        return json.toString();
    }

    public String buildStringForPrimitiveValue(String key, Object value) {
        if (value instanceof String) {
            return "\"" + key + "\": \"" + value.toString() + "\"";
        }
        else {
            return "\"" + key + "\":" + value;
        }
    }

    public String buildStringForJavaStandardType(String key, Object value) {
            return "\"" + key + "\":" + value.toString();
    }

    public String buildStringForCollectionType(String key, Object value) throws Exception {
        StringBuilder builder = new StringBuilder();
        Collection collection = (Collection) value;

        if (collection.isEmpty()) {
            return "\"" + key + "\":[ ]";
        }
        else {

            for (Object obj : collection) {
                builder.append(buildStringForCustomObject(obj));
                builder.append(",");
            }
            builder.deleteCharAt(builder.length() - 1); // убрать запутую, добавленную на последней итерации
            return "\"" + key + "\":[" + builder.toString() + "]";
        }
    }

    public String buildStringForCustomObject(String key, Object value) throws Exception {
        if (value == null) {
            return "\"" + key + "\": null";
        }
        else {
            return "\"" + key + "\": " + buildStringForCustomObject(value);
        }
    }

    private String buildStringForCustomObject(Object value) throws Exception {
        return new DIYJsonObjectParser(value).parse().buildJson();
    }
}
