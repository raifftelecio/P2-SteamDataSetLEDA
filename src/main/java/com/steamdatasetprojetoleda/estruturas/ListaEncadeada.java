package com.steamdatasetprojetoleda.estruturas;

public class ListaEncadeada<T> {
    private class Node {
        T data;
        Node next;
        
        public Node(T data) {
            this.data = data;
            next = null;
        }
    }
    
    private Node head;
    private Node tail;
    private int size;
    
    public ListaEncadeada() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public void add(T element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        Node atual = head;
        for (int i = 0; i < index; i++) {
            atual = atual.next;
        }
        return atual.data;
    }
    
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        Node atual = head;
        for (int i = 0; i < index; i++) {
            atual = atual.next;
        }
        T oldValue = atual.data;
        atual.data = element;
        return oldValue;
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IndexOutOfBoundsException("Índice inválido para swap");
        }
        if (i == j) return;
        
        Node nodeI = head;
        for (int k = 0; k < i; k++) {
            nodeI = nodeI.next;
        }
        Node nodeJ = head;
        for (int k = 0; k < j; k++) {
            nodeJ = nodeJ.next;
        }
        T temp = nodeI.data;
        nodeI.data = nodeJ.data;
        nodeJ.data = temp;
    }
    
    public int size() {
        return size;
    }
}
