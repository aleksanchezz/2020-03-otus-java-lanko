package ru.otus.collection;

import java.util.*;


public class DIYArrayList<T> implements List<T> {

    private Object[] repository;
    private static final int DEFAULT_CAPACITY = 10;
    private int count = 0;

    public DIYArrayList() {
        this.repository = new Object[DEFAULT_CAPACITY];
    }

    public DIYArrayList(int capacity) {
        this.repository = new Object[capacity];
    }

    private int getNewCapacity() {
        return repository.length + DEFAULT_CAPACITY;
    }

    private void expandInternalRepository(int newCapacity) {
        repository = Arrays.copyOf(repository, newCapacity);
    }

    private void expandInternalRepository() {
        repository = Arrays.copyOf(repository, getNewCapacity());
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * -1 - Не содержит
     */
    private int findElementIndex(Object o) {
        if (o == null) {
            return -1;
        }
        for (int i = 0; i < count; i++)
            if (o.equals(repository[i])) {
                return i;
            }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return findElementIndex(o) >= 0;
    }

    private class DIYIterator implements Iterator<T> {
        int pointer;

        private DIYIterator() {
            new DIYIterator(0);
        }

        private DIYIterator(int index) {
            pointer = index;
        }

        @Override
        public boolean hasNext() {
            return (pointer < count);
        }

        @Override
        public T next() {
            if ((pointer >= count) || (pointer >= repository.length)) {
                throw new NoSuchElementException();
            }
            T element = (T) repository[pointer];
            pointer += 1;
            return element;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DIYIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[count];
        for (int i = 0; i < count; i++) {
            array[i] = repository[i];
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        if (count == repository.length) {
            expandInternalRepository();
        }
        repository[count] = t;
        count += 1;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    private void checkIndex(int i) {
        if ((i < 0) || (i >= count)) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T get(int i) {
        checkIndex(i);
        return (T) repository[i];
    }

    @Override
    public T set(int i, T t) {
        T oldValue = get(i);
        repository[i] = t;
        return oldValue;
    }

    @Override
    public void add(int i, T t) {
        checkIndex(i);
        repository[i] = t;
    }

    @Override
    public T remove(int i) {
        return null;
    }

    /**
     * Уточнить момент, про NullPointerException для o == null?
     */
    @Override
    public int indexOf(Object o) {
        return findElementIndex(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DIYListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        return new DIYListIterator(i);
    }

    /**
     * next - по сути, и есть "текущий" элемент, на который смотрит указатель
     */
    public class DIYListIterator extends DIYIterator implements ListIterator<T> {
        private int previousGivenElementIndex = -1;

        private DIYListIterator() {
            super(0);
        }

        private DIYListIterator(int index) {
            super(index);
        }

        @Override
        public T next() {
            if ((pointer >= count) || (pointer >= repository.length)) {
                throw new NoSuchElementException();
            }
            T element = (T) repository[pointer];
            previousGivenElementIndex = pointer;
            pointer += 1;
            return element;
        }

        @Override
        public boolean hasPrevious() {
            return (pointer > 0);
        }

        @Override
        public T previous() {
            if (previousGivenElementIndex < 0) {
                throw new NoSuchElementException();
            }
            pointer = previousGivenElementIndex;
            return (T) repository[previousGivenElementIndex];
        }

        @Override
        public int nextIndex() {
            return pointer;
        }

        @Override
        public int previousIndex() {
            return pointer - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T t) {
            try {
                DIYArrayList.this.set(previousGivenElementIndex, t);
            } catch (IndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException("Index out of bounds while DIYListIterator#set");
            }
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }


    @Override
    public List<T> subList(int i, int i1) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < count; i++) {
            builder
                    .append(repository[i])
                    .append(", ");
        }
        return builder
                .append("]")
                .toString();
    }
}
