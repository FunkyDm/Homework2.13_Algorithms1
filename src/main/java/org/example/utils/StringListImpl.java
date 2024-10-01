package org.example.utils;

import org.example.exceptions.FullListException;
import org.example.exceptions.IllegalArgumentException;
import org.example.exceptions.OutOfRangeException;
import org.example.exceptions.MissingElementException;

import java.util.Objects;

public class StringListImpl implements StringList {

    private String[] stringArray;

    private int size = 0;

    public StringListImpl(int capacity) {
        stringArray = new String[capacity];
    }

    @Override
    public String add(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Нельзя добавить null");
        }
        if (size == stringArray.length) {
            throw new FullListException("Лист полностью заполнен");
        }

        stringArray[size] = item;
        size++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (index < 0 || index > size) {
            throw new OutOfRangeException("Индекс вне диапазона");
        }
        if (item == null) {
            throw new IllegalArgumentException("Нельзя добавить null");
        }
        if (size == stringArray.length) {
            throw new FullListException("Лист полностью заполнен");
        }

        for (int i = size; i > index; i--) {
            stringArray[i] = stringArray[i - 1];
        }

        stringArray[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (index < 0 || index >= size) {
            throw new OutOfRangeException("Индекс вне диапазона");
        }
        if (item == null) {
            throw new IllegalArgumentException("Нельзя добавить null");
        }

        return stringArray[index] = item;
    }

    @Override
    public String remove(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Нельзя добавить null");
        }

        boolean isFind = false;
        int elementIndex = 0;
        for(int i = 0; i < size; i++){
            if(stringArray[i] == item){
                stringArray[i] = null;
                isFind = true;
                elementIndex = i;
                break;
            }
        }

        if(!isFind){
            throw new MissingElementException("Такого элемента нет");
        }

        for(int i = size; i > elementIndex; i--){
            stringArray[i] = stringArray[i-1];
        }
        size--;
        return item;
    }

    @Override
    public String remove(int index) {
        if (index > stringArray.length) {
            throw new OutOfRangeException("Выход за пределы массива");
        }
        return stringArray[index] = null;
    }

    @Override
    public boolean contains(String item) {
        boolean isFind = false;
        for(int i = 0; i < size; i++){
            if(stringArray[i] == item){
                isFind = true;
            }
        }
        return isFind;
    }

    @Override
    public int indexOf(String item) {
        int index = -1;
        for (int i = 0; i < stringArray.length; i++) {
            if (Objects.equals(stringArray[i], item)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(String item) {
        int index = -1;
        for (int i = stringArray.length - 1; i >= 0; i--) {
            if (Objects.equals(stringArray[i], item)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public String get(int index) {
        if (index > stringArray.length) {
            throw new OutOfRangeException("Выход за пределы массива");
        }
        return stringArray[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return false;
    }

    @Override
    public int size() {
        int size = 0;
        for (String s : stringArray) {
            if (s != null) {
                size++;
            }
        }
        return size;
    }

    @Override
    public boolean isEmpty() {

    }

    @Override
    public void clear() {

    }

    @Override
    public String[] toArray() {
        return new String[0];
    }

}
