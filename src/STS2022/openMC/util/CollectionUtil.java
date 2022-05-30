package tcgstudio2022.openMC.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionUtil {
    public static <E>void iterateList(List<E> list, ListIterationAction<E> action){
        for(E item:list){
            action.action(item);
        }
    }

    public static <K,V>void iterateMap(Map<K,V> map, MapIterationAction<K,V> action){
        Set<K> keys=map.keySet();
        for(K key:keys){
            action.action(key,map.get(key));
        }
    }

    public static <T>void iterateArray(T[] array, ArrayIterationAction<T> action){
        for(T item:array){
            action.action(item);
        }
    }

    public interface ListIterationAction<E>{
        void action(E item);
    }

    public interface MapIterationAction<K,V>{
        void action(K key,V item);
    }

    public interface ArrayIterationAction<E>{
        void action(E item);
    }
}
