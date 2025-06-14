package com.steamdatasetprojetoleda.estruturas;

public class ListaDuplamenteEncadeada<T> {
    private class Node {
        T data;
        Node next;
        Node prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public ListaDuplamenteEncadeada() {
        head = null;
        tail = null;
        size = 0;
    }

    // Adiciona um elemento no final da lista.
    public void add(T element) {
        Node novo = new Node(element);
        if (head == null) {
            head = novo;
            tail = novo;
        } else {
            tail.next = novo;
            novo.prev = tail;
            tail = novo;
        }
        size++;
    }

    // Retorna o elemento armazenado na posição index (0-based).
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        Node current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current.data;
    }

    // Define um novo valor na posição index, retornando o antigo.
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        Node current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        T old = current.data;
        current.data = element;
        return old;
    }

    // Troca os elementos localizados nos índices i e j.
    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IndexOutOfBoundsException("Índice inválido para swap");
        }
        if (i == j)
            return;
        T temp = get(i);
        set(i, get(j));
        set(j, temp);
    }

    // Retorna o tamanho da lista.
    public int size() {
        return size;
    }
}