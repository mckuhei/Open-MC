package tcgstudio2022.openMC.util;

import java.util.*;

public class ArrayQueue <E> {
    public ArrayList<E> items=new ArrayList<>();

    //information
    public int size() {
        return this.items.size();
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    public boolean contains(Object o) {
        return this.items.contains(o);
    }

    //operation
    public Iterator<E> iterator() {
        return this.items.iterator();
    }

    public <T> T[] toArray(T[] a) {
        return this.items.toArray(a);
    }

    public void add(E e) {
        items.add(this.items.size(),e);
    }

    public void addAll(List<E> all){
        this.items.addAll(this.items.size(),all);
    }

    public E poll(){
        E e=this.items.get(0);
        this.items.remove(0);
        return e;
    }
    public List<E> pollAll(int count){
        ArrayList<E> returns=new ArrayList<>();
        for (int i = 0; i < count; i++) {
            returns.add(this.poll());
        }
        return returns;
    }
    public void clear() {
        this.items.clear();
    }

    public List<E> pollIf(int count ,PollIf<E> pollIf){
        ArrayList<E> returns=new ArrayList<>();
        for (E e:this.items) {
            if(pollIf.If(e)){
                returns.add(e);
            }//给我standby到满足条件
            if(returns.size()>=count){
                break;
            }
        }
        this.items.removeAll(returns);
        return returns;
    }
    public interface PollIf<E>{
        boolean If(E e);
    }



}
