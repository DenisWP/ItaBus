package com.example.deniswilson.itabus.Administrador;

import java.io.Serializable;

/**
 * Created by Denis Wilson on 23/10/2016.
 */

public class Horarios implements Serializable {

    private long id;
    private String codigo;
    private String segunda;
    private String terça;
    private String quarta;
    private String quinta;
    private String sexta;
    private String sabado;
    private String domingo;

    public Horarios(){
        setId(0);
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

    public String getSegunda() {
        return segunda;
    }

    public void setSegunda(String segunda) {
        this.segunda = segunda;
    }

    public String getTerça() {
        return terça;
    }

    public void setTerça(String terça) {
        this.terça = terça;
    }

    public String getQuarta() {
        return quarta;
    }

    public void setQuarta(String quarta) {
        this.quarta = quarta;
    }

    public String getQuinta() {
        return quinta;
    }

    public void setQuinta(String quinta) {
        this.quinta = quinta;
    }

    public String getSexta() {
        return sexta;
    }

    public void setSexta(String sexta) {
        this.sexta = sexta;
    }

    public String getSabado() {
        return sabado;
    }

    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

    public String getDomingo() {
        return domingo;
    }

    public void setDomingo(String domingo) {
        this.domingo = domingo;
    }
}
