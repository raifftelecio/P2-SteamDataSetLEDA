package com.steamdatasetprojetoleda.ordenações;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.steamdatasetprojetoleda.estruturas.ListaDuplamenteEncadeada;

public class AlgoritmosPrice {

    // Ordenação por Selection Sort – campo Price (índice 6)
    public static ListaDuplamenteEncadeada<CSVRecord> ordenarPorSelection(ListaDuplamenteEncadeada<CSVRecord> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            int indiceMinimo = i;
            for (int j = i + 1; j < n; j++) {
                double priceMin = Double.parseDouble(lista.get(indiceMinimo).get(6));
                double priceAtj = Double.parseDouble(lista.get(j).get(6));
                if (priceMin > priceAtj) {
                    indiceMinimo = j;
                }
            }
            lista.swap(i, indiceMinimo);
        }
        return lista;
    }

    // Ordenação por Insertion Sort – campo Price (índice 6)
    public static ListaDuplamenteEncadeada<CSVRecord> ordenarPorInsertion(ListaDuplamenteEncadeada<CSVRecord> lista) {
        int n = lista.size();
        for (int i = 1; i < n; i++) {
            CSVRecord atual = lista.get(i);
            double precoAtual = Double.parseDouble(atual.get(6));
            int j = i - 1;
            while (j >= 0 && Double.parseDouble(lista.get(j).get(6)) > precoAtual) {
                lista.set(j + 1, lista.get(j));
                j--;
            }
            lista.set(j + 1, atual);
        }
        return lista;
    }

    // Ordenação por Merge Sort – campo Price
    public static ListaDuplamenteEncadeada<CSVRecord> ordenarPorMerge(ListaDuplamenteEncadeada<CSVRecord> lista) {
        int n = lista.size();
        if (n <= 1)
            return lista;

        int meio = n / 2;
        ListaDuplamenteEncadeada<CSVRecord> esquerda = new ListaDuplamenteEncadeada<>();
        ListaDuplamenteEncadeada<CSVRecord> direita = new ListaDuplamenteEncadeada<>();

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

    // Combina os subvetores (esquerda e direita) em um único vetor (destino).
    private static void combinar(ListaDuplamenteEncadeada<CSVRecord> destino,
                                  ListaDuplamenteEncadeada<CSVRecord> esquerda,
                                  ListaDuplamenteEncadeada<CSVRecord> direita) {
        int i = 0, j = 0, k = 0;
        int nEsq = esquerda.size();
        int nDir = direita.size();

        while (i < nEsq && j < nDir) {
            double precoEsq = Double.parseDouble(esquerda.get(i).get(6));
            double precoDir = Double.parseDouble(direita.get(j).get(6));
            if (precoEsq <= precoDir) {
                destino.set(k++, esquerda.get(i++));
            } else {
                destino.set(k++, direita.get(j++));
            }
        }
        while (i < nEsq) {
            destino.set(k++, esquerda.get(i++));
        }
        while (j < nDir) {
            destino.set(k++, direita.get(j++));
        }
    }

    // Ordenação por Quick Sort – campo Price
    public static ListaDuplamenteEncadeada<CSVRecord> ordenarPorQuickSort(ListaDuplamenteEncadeada<CSVRecord> lista, int inicio,
                                                                            int fim) {
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

    // Realiza a partição (divisão) para o Quick Sort.
    private static int dividir(ListaDuplamenteEncadeada<CSVRecord> lista, int inicio, int fim) {
        double pivo = Double.parseDouble(lista.get(fim).get(6));
        int limite = inicio - 1;
        for (int atual = inicio; atual < fim; atual++) {
            double precoAtual = Double.parseDouble(lista.get(atual).get(6));
            if (precoAtual <= pivo) {
                limite++;
                lista.swap(limite, atual);
            }
        }
        lista.swap(limite + 1, fim);
        return limite + 1;
    }

    // Ordenação por Quick Sort com Mediana de 3 – campo Price
    public static ListaDuplamenteEncadeada<CSVRecord> ordenarPorQuickSortMediana3(ListaDuplamenteEncadeada<CSVRecord> lista,
                                                                                   int ini, int fim) {
        while (ini < fim) {
            int posPivo = dividirMediana(lista, ini, fim);
            if (posPivo - ini < fim - posPivo) {
                ordenarPorQuickSortMediana3(lista, ini, posPivo - 1);
                ini = posPivo + 1;
            } else {
                ordenarPorQuickSortMediana3(lista, posPivo + 1, fim);
                fim = posPivo - 1;
            }
        }
        return lista;
    }

    // Realiza a partição utilizando a mediana de três.
    private static int dividirMediana(ListaDuplamenteEncadeada<CSVRecord> lista, int ini, int fim) {
        int meio = (ini + fim) / 2;
        int mediana = encontrarMediana(lista, ini, meio, fim);

        lista.swap(mediana, fim);

        double pivo = Double.parseDouble(lista.get(fim).get(6));
        int limite = ini - 1;
        for (int i = ini; i < fim; i++) {
            double precoAtual = Double.parseDouble(lista.get(i).get(6));
            if (precoAtual <= pivo) {
                limite++;
                lista.swap(limite, i);
            }
        }
        lista.swap(limite + 1, fim);
        return limite + 1;
    }

    // Retorna o índice do elemento que é a mediana entre os índices ini, meio e fim.
    private static int encontrarMediana(ListaDuplamenteEncadeada<CSVRecord> lista, int ini, int meio, int fim) {
        double precoIni = Double.parseDouble(lista.get(ini).get(6));
        double precoMeio = Double.parseDouble(lista.get(meio).get(6));
        double precoFim = Double.parseDouble(lista.get(fim).get(6));

        if ((precoIni < precoMeio && precoMeio < precoFim) || (precoFim < precoMeio && precoMeio < precoIni))
            return meio;
        else if ((precoMeio < precoIni && precoIni < precoFim) || (precoFim < precoIni && precoIni < precoMeio))
            return ini;
        else
            return fim;
    }

    // Ordenação por Heap Sort – campo Price
    public static ListaDuplamenteEncadeada<CSVRecord> ordenarPorHeap(ListaDuplamenteEncadeada<CSVRecord> lista) {
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

    // Ajusta o heap para manter a propriedade de Heap.
    private static void ajustarHeap(ListaDuplamenteEncadeada<CSVRecord> lista, int tamanho, int raiz) {
        int maior = raiz;
        int esquerda = 2 * raiz + 1;
        int direita = 2 * raiz + 2;

        if (esquerda < tamanho && Double.parseDouble(lista.get(esquerda).get(6))
                > Double.parseDouble(lista.get(maior).get(6))) {
            maior = esquerda;
        }
        if (direita < tamanho && Double.parseDouble(lista.get(direita).get(6))
                > Double.parseDouble(lista.get(maior).get(6))) {
            maior = direita;
        }
        if (maior != raiz) {
            lista.swap(raiz, maior);
            ajustarHeap(lista, tamanho, maior);
        }
    }

    // Carrega os registros do CSV em uma ListaDuplamenteEncadeada.
    public static ListaDuplamenteEncadeada<CSVRecord> carregarLista(Path caminhoCSV) throws IOException {
        ListaDuplamenteEncadeada<CSVRecord> lista = new ListaDuplamenteEncadeada<>();
        try (
            Reader leitor = new FileReader(caminhoCSV.toFile());
            CSVParser parser = new CSVParser(leitor, CSVFormat.DEFAULT.withHeader())
        ) {
            for (CSVRecord r : parser) {
                lista.add(r);
            }
        }
        return lista;
    }

    // Salva os registros de uma ListaDuplamenteEncadeada em arquivo CSV.
    public static void salvarCSV(String nomeArquivo, ListaDuplamenteEncadeada<CSVRecord> lista) throws IOException {
        Path caminhoSaida = Paths.get("src", "test", "java", "com", "steamdatasetprojetoleda");
        File arquivoSaida = new File(caminhoSaida.toFile(), nomeArquivo);
        File arquivoCabecalho = new File(caminhoSaida.toFile(), "games_formated_release_data.csv");

        try (
            Writer escritor = new FileWriter(arquivoSaida);
            CSVPrinter printer = new CSVPrinter(escritor, CSVFormat.DEFAULT);
            Reader leitorCabecalho = new FileReader(arquivoCabecalho);
            CSVParser parserCabecalho = new CSVParser(leitorCabecalho, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        ) {
            printer.printRecord(parserCabecalho.getHeaderMap().keySet());
            for (int i = 0; i < lista.size(); i++) {
                printer.printRecord(lista.get(i));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Inverte a ordem dos registros na ListaDuplamenteEncadeada.
    public static void inverterLista(ListaDuplamenteEncadeada<CSVRecord> lista) {
        int inicio = 0;
        int fim = lista.size() - 1;
        while (inicio < fim) {
            lista.swap(inicio, fim);
            inicio++;
            fim--;
        }
    }
}
