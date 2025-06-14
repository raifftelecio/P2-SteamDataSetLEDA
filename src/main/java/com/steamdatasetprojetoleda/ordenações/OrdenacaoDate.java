package com.steamdatasetprojetoleda.ordenações;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVRecord;

import com.steamdatasetprojetoleda.estruturas.ListaEncadeada;

public class OrdenacaoDate {

    public static void processarOrdenacoesPorDate() throws IOException {
        Path caminhoArquivo = Paths.get("src", "test", "java", "com", "steamdatasetprojetoleda", "games_formated_release_data.csv");
        ListaEncadeada<CSVRecord> listaOriginal = AlgoritmosDate.carregarLista(caminhoArquivo);
        if (listaOriginal == null) {
            System.out.println("Erro ao carregar a lista do CSV.");
            return;
        }

        long tempoInicio, tempoFim, duracao, memoriaAntes, memoriaDepois, memoriaTotal;
        ListaEncadeada<CSVRecord> listaOrdenada;

        System.out.println("Executando ordenações no campo Release Date (caso médio):\n");

        // MÉTODO: Selection Sort - Entrada desordenada (caso médio)
        System.out.println("Método Selection Sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorSelection(cloneLista(listaOriginal));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_selectionSort_medioCaso.csv", listaOrdenada);

        // MÉTODO: Insertion Sort - Entrada desordenada (caso médio)
        System.out.println("Método Insertion Sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorInsertion(cloneLista(listaOriginal));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_insertionSort_medioCaso.csv", listaOrdenada);

        // MÉTODO: Merge Sort - Entrada desordenada (caso médio)
        System.out.println("Método Merge Sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorMerge(cloneLista(listaOriginal));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_mergeSort_medioCaso.csv", listaOrdenada);

        // MÉTODO: Quick Sort - Entrada desordenada (caso médio)
        System.out.println("Método Quick Sort -> Entrada desordenada:");
        ListaEncadeada<CSVRecord> copiaQuick = cloneLista(listaOriginal);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorQuickSort(copiaQuick, 0, copiaQuick.size() - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_quickSort_medioCaso.csv", listaOrdenada);

        // MÉTODO: Quick Sort (Mediana de 3) - Entrada desordenada (caso médio)
        System.out.println("Método Quick Sort (Mediana de 3) -> Entrada desordenada:");
        ListaEncadeada<CSVRecord> copiaQuickMediana = cloneLista(listaOriginal);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorQuickSortMediana3(copiaQuickMediana, 0, copiaQuickMediana.size() - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_quickSort_mediana_de_3_medioCaso.csv", listaOrdenada);

        // MÉTODO: Heap Sort - Entrada desordenada (caso médio)
        System.out.println("Método Heap Sort -> Entrada desordenada:");
        ListaEncadeada<CSVRecord> copiaHeap = cloneLista(listaOriginal);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorHeap(copiaHeap);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_heapSort_medioCaso.csv", listaOrdenada);

        System.out.println("\nObservação: O melhor caso será simulado utilizando uma lista previamente ordenada.\n");

        // Carrega a lista já ordenada (pelo Merge Sort do caso médio) para simular o melhor caso
        Path caminhoOrdenado = Paths.get("src", "test", "java", "com", "steamdatasetprojetoleda", "games_release_date_mergeSort_medioCaso.csv");
        ListaEncadeada<CSVRecord> listaMelhorCaso = AlgoritmosDate.carregarLista(caminhoOrdenado);
        if (listaMelhorCaso == null) {
            System.out.println("Erro ao carregar a lista ordenada (melhor caso).");
            return;
        }

        System.out.println("Executando ordenações no campo Release Date (melhor caso - dados já ordenados):\n");

        // Selection Sort - melhor caso
        System.out.println("Método Selection Sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorSelection(cloneLista(listaMelhorCaso));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_selectionSort_melhorCaso.csv", listaOrdenada);

        // Insertion Sort - melhor caso
        System.out.println("Método Insertion Sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorInsertion(cloneLista(listaMelhorCaso));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_insertionSort_melhorCaso.csv", listaOrdenada);

        // Merge Sort - melhor caso
        System.out.println("Método Merge Sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorMerge(cloneLista(listaMelhorCaso));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_mergeSort_melhorCaso.csv", listaOrdenada);

        // Quick Sort - melhor caso
        System.out.println("Método Quick Sort -> Entrada ordenada:");
        ListaEncadeada<CSVRecord> copiaQuickMelhor = cloneLista(listaMelhorCaso);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorQuickSort(copiaQuickMelhor, 0, copiaQuickMelhor.size() - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_quickSort_melhorCaso.csv", listaOrdenada);

        // Quick Sort (Mediana de 3) - melhor caso
        System.out.println("Método Quick Sort (Mediana de 3) -> Entrada ordenada:");
        ListaEncadeada<CSVRecord> copiaQuickMedianaMelhor = cloneLista(listaMelhorCaso);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorQuickSortMediana3(copiaQuickMedianaMelhor, 0, copiaQuickMedianaMelhor.size() - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_quickSort_mediana_de_3_melhorCaso.csv", listaOrdenada);

        // Heap Sort - melhor caso
        System.out.println("Método Heap Sort -> Entrada ordenada:");
        ListaEncadeada<CSVRecord> copiaHeapMelhor = cloneLista(listaMelhorCaso);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorHeap(copiaHeapMelhor);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_heapSort_melhorCaso.csv", listaOrdenada);

        // Prepara o pior caso (entrada em ordem reversa) a partir da lista já ordenada (melhor caso)
        ListaEncadeada<CSVRecord> listaPiorCaso = cloneLista(listaMelhorCaso);
        AlgoritmosDate.inverterLista(listaPiorCaso);

        System.out.println("\nExecutando ordenações no campo Release Date (pior caso - dados ordenados em ordem inversa):\n");

        // Selection Sort - pior caso
        System.out.println("Método Selection Sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorSelection(cloneLista(listaPiorCaso));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_selectionSort_piorCaso.csv", listaOrdenada);

        // Insertion Sort - pior caso
        System.out.println("Método Insertion Sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorInsertion(cloneLista(listaPiorCaso));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_insertionSort_piorCaso.csv", listaOrdenada);

        // Merge Sort - pior caso
        System.out.println("Método Merge Sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorMerge(cloneLista(listaPiorCaso));
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_mergeSort_piorCaso.csv", listaOrdenada);

        // Quick Sort - pior caso
        System.out.println("Método Quick Sort -> Entrada ordenada em ordem inversa:");
        ListaEncadeada<CSVRecord> copiaQuickPior = cloneLista(listaPiorCaso);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorQuickSort(copiaQuickPior, 0, copiaQuickPior.size() - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_quickSort_piorCaso.csv", listaOrdenada);

        // Quick Sort (Mediana de 3) - pior caso
        System.out.println("Método Quick Sort (Mediana de 3) -> Entrada ordenada em ordem inversa:");
        ListaEncadeada<CSVRecord> copiaQuickMedianaPior = cloneLista(listaPiorCaso);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorQuickSortMediana3(copiaQuickMedianaPior, 0, copiaQuickMedianaPior.size() - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_quickSort_mediana_de_3_piorCaso.csv", listaOrdenada);

        // Heap Sort - pior caso
        System.out.println("Método Heap Sort -> Entrada ordenada em ordem inversa:");
        ListaEncadeada<CSVRecord> copiaHeapPior = cloneLista(listaPiorCaso);
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        listaOrdenada = AlgoritmosDate.ordenarPorHeap(copiaHeapPior);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_heapSort_piorCaso.csv", listaOrdenada);
    }

    // Método auxiliar para clonar uma ListaEncadeada de CSVRecord
    private static ListaEncadeada<CSVRecord> cloneLista(ListaEncadeada<CSVRecord> original) {
        ListaEncadeada<CSVRecord> nova = new ListaEncadeada<>();
        for (int i = 0; i < original.size(); i++) {
            nova.add(original.get(i));
        }
        return nova;
    }
}
