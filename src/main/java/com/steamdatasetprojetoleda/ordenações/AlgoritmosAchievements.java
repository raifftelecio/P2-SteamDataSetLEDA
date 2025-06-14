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

import com.steamdatasetprojetoleda.estruturas.PriorityQueueAchievements;

public class AlgoritmosAchievements {

    /**
     * Ordena os registros utilizando a fila de prioridade.
     * Cada registro é inserido na PriorityQueueAchievements, que garante que o registro
     * com maior número de conquistas (campo 26) tenha a maior prioridade.
     * Ao remover os elementos, obtemos um array em ordem decrescente.
     */
    public static CSVRecord[] ordenarPorPriorityQueue(CSVRecord[] registros) {
        PriorityQueueAchievements pq = new PriorityQueueAchievements(registros.length);
        for (CSVRecord registro : registros) {
            pq.inserir(registro);
        }
        CSVRecord[] ordenado = new CSVRecord[registros.length];
        int i = 0;
        // Removendo todos os elementos – eles saem em ordem decrescente
        while (!pq.isEmpty()) {
            ordenado[i++] = pq.remover();
        }
        return ordenado;
    }
    
    
    public static CSVRecord[] ordenarPorSelection(CSVRecord[] registros) {
        for (int i = 0; i < registros.length - 1; i++) {
            int indiceMaior = i;
            for (int j = i + 1; j < registros.length; j++) {
                if (Integer.parseInt(registros[indiceMaior].get(26)) < Integer.parseInt(registros[j].get(26))) {
                    indiceMaior = j;
                }
            }
            CSVRecord aux = registros[indiceMaior];
            registros[indiceMaior] = registros[i];
            registros[i] = aux;
        }
        return registros;
    }

    public static CSVRecord[] ordenarPorInsertion(CSVRecord[] registros) {
        for (int i = 1; i < registros.length; i++) {
            CSVRecord atual = registros[i];
            int j = i - 1;
            int valorAtual = Integer.parseInt(atual.get(26));
            while (j >= 0 && Integer.parseInt(registros[j].get(26)) < valorAtual) {
                registros[j + 1] = registros[j];
                j--;
            }
            registros[j + 1] = atual;
        }
        return registros;
    }

    public static CSVRecord[] ordenarPorMerge(CSVRecord[] registros) {
        if (registros.length <= 1)
            return registros;

        int meio = registros.length / 2;
        CSVRecord[] esquerda = new CSVRecord[meio];
        CSVRecord[] direita = new CSVRecord[registros.length - meio];

        System.arraycopy(registros, 0, esquerda, 0, meio);
        System.arraycopy(registros, meio, direita, 0, registros.length - meio);

        ordenarPorMerge(esquerda);
        ordenarPorMerge(direita);
        combinar(registros, esquerda, direita);
        return registros;
    }

    private static void combinar(CSVRecord[] registros, CSVRecord[] esquerda, CSVRecord[] direita) {
        int i = 0, j = 0, k = 0;
        while (i < esquerda.length && j < direita.length) {
            int conquistasEsq = Integer.parseInt(esquerda[i].get(26));
            int conquistasDir = Integer.parseInt(direita[j].get(26));
            if (conquistasEsq >= conquistasDir) {
                registros[k++] = esquerda[i++];
            } else {
                registros[k++] = direita[j++];
            }
        }
        while (i < esquerda.length)
            registros[k++] = esquerda[i++];
        while (j < direita.length)
            registros[k++] = direita[j++];
    }

    public static CSVRecord[] ordenarPorQuickSort(CSVRecord[] registros, int inicio, int fim) {
        while (inicio < fim) {
            int posPivo = dividir(registros, inicio, fim);
            if (posPivo - inicio < fim - posPivo) {
                ordenarPorQuickSort(registros, inicio, posPivo - 1);
                inicio = posPivo + 1;
            } else {
                ordenarPorQuickSort(registros, posPivo + 1, fim);
                fim = posPivo - 1;
            }
        }
        return registros;
    }

