package com.steamdatasetprojetoleda.estruturas;

import org.apache.commons.csv.CSVRecord;

public class PriorityQueueAchievements {
    private CSVRecord[] heap;
    private int size;

    public PriorityQueueAchievements(int capacidade) {
        heap = new CSVRecord[capacidade];
        size = 0;
    }

    // Insere um registro na fila de prioridade.
    // A comparação é feita utilizando o valor do campo de conquistas (índice 26).
    public void inserir(CSVRecord registro) {
        if (size == heap.length) {
            throw new IllegalStateException("Fila cheia!");
        }
        heap[size] = registro;
        subir(size);
        size++;
    }

    // Remove e retorna o registro com maior número de conquistas.
    public CSVRecord remover() {
        if (size == 0) {
            throw new IllegalStateException("Fila vazia!");
        }
        CSVRecord maior = heap[0];
        heap[0] = heap[size - 1];
        size--;
        descer(0);
        return maior;
    }

    // Retorna um array contendo os registros que estão atualmente na fila.
    public CSVRecord[] toArray() {
        CSVRecord[] ordenado = new CSVRecord[size];
        for (int i = 0; i < size; i++) {
            ordenado[i] = heap[i];
        }
        return ordenado;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Método auxiliar: sobe o elemento inserido até que a propriedade do heap seja mantida.
    private void subir(int indice) {
        while (indice > 0) {
            int pai = (indice - 1) / 2;
            if (Integer.parseInt(heap[indice].get(26)) <= Integer.parseInt(heap[pai].get(26))) {
                break;
            }
            trocar(indice, pai);
            indice = pai;
        }
    }

    // Método auxiliar: desce o elemento na posição 'indice' para restaurar a propriedade do heap.
    private void descer(int indice) {
        while (true) {
            int esquerda = 2 * indice + 1;
            int direita = 2 * indice + 2;
            int maior = indice;
            if (esquerda < size && Integer.parseInt(heap[esquerda].get(26)) > Integer.parseInt(heap[maior].get(26))) {
                maior = esquerda;
            }
            if (direita < size && Integer.parseInt(heap[direita].get(26)) > Integer.parseInt(heap[maior].get(26))) {
                maior = direita;
            }
            if (maior == indice) {
                break;
            }
            trocar(indice, maior);
            indice = maior;
        }
    }

    // Troca os elementos das posições i e j no heap.
    private void trocar(int i, int j) {
        CSVRecord temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
