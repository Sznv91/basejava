package ru.topjava.basejava;

import ru.topjava.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Class clazz = r.getClass();
        System.out.println(Arrays.toString(clazz.getDeclaredMethods()));
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        field.set(r, "new_uuid");
        // TODO : invoke r.toString via reflection
        Method toString = clazz.getDeclaredMethod("toString");
        System.out.println("Reflection invoke " + toString.invoke(r));

        System.out.println(r);
    }
}
