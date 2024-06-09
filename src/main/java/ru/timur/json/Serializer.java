package ru.timur.json;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Serializer<T> implements Externalizable {
    public String writeAsJson(Object entity) throws IllegalAccessException{

        byte[] s = {};

        System.out.println(s.getClass().getName());
    }

    private Object values(Object entity) {
        Class<?> entityClass = entity.getClass();

        // is Object and not primitive
        if (entityClass.isPrimitive()) {
            return entity;
        } else if (entityClass.isArray()) {
            Class<?> arrayClass = entityClass.arrayType();
            arrayClass.cast(entity);
            arrayClass.getDeclaredFields();
        } else {
            Field[] fields = entity.getClass().getDeclaredFields();
            Map<String, Object> pairs = new HashMap<>();

            Arrays.stream(fields).forEach(field -> {
                field.setAccessible(true);
                try {
                    pairs.put(field.getName(), field.get(entity));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            return pairs;
        }
    }

    /**
     * The object implements the writeExternal method to save its contents
     * by calling the methods of DataOutput for its primitive values or
     * calling the writeObject method of ObjectOutput for objects, strings,
     * and arrays.
     *
     * @param out the stream to write the object to
     * @throws IOException Includes any I/O exceptions that may occur
     * @serialData Overriding methods should use this tag to describe
     * the data layout of this Externalizable object.
     * List the sequence of element types and, if possible,
     * relate the element to a public/protected field and/or
     * method of this Externalizable class.
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    /**
     * The object implements the readExternal method to restore its
     * contents by calling the methods of DataInput for primitive
     * types and readObject for objects, strings and arrays.  The
     * readExternal method must read the values in the same sequence
     * and with the same types as were written by writeExternal.
     *
     * @param in the stream to read data from in order to restore the object
     * @throws IOException            if I/O errors occur
     * @throws ClassNotFoundException If the class for an object being
     *                                restored cannot be found.
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
