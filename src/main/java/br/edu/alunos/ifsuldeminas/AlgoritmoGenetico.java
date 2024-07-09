package br.edu.alunos.ifsuldeminas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlgoritmoGenetico {

    private final List<Carga> cargas = new ArrayList<>();
    private int tamanhoCromossomo = 0;
    private final List<List<Integer>> populacao = new ArrayList<>();
    private final List<Integer> roletaVirtual = new ArrayList<>();

    public void executar(String nomeArquivo) {
        carregarArquivo(nomeArquivo);
        criarPopulacao();
        for (int i = 0; i < Main.NUMERO_GERACOES; i++) {
            System.out.println("Geração: " + i);
            mostrarPopulacao();
            operadoresGeneticos();
            renovarPopulacao();
            System.out.println("--------------------------------------");
        }
        mostrarCargaAviao(populacao.get(obterMelhor()));
    }

    private void carregarArquivo(String nomeArquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            String[] dadosCarga;
            while ((linha = br.readLine()) != null) {
                dadosCarga = linha.split(",");
                adicionarCarga(criarCarga(dadosCarga));
            }
        } catch (IOException e) {
            throw new RuntimeException("Confira o caminho e as permissões do arquivo '" + nomeArquivo + "'!", e);
        }
    }

    private Carga criarCarga(String[] dadosCarga) {
        if (dadosCarga == null || dadosCarga.length < 5) {
            throw new IllegalArgumentException("Dados insuficientes para criação de carga!");
        }
        Carga novaCarga = new Carga();
        novaCarga.setDescricao(dadosCarga[0]);
        novaCarga.setMassa(Double.parseDouble(dadosCarga[1]));
        novaCarga.setLargura(Double.parseDouble(dadosCarga[2]));
        novaCarga.setAltura(Double.parseDouble(dadosCarga[3]));
        novaCarga.setProfundidade(Double.parseDouble(dadosCarga[4]));
        return novaCarga;
    }

    private void adicionarCarga(Carga novaCarga) {
        if (novaCarga == null) {
            throw new NullPointerException("Não há carga a ser adicionada!");
        }
        cargas.add(novaCarga);
        tamanhoCromossomo++;
    }

    private void criarPopulacao() {
        for (int i = 0; i < Main.POPULACAO; i++) {
            populacao.add(criarCromossomo());
        }
    }

    private List<Integer> criarCromossomo() {
        List<Integer> novoCromossomo = new ArrayList<>();
        for (int i = 0; i < tamanhoCromossomo; i++) {
            if (Math.random() < 0.6) {
                novoCromossomo.add(0);
            } else {
                novoCromossomo.add(1);
            }
        }
        return novoCromossomo;
    }

    private void mostrarPopulacao() {
        for (int i = 0; i < Main.POPULACAO; i++) {
            System.out.println("Cromossomo [" + i + "]: " + populacao.get(i));
            System.out.println("> Avaliação: " + fitness(populacao.get(i)));
        }
    }

    private Double fitness(List<Integer> cromossomo) {
        double massaTotal = 0;
        double volumeTotal = 0;
        boolean dimensaoInvalida = false;
        Carga cargaAnalisada;

        for (int i = 0; i < tamanhoCromossomo; i++) {
            if (cromossomo.get(i) == 1) {
                cargaAnalisada = cargas.get(i);
                massaTotal += cargaAnalisada.getMassa();
                volumeTotal += cargaAnalisada.calcularVolume();

                if (cargaAnalisada.getLargura() > Main.LARGURA_MAXIMA
                        || cargaAnalisada.getAltura() > Main.ALTURA_MAXIMA
                        || cargaAnalisada.getProfundidade() > Main.PROFUNDIDADE_MAXIMA) {
                    dimensaoInvalida = true;
                }
            }
        }

        if (massaTotal > Main.CAPACIDADE_CARGA || volumeTotal > Main.volumeMaximo() || dimensaoInvalida) {
            return 0D;
        }

        return volumeTotal;
    }

    private void operadoresGeneticos() {
        List<Integer> primeiroFilho;
        List<Integer> segundoFilho;
        List<List<Integer>> filhos;
        gerarRoleta();
        for (int i = 0; i < Main.QUANTIDADE_CRUZAMENTOS; i++) {
            filhos = cruzamento();
            primeiroFilho = filhos.get(0);
            segundoFilho = filhos.get(1);
            mutacao(primeiroFilho);
            mutacao(segundoFilho);
            populacao.add(primeiroFilho);
            populacao.add(segundoFilho);
        }
    }

    private void gerarRoleta() {
        List<Double> fitnessIndividuos = new ArrayList<>();
        double totalFitness = 0;
        for (int i = 0; i < Main.POPULACAO; i++) {
            fitnessIndividuos.add(i, fitness(populacao.get(i)));
            totalFitness += fitnessIndividuos.get(i);
        }
        System.out.println("Soma total fitness: " + totalFitness);
        for (int i = 0; i < Main.POPULACAO; i++) {
            double qtdPosicoes = (fitnessIndividuos.get(i) / totalFitness) * 1000;
            for (int j = 0; j <= qtdPosicoes; j++) {
                roletaVirtual.add(i);
            }
        }
    }

    private List<List<Integer>> cruzamento() {
        List<Integer> filho1 = new ArrayList<>();
        List<Integer> filho2 = new ArrayList<>();
        List<List<Integer>> filhos = new ArrayList<>();
        List<Integer> pai1, pai2;
        int indice_pai1, indice_pai2;
        indice_pai1 = roleta();
        indice_pai2 = roleta();
        pai1 = populacao.get(indice_pai1);
        pai2 = populacao.get(indice_pai2);
        Random r = new Random();
        int pos = r.nextInt(tamanhoCromossomo);
        for (int i = 0; i <= pos; i++) {
            filho1.add(pai1.get(i));
            filho2.add(pai2.get(i));
        }
        for (int i = pos + 1; i < tamanhoCromossomo; i++) {
            filho1.add(pai2.get(i));
            filho2.add(pai1.get(i));
        }
        filhos.add(filho1);
        filhos.add(filho2);
        return filhos;
    }

    private Integer roleta() {
        Random r = new Random();
        int selecionado = r.nextInt(roletaVirtual.size());
        return roletaVirtual.get(selecionado);
    }

    private void mutacao(List<Integer> filho) {
        Random r = new Random();
        int v = r.nextInt(100);
        if (v < Main.PROBABILIDADE_MUTACAO) {
            int ponto = r.nextInt(tamanhoCromossomo);
            if (filho.get(ponto) == 1) {
                filho.set(ponto, 0);
            } else {
                filho.set(ponto, 1);
            }

            int ponto2 = r.nextInt(tamanhoCromossomo);
            if (filho.get(ponto2) == 1)
                filho.set(ponto2, 0);
            else
                filho.set(ponto2, 1);

            System.out.println("Ocorreu mutação!");
        }
    }

    private void renovarPopulacao() {
        for (int i = 0; i < Main.QUANTIDADE_CRUZAMENTOS; i++) {
            populacao.remove(obterPior());
        }
    }

    private int obterPior() {
        int indicePior = 0;
        double pior;
        double nota;
        pior = fitness(populacao.get(0));
        for (int i = 1; i < Main.POPULACAO; i++) {
            nota = fitness(populacao.get(i));
            if (nota < pior) {
                pior = nota;
                indicePior = i;
            }
        }
        return indicePior;
    }

    private void mostrarCargaAviao(List<Integer> resultado) {
        System.out.println("# Avaliação do melhor: " + fitness(resultado));
        System.out.println("--------------------------------------");
        System.out.println("\n| Cargas levadas no avião:\n");
        Carga cargaPresente;
        double massaTotal = 0;
        double volumeTotal = 0;
        for (int i = 0; i < resultado.size(); i++) {
            int leva = resultado.get(i);
            if (leva == 1) {
                cargaPresente = cargas.get(i);
                System.out.println(cargaPresente);
                massaTotal += cargaPresente.getMassa();
                volumeTotal += cargaPresente.calcularVolume();
            }
        }
        System.out.printf("\n# Massa total: %.2f kg", massaTotal);
        System.out.printf("\n# Volume total: %.2f m³\n", volumeTotal);
    }

    private int obterMelhor() {
        int indiceMelhor = 0;
        double melhor;
        double nota;
        melhor = fitness(populacao.get(0));
        for (int i = 1; i < Main.POPULACAO; i++) {
            nota = fitness(populacao.get(i));
            if (nota > melhor) {
                melhor = nota;
                indiceMelhor = i;
            }
        }
        return indiceMelhor;
    }
}
