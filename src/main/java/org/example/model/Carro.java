package org.example.model;

import org.example.exception.VelocidadeNegativaException;
import org.example.exception.CarroNaoLigadoException;
import org.example.exception.VelocidadeDiferenteDeZeroException;

import java.util.Objects;

public class Carro {
    private String cor;
    private String marca;
    private String modelo;
    private Boolean ligado;
    private Integer velocidadeAtual;
    private Integer velocidadeMaxima;
    private Boolean trancado;

    public Carro(String cor, String marca, String modelo) {
        this.cor = cor;
        this.marca = marca;
        this.modelo = modelo;
        this.trancado = true;
    }

    public Carro() {
        this.velocidadeAtual = 0;
        this.ligado = false;
        this.velocidadeMaxima = 200;
        this.trancado = true;
    }

    public Carro(String cor, String marca, String modelo, Integer velocidadeMaxima) {
        this.cor = cor;
        this.marca = marca;
        this.modelo = modelo;
        this.velocidadeMaxima = velocidadeMaxima;
        this.velocidadeAtual = 0;
        this.ligado = false;
        this.trancado = true;
    }

    public Carro(Integer velocidadeMaxima) {
        this.velocidadeAtual = 0;
        this.ligado = false;
        this.velocidadeMaxima = velocidadeMaxima;
        this.trancado = true;
    }

    public void ligar() {
        this.ligado = true;
    }

    public void trancar() {
        if(trancado == true) {
            System.out.println("Ja esta trancado!");
        } else {
            trancado = true;
        }
    }

    public void destrancar() {
        if(trancado == false) {
            System.out.println("Ja esta destrancado!");
        } else {
            trancado = false;
        }
    }

    public void desligar() throws VelocidadeDiferenteDeZeroException {
        if (this.velocidadeAtual != 0) {
            throw new VelocidadeDiferenteDeZeroException("Para desligar o carro deve está parado!");
        } else {
            this.ligado = false;
        }
    }

    public void acelerar(Integer velocidade) throws CarroNaoLigadoException, VelocidadeNegativaException {
        if (Boolean.FALSE.equals(ligado)) {
            // throw new Exception("Não é possível acelerar com o carro desligado!");
            throw new CarroNaoLigadoException("Não é possível acelerar com o carro desligado!");
        }

        if(velocidade < 0) {
            // Exception - desvio de uma regra ou de um padrão convencionalmente aceito.
            // throw new Exception("A aceleracao não pode ser menor que zero!");
            throw new VelocidadeNegativaException("A aceleracao não pode ser menor que zero!");
        }

        if(this.velocidadeMaxima <= velocidade + velocidadeAtual) {
            this.velocidadeAtual = this.velocidadeMaxima;
        } else {
            this.velocidadeAtual = velocidade + velocidadeAtual;
        }
    }

    public void frear(Integer velocidade) throws CarroNaoLigadoException, VelocidadeNegativaException {
        if (Boolean.FALSE.equals(ligado)) {
            // throw new Exception("Não é possível acelerar com o carro desligado!");
            throw new CarroNaoLigadoException("Não é possível frear com o carro desligado!");
        }

        if(velocidade < 0) {
            // Exception - desvio de uma regra ou de um padrão convencionalmente aceito.
            // throw new Exception("A aceleracao não pode ser menor que zero!");
            throw new VelocidadeNegativaException("A frenagem não pode ser menor que zero!");
        }

        /*if(this.velocidadeAtual-velocidade <= 0) {
            this.velocidadeAtual = 0;
        } else {
            this.velocidadeAtual = this.velocidadeAtual - velocidade;
        }*/
        this.velocidadeAtual = Math.max(this.velocidadeAtual - velocidade, 0);
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Boolean getLigado() {
        return ligado;
    }

    public Integer getVelocidadeAtual() {
        return velocidadeAtual;
    }

    public Integer getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    public void setVelocidadeMaxima(Integer velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    public Boolean getTrancado() {
        return trancado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Carro carro)) return false;

        /*Carro carro = (Carro) o;

        return Objects.equals(marca, carro.marca) && Objects.equals(modelo, carro.modelo);*/

        return Objects.equals(marca, carro.marca) && Objects.equals(modelo, carro.modelo);
    }

    @Override
    public String toString() {
        return "Carro{" +
                "cor='" + cor + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ligado=" + ligado +
                ", velocidadeAtual=" + velocidadeAtual +
                ", velocidadeMaxima=" + velocidadeMaxima +
                ", trancado=" + trancado +
                '}';
    }
}
