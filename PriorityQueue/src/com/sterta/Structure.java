package com.sterta;

public class Structure<T, U> {

    private final T some_object;
    private final U priority;


    public Structure(T some_object, U priority) {
        this.some_object = some_object;
        this.priority = priority;
    }


    public U getPriority()
    {
        return priority;
    }

}