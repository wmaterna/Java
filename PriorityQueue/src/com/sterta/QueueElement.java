package com.sterta;

public class QueueElement<T> implements Comparable<QueueElement<T>>{
    private T value;
    private int priority;
    private int additionalPriority;

    public QueueElement(T value, int priority, int additionalPriority) {
        this.value = value;
        this.priority = priority;
        this.additionalPriority = additionalPriority;
    }

    public int getAdditionalPriority() {
        return additionalPriority;
    }

    public int getPriority() {
        return priority;
    }
    public T getValue() {
        return value;
    }


    @Override
    public int compareTo(QueueElement<T> o) {
        if(Integer.compare(this.priority,o.priority)==0)
        {
            return Integer.compare(o.additionalPriority,this.additionalPriority);
        }
        return Integer.compare(this.priority,o.priority);
    }

    @Override
    public String toString() {
        return "QueueElement{" +
                "value=" + value +
                ", priority=" + priority +
                '}';
    }
}
