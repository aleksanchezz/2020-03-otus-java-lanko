package ru.otus.mygson;

import java.util.Arrays;
import java.util.Collection;

/**
 * Выделил 4 вида типов данных, которые могут быть:
 *  - примитивы
 *  - стандартные (обретки Integer, Long и пр.)
 *  - Коллекции (implements Collection)
 *  - Сложные кастомные объекты (такие как Person, Address)
 */
public class Type {
    private static final String COLLECTION_INTERFACE_NAME = Collection.class.getName();
    private static final String JAVA_LANG_PACKAGE_NAME = "java.lang";

    private final Class<?> clazz;
    private boolean isCollection = false;
    private boolean isCustomObject = false;
    private boolean isJavaStandardType = false;
    private boolean isPrimitive = false;

    private boolean isSimple = false;
    private boolean isComplex = false;

    public Type(Class<?> clazz) {
        this.clazz = clazz;
        process();
    }

    private void process() {
        isPrimitive = checkIfPrimitive();
        isCollection = checkIfCollection();
        isJavaStandardType = checkIfStandard();
        isCustomObject = !(isPrimitive | isCollection | isJavaStandardType);

        isSimple = isPrimitive | isJavaStandardType;
        isComplex = isCollection | isCustomObject;
    }

    private boolean checkIfPrimitive() {
        return clazz.isPrimitive();
    }

    private boolean checkIfCollection() {
        // Проверить интерфейсы, найти если есть Collection
        return Arrays
                .stream(clazz.getInterfaces())
                .map(Class::getName)
                .anyMatch(i -> i.contains(COLLECTION_INTERFACE_NAME));
    }

    private boolean checkIfStandard() {
        return clazz.getName().contains(JAVA_LANG_PACKAGE_NAME);
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public boolean isCustomObject() {
        return isCustomObject;
    }

    public boolean isJavaStandardType() {
        return isJavaStandardType;
    }

    public boolean isPrimitive() {
        return isPrimitive;
    }

    public boolean isSimple() {
        return isSimple;
    }

    public boolean isComplex() {
        return isComplex;
    }

    @Override
    public String toString() {
        return "Type{" +
                "clazz=" + clazz.getSimpleName() +
                ", isPrimitive=" + isPrimitive +
                ", isCollection=" + isCollection +
                ", isJavaStandardType=" + isJavaStandardType +
                ", isCustomObject=" + isCustomObject +
                '}';
    }
}
