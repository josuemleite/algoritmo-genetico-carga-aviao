package br.edu.alunos.ifsuldeminas;

public class Main {
    public static final Double CAPACIDADE_CARGA = 400D;
    public static final Double LARGURA_MAXIMA = 300D;
    public static final Double ALTURA_MAXIMA = 300D;
    public static final Double PROFUNDIDADE_MAXIMA = 400D;

    public static final Integer POPULACAO = 20;
    public static final Integer PROBABILIDADE_MUTACAO = 5;
    public static final Integer QUANTIDADE_CRUZAMENTOS = 5;
    public static final Integer NUMERO_GERACOES = 10;

    public static void main(String[] args) {
        if (!(CAPACIDADE_CARGA > 0 &&
                LARGURA_MAXIMA >= 0 &&
                ALTURA_MAXIMA >= 0 &&
                PROFUNDIDADE_MAXIMA >= 0 &&
                POPULACAO > 0 &&
                PROBABILIDADE_MUTACAO >= 0 &&
                QUANTIDADE_CRUZAMENTOS > 0 &&
                NUMERO_GERACOES > 0
        )) {
            throw new IllegalArgumentException("Valores das constantes inválidos para execução do Algoritmo Genético!");
        }
        AlgoritmoGenetico algoritmoGenetico = new AlgoritmoGenetico();
        algoritmoGenetico.executar("src/main/resources/carga_aviao.csv");
    }

    public static Double volumeMaximo() {
        return (LARGURA_MAXIMA * ALTURA_MAXIMA * PROFUNDIDADE_MAXIMA) / 1_000_000;
    }
}
