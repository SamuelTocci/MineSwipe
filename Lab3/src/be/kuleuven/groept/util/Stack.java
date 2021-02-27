package be.kuleuven.groept.util;

import java.util.ArrayList;

public class Stack<T> implements StackIntf<T> {

    public ArrayList<T> stack = new ArrayList<T>();

    @Override
    public void push(T o) {
        stack.add(o);
    }

    @Override
    public T pop() {
        if(stack.isEmpty()){
            return null;
        }
        else{
            T object = stack.get(stack.size() -1);
            stack.remove(stack.size() -1);
            return object;
        }
    }

    @Override
    public T peek() {
        if(stack.isEmpty()){
            return null;
        }
        else{
            return stack.get(stack.size() -1);
        }
    }

    @Override
    public boolean isEmpty() {
        if(stack.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        stack.clear();
    }

    // TODO: implement your stack

}
