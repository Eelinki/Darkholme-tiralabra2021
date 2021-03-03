package fi.eelij.Darkholme.Util;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class CustomList<T> implements Iterable<T> {
    private Object[] list;
    private int pointer;

    public CustomList() {
        this.list = new Object[8];
        this.pointer = 0;
    }

    public boolean add(T obj) {
        checkSize();

        this.list[pointer] = obj;
        this.pointer++;

        return true;
    }

    public boolean remove(T obj) {
        boolean removed = false;

        for (int i = 0; i < this.list.length - 1; i++) {
            if (this.list[i] == obj) {
                this.list[i] = null;
                removed = true;
                pointer--;
                i++;
            }

            if (removed) {
                this.list[i - 1] = this.list[i];
            }
        }

        shrink();

        return removed;
    }

    private void shrink() {
        if (this.pointer < this.list.length / 2) {
            Object[] newList = new Object[this.list.length / 2];

            for (int i = 0; i < this.pointer; i++) {
                newList[i] = this.list[i];
            }

            this.list = newList;
        }
    }

    private void checkSize() {
        if (this.pointer >= this.list.length) {
            grow();
        }
    }

    private void grow() {
        Object[] newList = new Object[this.list.length * 2];

        for (int i = 0; i < this.list.length; i++) {
            newList[i] = this.list[i];
        }

        this.list = newList;
    }

    public T get(int i) {
        return (T) this.list[i];
    }

    public int size() {
        return this.pointer;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iter = new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < pointer && list[index] != null;
            }

            @Override
            public T next() {
                return (T) list[index++];
            }

            @Override
            public void remove() {
                CustomList.this.remove(get(index - 1));
            }
        };

        return iter;
    }
}
