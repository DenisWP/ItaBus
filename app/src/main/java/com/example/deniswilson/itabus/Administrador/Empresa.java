package com.example.deniswilson.itabus.Administrador;

import java.io.Serializable;

/**
 * Created by Denis Wilson on 22/10/2016.
 */

public class Empresa implements Serializable {

    private long id;
    private String codigo;
    private String nome;
    private String sigla;
    private String contato;

    public Empresa(){
        id = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    /*Método para filtrar. Classe filtrarEmpresa*/
    public String toString(){
        return nome +" - "+ codigo ;
    }
}
