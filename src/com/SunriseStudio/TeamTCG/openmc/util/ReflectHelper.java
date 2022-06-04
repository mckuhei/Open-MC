package com.SunriseStudio.TeamTCG.openmc.util;

import com.SunriseStudio.TeamTCG.openmc.Minecraft;

import java.lang.reflect.Field;
import java.util.*;

public class ReflectHelper {
    public static Iterator<Class<?>> getAllClass(){
        Class<?> classLoaderClass = Minecraft.class .getClassLoader().getClass();
        Field classLoaderField=null;
        try {
            classLoaderField = classLoaderClass.getField("classes");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //让我访问
        classLoaderField.setAccessible(true);
        //get all classes in jvm
        Vector<Class<?>> classes = null;
        try {
            classes = (Vector<Class<?>>) classLoaderField.get(ReflectHelper.class.getClassLoader());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return classes.iterator();
    }
}
