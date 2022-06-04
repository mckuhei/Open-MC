package com.SunriseStudio.TeamTCG.openmc.util.collections;

import java.util.Arrays;
import java.util.Objects;

public class ArrayUtil {
    public static <T>void iterateArray(T[] array, ArrayIterationAction<T> action){
        for(T item:array){
            action.action(item);
        }
    }

    public static <T>boolean arrayMatchAny(T t, T[] arr){
        return Arrays.stream(arr).anyMatch(t2 -> {
            boolean b=false;
            if(Objects.equals(t2, t)){
                b=true;
            }
            return b;
        });
    }

    public static <T>boolean arrayMatchNone(T t, T[] arr){
        return Arrays.stream(arr).noneMatch(t2 -> {
            boolean b=false;
            if(Objects.equals(t2, t)){
                b=true;
            }
            return b;
        });
    }

    public interface ArrayIterationAction<E>{
        void action(E item);
    }

    public static <T>boolean startWith(T[] t,T[] arr){
        boolean b=true;
        for (int i=0;i<t.length;i++){
            if (t[i] != arr[i]) {
                b = false;
                break;
            }
        }
        return b;
    }

    public static boolean startWith(byte[] t,byte[] arr){
        boolean b=true;
        for (int i=0;i<t.length;i++){
            if (t[i] != arr[i]) {
                b = false;
                break;
            }
        }
        return b;
    }


    public static <T>boolean endWith(T[] t,T[] arr){
        boolean b=true;
        for (int i=arr.length-t.length;i<arr.length;i++){
            if (t[i] != arr[arr.length-t.length+i]) {
                b = false;
                break;
            }
        }
        return b;
    }
}
