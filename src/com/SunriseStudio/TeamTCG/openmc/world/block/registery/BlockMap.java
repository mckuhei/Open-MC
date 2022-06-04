package com.SunriseStudio.TeamTCG.openmc.world.block.registery;

import com.SunriseStudio.TeamTCG.openmc.util.ReflectHelper;
import com.SunriseStudio.TeamTCG.openmc.world.block.material.IBlockMaterial;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class BlockMap {
    private static final HashMap<String, IBlockMaterial> blockMaterialHashMap=new HashMap<>();
    private static final HashMap<String, IBlockMaterial> blockBehaviorHashMap=new HashMap<>();

    public static IBlockMaterial getByID(String id){
        return blockMaterialHashMap.get(id);
    }

    /**
     * search all class in jvm,find registerer,invoke them
     */
    public static void registerBlock(){
        try{
            //get class with annotation
            List<Class<?>> result=new ArrayList<>();
            Iterator<Class<?>> it=ReflectHelper.getAllClass();
            while (it.hasNext()){
                Class<?> clazz=it.next();
                if(Arrays.stream(clazz.getAnnotations()).anyMatch(annotation -> annotation instanceof BlockRegisterer)){
                    result.add(clazz);
                }
            }

            //find annotated method
            Iterator<Class<?>> classIterator= result.iterator();
            while (classIterator.hasNext()){
                Class<?> clazz=classIterator.next();
                for (Method m:classIterator.next().getMethods()){
                    if(Arrays.stream(m.getAnnotations()).anyMatch(annotation -> annotation instanceof BlockGetter)) {
                        BlockGetter getter = m.getAnnotation(BlockGetter.class);
                        blockMaterialHashMap.put(clazz.getAnnotation(BlockRegisterer.class).namespace()
                            + ":" + getter.id(),
                            (IBlockMaterial) m.invoke(clazz.getConstructor().newInstance(), blockBehaviorHashMap.get(getter.behavior())));
                    }
                };
            }
        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            //fuck!
        }
    }

    /**
     * search all class in jvm,find registerer,invoke them
     */
    public static void registerBlockBehavior(){
        try{
            //get class with annotation
            List<Class<?>> result=new ArrayList<>();
            Iterator<Class<?>> it=ReflectHelper.getAllClass();
            while (it.hasNext()){
                Class<?> clazz=it.next();
                if(Arrays.stream(clazz.getAnnotations()).anyMatch(annotation -> annotation instanceof BlockBehaviorRegisterer)){
                    result.add(clazz);
                }
            }

            //find annotated method
            Iterator<Class<?>> classIterator= result.iterator();
            while (classIterator.hasNext()){
                Class<?> clazz=classIterator.next();
                for (Method m:classIterator.next().getMethods()){
                    if(Arrays.stream(m.getAnnotations()).anyMatch(annotation -> annotation instanceof BlockBehaviorGetter)) {
                        BlockBehaviorGetter getter = m.getAnnotation(BlockBehaviorGetter.class);
                        blockBehaviorHashMap.put(clazz.getAnnotation(BlockBehaviorRegisterer.class).namespace()
                                        + ":" + getter.id(),
                                (IBlockMaterial) m.invoke(clazz.getConstructor().newInstance()));
                    }
                };
            }
        }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e){
            //fuck!
        }
    }

}