    public static int dividir(CSVRecord[] registros, int inicio, int fim) {
        int pivo = Integer.parseInt(registros[fim].get(26));
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (Integer.parseInt(registros[j].get(26)) >= pivo) {
                i++;
                CSVRecord temp = registros[i];
                registros[i] = registros[j];
                registros[j] = temp;
            }
        }
        CSVRecord temp = registros[i + 1];
        registros[i + 1] = registros[fim];
        registros[fim] = temp;
        return i + 1;
    }

    public static CSVRecord[] ordenarPorQuickSortMediana3(CSVRecord[] registros, int inicio, int fim) {
        while (inicio < fim) {
            int posPivo = dividirMediana(registros, inicio, fim);
            if (posPivo - inicio < fim - posPivo) {
                ordenarPorQuickSortMediana3(registros, inicio, posPivo - 1);
                inicio = posPivo + 1;
            } else {
                ordenarPorQuickSortMediana3(registros, posPivo + 1, fim);
                fim = posPivo - 1;
            }
        }
        return registros;
    }

    public static int dividirMediana(CSVRecord[] registros, int inicio, int fim) {
        int meio = (inicio + fim) / 2;
        int indiceMediana = encontrarMediana(registros, inicio, meio, fim);

        CSVRecord temp = registros[indiceMediana];
        registros[indiceMediana] = registros[fim];
        registros[fim] = temp;

        double pivo = Double.parseDouble(registros[fim].get(26));
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            double valorAtual = Double.parseDouble(registros[j].get(26));
            if (valorAtual >= pivo) {
                i++;
                CSVRecord aux = registros[i];
                registros[i] = registros[j];
                registros[j] = aux;
            }
        }
        CSVRecord aux = registros[i + 1];
        registros[i + 1] = registros[fim];
        registros[fim] = aux;
        return i + 1;
    }

    public static int encontrarMediana(CSVRecord[] registros, int primeiro, int meio, int ultimo) {
        double valor1 = Double.parseDouble(registros[primeiro].get(26));
        double valor2 = Double.parseDouble(registros[meio].get(26));
        double valor3 = Double.parseDouble(registros[ultimo].get(26));
        if ((valor1 < valor2 && valor2 < valor3) || (valor3 < valor2 && valor2 < valor1))
            return meio;
        if ((valor2 < valor1 && valor1 < valor3) || (valor3 < valor1 && valor1 < valor2))
            return primeiro;
        return ultimo;
    }

    public static CSVRecord[] ordenarPorHeap(CSVRecord[] registros) {
        int tamanho = registros.length;
        for (int i = tamanho / 2 - 1; i >= 0; i--) {
            ajustarHeap(registros, tamanho, i);
        }
        for (int i = tamanho - 1; i > 0; i--) {
            CSVRecord temp = registros[0];
            registros[0] = registros[i];
            registros[i] = temp;
            ajustarHeap(registros, i, 0);
        }
        return registros;
    }

    private static void ajustarHeap(CSVRecord[] registros, int tamanho, int raiz) {
        int maior = raiz;
        int esquerda = 2 * raiz + 1;
        int direita = 2 * raiz + 2;
        if (esquerda < tamanho && Integer.parseInt(registros[esquerda].get(26)) < Integer.parseInt(registros[maior].get(26))) {
            maior = esquerda;
        }
        if (direita < tamanho && Integer.parseInt(registros[direita].get(26)) < Integer.parseInt(registros[maior].get(26))) {
            maior = direita;
        }
        if (maior != raiz) {
            CSVRecord temp = registros[raiz];
            registros[raiz] = registros[maior];
            registros[maior] = temp;
            ajustarHeap(registros, tamanho, maior);
        }
    }

    public static CSVRecord[] ordenarPorCouting(CSVRecord[] registros) {
        int tamanho = registros.length;
        int maiorValor = Integer.parseInt(registros[0].get(26));
        for (int i = 1; i < tamanho; i++) {
            int valor = Integer.parseInt(registros[i].get(26));
            if (valor > maiorValor)
                maiorValor = valor;
        }
        int[] contagem = new int[maiorValor + 1];
        for (CSVRecord registro : registros) {
            contagem[Integer.parseInt(registro.get(26))]++;
        }
        for (int i = maiorValor - 1; i >= 0; i--) {
            contagem[i] += contagem[i + 1];
        }
        CSVRecord[] resultado = new CSVRecord[tamanho];
        for (int i = tamanho - 1; i >= 0; i--) {
            int valor = Integer.parseInt(registros[i].get(26));
            resultado[contagem[valor] - 1] = registros[i];
            contagem[valor]--;
        }
        return resultado;
    }

        public static CSVRecord[] carregarArray(Path caminhoArquivo) throws IOException {
        try (Reader leitor = new FileReader(caminhoArquivo.toFile());
             CSVParser analisador = new CSVParser(leitor, CSVFormat.DEFAULT.withHeader())) {

            int tamanho = 0;
            for (CSVRecord ignorado : analisador.getRecords()) {
                tamanho++;
            }
            analisador.close();
            leitor.close();

            Reader leitorNovamente = new FileReader(caminhoArquivo.toFile());
            CSVParser analisadorNovamente = new CSVParser(leitorNovamente, CSVFormat.DEFAULT.withHeader());
            CSVRecord[] array = new CSVRecord[tamanho];
            int i = 0;
            for (CSVRecord registro : analisadorNovamente) {
                array[i++] = registro;
            }
            analisadorNovamente.close();
            leitorNovamente.close();
            return array;
        }
    }

    public static void salvarCSV(String nomeArquivo, CSVRecord[] registros) throws IOException {
        Path caminhoEntrada = Paths.get("src", "test", "java", "com", "steamdatasetprojetoleda");
        File arquivoSaida = new File(caminhoEntrada.toString(), nomeArquivo);
        File arquivoCabecalho = new File(caminhoEntrada.toString(), "games_formated_release_data.csv");

        try (Writer escritor = new FileWriter(arquivoSaida);
             CSVPrinter impressora = new CSVPrinter(escritor, CSVFormat.DEFAULT);
             Reader leitorCabecalho = new FileReader(arquivoCabecalho);
             CSVParser parserCabecalho = new CSVParser(leitorCabecalho, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            impressora.printRecord(parserCabecalho.getHeaderMap().keySet());
            for (CSVRecord registro : registros) {
                impressora.printRecord(registro);
            }
        }
    }

    public static void inverterArray(CSVRecord[] registros) {
        int inicio = 0;
        int fim = registros.length - 1;
        while (inicio < fim) {
            CSVRecord aux = registros[inicio];
            registros[inicio] = registros[fim];
            registros[fim] = aux;
            inicio++;
            fim--;
        }
    }
}
