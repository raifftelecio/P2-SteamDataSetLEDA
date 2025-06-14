package com.steamdatasetprojetoleda.ordenações;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVRecord;

import com.steamdatasetprojetoleda.estruturas.ListaDuplamenteEncadeada;

public class OrdenacaoPrice {

    public static void processarOrdenacoesPorPrice() throws IOException {
        // Carrega a lista original a partir do CSV
        Path caminhoArquivo = Paths.get("src", "test", "java", "com", "steamdatasetprojetoleda", "games_formated_release_data.csv");
        ListaDuplamenteEncadeada<CSVRecord> listaOriginal = AlgoritmosPrice.carregarLista(caminhoArquivo);
        if (listaOriginal == null) {
            System.out.println("Erro ao carregar a lista do CSV.");
            return;
        }

        long tempoInicio, tempoFim, duracao, memoriaAntes, memoriaDepois, memoriaTotal;
        ListaDuplamenteEncadeada<CSVRecord> listaOrdenada;

        System.out.println("Executando ordenações no campo Price (caso médio):\n");

        // ---- CASO MÉDIO ----

        // Selection Sort - Entrada desordenada
        System.out.println("Método Selection Sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorSelection(cloneLista(listaOriginal));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_selectionSort_medioCaso.csv", listaOrdenada);

        // Insertion Sort - Entrada desordenada
        System.out.println("Método Insertion sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorInsertion(cloneLista(listaOriginal));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_insertionSort_medioCaso.csv", listaOrdenada);

        // Merge Sort - Entrada desordenada
        System.out.println("Método Merge sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorMerge(cloneLista(listaOriginal));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_mergeSort_medioCaso.csv", listaOrdenada);

        // Quick Sort - Entrada desordenada
        System.out.println("Método Quick sort -> Entrada desordenada:");
        ListaDuplamenteEncadeada<CSVRecord> copiaQuick = cloneLista(listaOriginal);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorQuickSort(copiaQuick, 0, copiaQuick.size() - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_quickSort_medioCaso.csv", listaOrdenada);

        // Quick Sort com Mediana de 3 - Entrada desordenada
        System.out.println("Método Quick sort (Mediana de 3) -> Entrada desordenada:");
        ListaDuplamenteEncadeada<CSVRecord> copiaQuickMediana = cloneLista(listaOriginal);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorQuickSortMediana3(copiaQuickMediana, 0, copiaQuickMediana.size() - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_quickSort_mediana_de_3_medioCaso.csv", listaOrdenada);

        // Heap Sort - Entrada desordenada
        System.out.println("Método Heap sort -> Entrada desordenada:");
        ListaDuplamenteEncadeada<CSVRecord> copiaHeap = cloneLista(listaOriginal);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorHeap(copiaHeap);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_heapSort_medioCaso.csv", listaOrdenada);

        System.out.println("\nObservação: O melhor caso será simulado utilizando uma lista previamente ordenada.\n");

        // ---- CASO MELHOR ----
        // Carrega a lista já ordenada a partir do CSV gerado (por exemplo, a partir do HeapSort do caso médio)
        Path caminhoOrdenado = Paths.get("src", "test", "java", "com", "steamdatasetprojetoleda", "games_price_heapSort_medioCaso.csv");
        ListaDuplamenteEncadeada<CSVRecord> listaMelhorCaso = AlgoritmosPrice.carregarLista(caminhoOrdenado);
        if (listaMelhorCaso == null) {
            System.out.println("Erro ao carregar a lista ordenada (melhor caso).");
            return;
        }

        System.out.println("Executando ordenações no campo Price (melhor caso - dados já ordenados):\n");

        // Selection Sort - Melhor caso
        System.out.println("Método Selection sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorSelection(cloneLista(listaMelhorCaso));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_selectionSort_melhorCaso.csv", listaOrdenada);

        // Insertion Sort - Melhor caso
        System.out.println("Método Insertion sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorInsertion(cloneLista(listaMelhorCaso));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_insertionSort_melhorCaso.csv", listaOrdenada);

        // Merge Sort - Melhor caso
        System.out.println("Método Merge sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorMerge(cloneLista(listaMelhorCaso));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_mergeSort_melhorCaso.csv", listaOrdenada);

        // Quick Sort - Melhor caso
        System.out.println("Método Quick sort -> Entrada ordenada:");
        ListaDuplamenteEncadeada<CSVRecord> copiaQuickMelhor = cloneLista(listaMelhorCaso);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorQuickSort(copiaQuickMelhor, 0, copiaQuickMelhor.size() - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_quickSort_melhorCaso.csv", listaOrdenada);

        // Quick Sort com Mediana de 3 - Melhor caso
        System.out.println("Método Quick sort (Mediana de 3) -> Entrada ordenada:");
        ListaDuplamenteEncadeada<CSVRecord> copiaQuickMedianaMelhor = cloneLista(listaMelhorCaso);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorQuickSortMediana3(copiaQuickMedianaMelhor, 0, copiaQuickMedianaMelhor.size() - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_quickSort_mediana_de_3_melhorCaso.csv", listaOrdenada);

        // Heap Sort - Melhor caso
        System.out.println("Método Heap sort -> Entrada ordenada:");
        ListaDuplamenteEncadeada<CSVRecord> copiaHeapMelhor = cloneLista(listaMelhorCaso);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorHeap(copiaHeapMelhor);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_heapSort_melhorCaso.csv", listaOrdenada);

        // ---- CASO PIOR ----
        // Para simular o pior caso, partimos da mesma lista ordenada (do melhor caso) e invertemos sua ordem
        ListaDuplamenteEncadeada<CSVRecord> listaPiorCaso = cloneLista(listaMelhorCaso);
        AlgoritmosPrice.inverterLista(listaPiorCaso);

        System.out.println("\nExecutando ordenações no campo Price (pior caso - dados ordenados em ordem inversa):\n");

        // Selection Sort - Pior caso
        System.out.println("Método Selection sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorSelection(cloneLista(listaPiorCaso));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_selectionSort_piorCaso.csv", listaOrdenada);

        // Insertion Sort - Pior caso
        System.out.println("Método Insertion sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorInsertion(cloneLista(listaPiorCaso));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_insertionSort_piorCaso.csv", listaOrdenada);

        // Merge Sort - Pior caso
        System.out.println("Método Merge sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorMerge(cloneLista(listaPiorCaso));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_mergeSort_piorCaso.csv", listaOrdenada);

        // Quick Sort - Pior caso
        System.out.println("Método Quick sort -> Entrada ordenada em ordem inversa:");
        ListaDuplamenteEncadeada<CSVRecord> copiaQuickPior = cloneLista(listaPiorCaso);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorQuickSort(copiaQuickPior, 0, copiaQuickPior.size() - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_quickSort_piorCaso.csv", listaOrdenada);

        // Quick Sort com Mediana de 3 - Pior caso
        System.out.println("Método Quick sort (Mediana de 3) -> Entrada ordenada em ordem inversa:");
        ListaDuplamenteEncadeada<CSVRecord> copiaQuickMedianaPior = cloneLista(listaPiorCaso);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorQuickSortMediana3(copiaQuickMedianaPior, 0, copiaQuickMedianaPior.size() - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_quickSort_mediana_de_3_piorCaso.csv", listaOrdenada);

        // Heap Sort - Pior caso
        System.out.println("Método Heap sort -> Entrada ordenada em ordem inversa:");
        ListaDuplamenteEncadeada<CSVRecord> copiaHeapPior = cloneLista(listaPiorCaso);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosPrice.ordenarPorHeap(copiaHeapPior);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_heapSort_piorCaso.csv", listaOrdenada);
    }

    // Método auxiliar para clonar uma ListaDuplamenteEncadeada de CSVRecord.
    private static ListaDuplamenteEncadeada<CSVRecord> cloneLista(ListaDuplamenteEncadeada<CSVRecord> original) {
        ListaDuplamenteEncadeada<CSVRecord> nova = new ListaDuplamenteEncadeada<>();
        for (int i = 0; i < original.size(); i++) {
            nova.add(original.get(i));
        }
        return nova;
    }
}
