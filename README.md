# Aplicação de Algoritmos Genéticos em Problemas de Otimização

## Índice

* [Introdução](#introdução)
* [Objetivo](#objetivo)
* [Modelagem do Problema](#modelagem-do-problema)
* [Implementação](#implementação)
* [Tecnologias Necessárias](#tecnologias-necessárias)
* [Acesso ao Projeto](#acesso-ao-projeto)
* [Autor](#autor)

## Introdução

Neste trabalho, aplicamos os conhecimentos sobre algoritmos genéticos para resolver um problema prático de otimização, especificamente focado no planejamento de carga em aviões. Através da adaptação de algoritmos genéticos, buscamos maximizar a utilização do espaço disponível e balancear o peso da carga no avião, respeitando todas as restrições impostas.

---

## Objetivo

O objetivo deste trabalho é otimizar a distribuição de carga em um avião, maximizando a utilização do espaço disponível e garantindo o balanceamento adequado do peso. Para isso, utilizamos algoritmos genéticos, adaptando-os para considerar as especificidades do cenário de carga aérea.

---

## Modelagem do Problema

### Parâmetros do Problema

Para resolver o problema de planejamento de carga em aviões, consideramos os seguintes parâmetros:

* Capacidade de carga (CAPACIDADE_CARGA)
* Dimensões máximas da área de carga (LARGURA_MAXIMA, ALTURA_MAXIMA, PROFUNDIDADE_MAXIMA)
* Dados dos itens de carga: descrição, massa (peso), largura, altura, profundidade

### Representação dos Cromossomos

Os cromossomos representam uma possível solução para o problema, onde cada gene corresponde a um item de carga e seu valor indica se o item está incluído na solução ou não.

### Função de Avaliação (Fitness)

A função de avaliação deve considerar tanto o peso quanto as dimensões dos itens de carga, penalizando soluções que excedam as restrições. A avaliação é feita com base no volume de carga que se consegue carregar, buscando maximizar este volume dentro dos limites permitidos.

---

## Implementação

### Adaptação do Algoritmo

Para adaptar o algoritmo genético ao problema de planejamento de carga em aviões, realizamos as seguintes modificações:

* Implementação da função de avaliação que considera peso e dimensões.
* Leitura dos dados de entrada a partir de um arquivo CSV.
* Configuração dos parâmetros do problema (CAPACIDADE_CARGA, LARGURA_MAXIMA, ALTURA_MAXIMA, PROFUNDIDADE_MAXIMA).

### Exemplo de Dados de Entrada

Arquivo CSV com os itens de carga:

```
descricao,massa,largura,altura,profundidade
Caixa 1,17.36,190,250,120
Caixa 2,2.26,310,99,81
Caixa 3,4.19,22,13,31
```

### Exemplos de Utilização

Abaixo estão exemplos do funcionamento da aplicação:

#### Inicialização da População

![Imagem 1](https://github.com/josuemleite/literalura/assets/84863364/32e1e4c2-d8b0-47e4-802f-6f1e53c1a7e8)

Esta imagem mostra a geração inicial (Geração 0) da população de cromossomos e suas avaliações.

#### Evolução após 10 Gerações

![Imagem 2](https://github.com/josuemleite/literalura/assets/84863364/93cbebd9-9055-4812-828e-e9f8c3272094)

Esta imagem apresenta a população de cromossomos e suas avaliações após 10 gerações, incluindo a soma total do fitness.

#### Cargas Selecionadas para Transporte

![Imagem 3](https://github.com/josuemleite/forumhub/assets/84863364/04951092-b255-45ec-9ca9-5abb3f2ec01e)

Esta imagem detalha as cargas que foram selecionadas para serem levadas no avião, com informações sobre peso e volume totais.

---

## Tecnologias Necessárias

* **Java 8+**

---

## Acesso ao Projeto

Para acessar o projeto, basta cloná-lo em uma máquina com o `git` ou baixá-lo diretamente pelo GitHub. Em seguida, execute a aplicação em uma IDE Java de sua preferência.

---

## Autor

<a href="https://github.com/josuemleite/" target="_blank">
    <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/84863364?v=4" width="100px;" alt=""/>
    <br />
    <sub><b>Josué Melquisedeque Leite</b> 🚀</sub>
</a>

<br />
<br />

<a href="mailto:josuemelquileite@gmail.com"><img src="https://img.shields.io/badge/-Gmail-%23333?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>
<a href="https://www.linkedin.com/in/josuemleite/" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>
