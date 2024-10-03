package org.example.utils;

import org.example.exceptions.FullListException;
import org.example.exceptions.IllegalArgumentException;
import org.example.exceptions.OutOfRangeException;
import org.example.exceptions.MissingElementException;

import java.util.Arrays;
import java.util.Objects;

public class StringListImpl implements StringList {

    private final String[] stringArray;

    private int size;

    public StringListImpl(int capacity) {
        stringArray = new String[capacity];
        size = 0;
    }

    @Override
    public String add(String item) {
        checkNullElement(item);
        checkListIsFulled();

        stringArray[size] = item;
        size++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        checkNullElement(item);
        checkListIsFulled();
        if (index < 0 || index > size) {
            throw new OutOfRangeException("Индекс вне диапазона");
        }

        for (int i = size; i > index; i--) {//todo заменить на arraycopy
            stringArray[i] = stringArray[i - 1];
        }
        stringArray[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkNullElement(item);
        if (index < 0 || index >= size) {
            throw new OutOfRangeException("Индекс вне диапазона");
        }

        String oldItem = stringArray[index];
        stringArray[index] = item;
        return oldItem;
    }

    @Override
    public String remove(String item) {
        checkNullElement(item);

        int index = indexOf(item);
        if (index == -1) {
            throw new MissingElementException("Такого элемента нет");
        }
        return remove(index);
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new OutOfRangeException("Индекс вне диапазона");
        }

        String removedItem = stringArray[index];
        for (int i = size; i > index; i--) {//todo заменить на arraycopy
            stringArray[i] = stringArray[i - 1];
        }
        size--;
        stringArray[size] = null;
        return removedItem;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        checkNullElement(item);

        for (int i = 0; i < size; i++) {
            if (stringArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        checkNullElement(item);

        for (int i = size - 1; i >= 0; i--) {
            if (stringArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new OutOfRangeException("Индекс вне диапазона");
        }

        return stringArray[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new IllegalArgumentException("Нельзя добавить null");
        }
        if (size != otherList.size()) {
            throw new ArithmeticException("Размеры массивов отличаются");
        }

        for (int i = 0; i < size; i++) {
            if (!stringArray[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return stringArray[0] == null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            stringArray[i] = null;
        }
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(stringArray, size);
    }

    private void checkNullElement(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Нельзя добавить null");
        }
    }

    private void checkListIsFulled() {
        if (size == stringArray.length) {
            throw new FullListException("Лист полностью заполнен");
        }
    }

}
