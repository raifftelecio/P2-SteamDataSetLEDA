package com.steamdatasetprojetoleda.ordenações;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.steamdatasetprojetoleda.estruturas.ListaEncadeada;

public class AlgoritmosDate {
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Ordenação por Selection Sort utilizando a ListaEncadeada
    public static ListaEncadeada<CSVRecord> ordenarPorSelection(ListaEncadeada<CSVRecord> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            int indiceMinimo = i;
            for (int j = i + 1; j < n; j++) {
                LocalDate dataAtual = LocalDate.parse(lista.get(j).get(2), FORMATO_DATA);
                LocalDate dataMinima = LocalDate.parse(lista.get(indiceMinimo).get(2), FORMATO_DATA);
                if (dataAtual.isBefore(dataMinima)) {
                    indiceMinimo = j;
                }
            }
            lista.swap(i, indiceMinimo);
        }
        return lista;
    }

    // Ordenação por Insertion Sort utilizando a ListaEncadeada
    public static ListaEncadeada<CSVRecord> ordenarPorInsertion(ListaEncadeada<CSVRecord> lista) {
        int n = lista.size();
        for (int i = 1; i < n; i++) {
            CSVRecord atual = lista.get(i);
            LocalDate dataAtual = LocalDate.parse(atual.get(2), FORMATO_DATA);
            int j = i - 1;
            while (j >= 0 && LocalDate.parse(lista.get(j).get(2), FORMATO_DATA).isAfter(dataAtual)) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, atual);
        }
        return lista;
    }

    // Ordenação por Merge Sort utilizando a ListaEncadeada
    public static ListaEncadeada<CSVRecord> ordenarPorMerge(ListaEncadeada<CSVRecord> lista) {
        int n = lista.size();
        if (n <= 1) return lista;
        int meio = n / 2;

        ListaEncadeada<CSVRecord> esquerda = new ListaEncadeada<>();
        ListaEncadeada<CSVRecord> direita = new ListaEncadeada<>();

        for (int i = 0; i < meio; i++) {
            esquerda.add(lista.get(i));
        }
        for (int i = meio; i < n; i++) {
            direita.add(lista.get(i));
        }

        ordenarPorMerge(esquerda);
        ordenarPorMerge(direita);
        combinar(lista, esquerda, direita);
        return lista;
    }

    private static void combinar(ListaEncadeada<CSVRecord> destino, ListaEncadeada<CSVRecord> esquerda, ListaEncadeada<CSVRecord> direita) {
        int i = 0, j = 0, k = 0;
        while (i < esquerda.size() && j < direita.size()) {
            LocalDate dataEsquerda = LocalDate.parse(esquerda.get(i).get(2), FORMATO_DATA);
            LocalDate dataDireita = LocalDate.parse(direita.get(j).get(2), FORMATO_DATA);
            if (dataEsquerda.isBefore(dataDireita) || dataEsquerda.equals(dataDireita)) {
                destino.set(k++, esquerda.get(i++));
            } else {
                destino.set(k++, direita.get(j++));
            }
        }
        while (i < esquerda.size()) {
            destino.set(k++, esquerda.get(i++));
        }
        while (j < direita.size()) {
            destino.set(k++, direita.get(j++));
        }
    }

    // Ordenação por Quick Sort utilizando a ListaEncadeada
    public static ListaEncadeada<CSVRecord> ordenarPorQuickSort(ListaEncadeada<CSVRecord> lista, int inicio, int fim) {
        while (inicio < fim) {
            int posPivo = dividir(lista, inicio, fim);
            if (posPivo - inicio < fim - posPivo) {
                ordenarPorQuickSort(lista, inicio, posPivo - 1);
                inicio = posPivo + 1;
            } else {
                ordenarPorQuickSort(lista, posPivo + 1, fim);
                fim = posPivo - 1;
            }
        }
        return lista;
    }

    private static int dividir(ListaEncadeada<CSVRecord> lista, int inicio, int fim) {
        LocalDate pivo = LocalDate.parse(lista.get(fim).get(2), FORMATO_DATA);
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            LocalDate dataAtual = LocalDate.parse(lista.get(j).get(2), FORMATO_DATA);
            if (!dataAtual.isAfter(pivo)) {
                i++;
                lista.swap(i, j);
            }
        }
        lista.swap(i + 1, fim);
        return i + 1;
    }

    // Ordenação por Quick Sort com Mediana de 3 utilizando a ListaEncadeada
    public static ListaEncadeada<CSVRecord> ordenarPorQuickSortMediana3(ListaEncadeada<CSVRecord> lista, int inicio, int fim) {
        if (inicio < fim) {
            int pivo = dividirMediana(lista, inicio, fim);
            ordenarPorQuickSortMediana3(lista, inicio, pivo - 1);
            ordenarPorQuickSortMediana3(lista, pivo + 1, fim);
        }
        return lista;
    }

    private static int dividirMediana(ListaEncadeada<CSVRecord> lista, int inicio, int fim) {
        int meio = (inicio + fim) / 2;
        int indiceMediana = encontrarMediana(lista, inicio, meio, fim);

        lista.swap(indiceMediana, fim);

        LocalDate pivo = LocalDate.parse(lista.get(fim).get(2), FORMATO_DATA);
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            LocalDate atual = LocalDate.parse(lista.get(j).get(2), FORMATO_DATA);
            if (!atual.isAfter(pivo)) {
                i++;
                lista.swap(i, j);
            }
        }
        lista.swap(i + 1, fim);
        return i + 1;
    }

    private static int encontrarMediana(ListaEncadeada<CSVRecord> lista, int i, int j, int k) {
        LocalDate a = LocalDate.parse(lista.get(i).get(2), FORMATO_DATA);
        LocalDate b = LocalDate.parse(lista.get(j).get(2), FORMATO_DATA);
        LocalDate c = LocalDate.parse(lista.get(k).get(2), FORMATO_DATA);

        if ((a.isBefore(b) && b.isBefore(c)) || (c.isBefore(b) && b.isBefore(a)))
            return j;
        if ((b.isBefore(a) && a.isBefore(c)) || (c.isBefore(a) && a.isBefore(b)))
            return i;
        return k;
    }

    // Ordenação por HeapSort utilizando a ListaEncadeada
    public static ListaEncadeada<CSVRecord> ordenarPorHeap(ListaEncadeada<CSVRecord> lista) {
        int n = lista.size();
        for (int i = n / 2 - 1; i >= 0; i--) {
            ajustarHeap(lista, n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            lista.swap(0, i);
            ajustarHeap(lista, i, 0);
        }
        return lista;
    }

    private static void ajustarHeap(ListaEncadeada<CSVRecord> lista, int n, int i) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        if (esq < n && LocalDate.parse(lista.get(esq).get(2), FORMATO_DATA)
                .isAfter(LocalDate.parse(lista.get(maior).get(2), FORMATO_DATA))) {
            maior = esq;
        }

        if (dir < n && LocalDate.parse(lista.get(dir).get(2), FORMATO_DATA)
                .isAfter(LocalDate.parse(lista.get(maior).get(2), FORMATO_DATA))) {
            maior = dir;
        }

        if (maior != i) {
            lista.swap(i, maior);
            ajustarHeap(lista, n, maior);
        }
    }

    // Método para carregar os registros do CSV em uma ListaEncadeada
    public static ListaEncadeada<CSVRecord> carregarLista(Path caminhoCSV) throws IOException {
        ListaEncadeada<CSVRecord> lista = new ListaEncadeada<>();
        try (
            Reader leitor = new FileReader(caminhoCSV.toFile());
            CSVParser parser = new CSVParser(leitor, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        ) {
            for (CSVRecord r : parser) {
                lista.add(r);
            }
        }
        return lista;
    }

    // Método para salvar os registros de uma ListaEncadeada em arquivo CSV
    public static void salvarCSV(String nomeArquivo, ListaEncadeada<CSVRecord> lista) throws IOException {
        Path caminhoSaida = Paths.get("src", "test", "java", "com", "steamdatasetprojetoleda");
        File arquivoSaida = new File(caminhoSaida.toFile(), nomeArquivo);
        File cabecalhoOrigem = new File(caminhoSaida.toFile(), "games_formated_release_data.csv");

        try (
            Writer escritor = new FileWriter(arquivoSaida);
            CSVPrinter printer = new CSVPrinter(escritor, CSVFormat.DEFAULT);
            Reader leitorCabecalho = new FileReader(cabecalhoOrigem);
            CSVParser parserCabecalho = new CSVParser(leitorCabecalho, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        ) {
            printer.printRecord(parserCabecalho.getHeaderMap().keySet());
            for (int i = 0; i < lista.size(); i++) {
                printer.printRecord(lista.get(i));
            }
        }
    }

    // Método para inverter a ordem dos registros na ListaEncadeada
    public static void inverterLista(ListaEncadeada<CSVRecord> lista) {
        int inicio = 0;
        int fim = lista.size() - 1;
        while (inicio < fim) {
            lista.swap(inicio, fim);
            inicio++;
            fim--;
        }
    }
}
