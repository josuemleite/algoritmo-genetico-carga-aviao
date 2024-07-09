package br.edu.alunos.ifsuldeminas;

public class Carga {
    private String descricao;
    private Double massa;
    private Double largura;
    private Double altura;
    private Double profundidade;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getMassa() {
        return massa;
    }

    public void setMassa(Double massa) {
        this.massa = massa;
    }

    public Double getLargura() {
        return largura;
    }

    public void setLargura(Double largura) {
        this.largura = largura;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(Double profundidade) {
        this.profundidade = profundidade;
    }

    public Double calcularVolume() {
        return (this.largura * this.altura * this.profundidade) / 1_000_000;
    }

    @Override
    public String toString() {
        return getDescricao() + ": " +
                getMassa() + " kg" + ", " +
                calcularVolume() + " m³" + " " +
                "(" + getLargura() + " cm, " + getAltura() + " cm, " + getProfundidade() + " cm)";
    }
}
