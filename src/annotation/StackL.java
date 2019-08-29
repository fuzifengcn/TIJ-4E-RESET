package annotation;

import java.util.LinkedList;

public class StackL<T> {
    private LinkedList<T> list = new LinkedList<>();
    public void push(T v){
        this.list.addFirst(v);
    }
    public T top(){
        return this.list.getFirst();
    }
    public T pop(){
        return list.removeFirst();
    }


}
