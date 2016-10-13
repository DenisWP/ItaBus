package com.example.deniswilson.itabus.Administrador;

import java.io.Serializable;

/**
 * Created by Denis Wilson on 11/10/2016.
 */

public class Intermunicipal implements Serializable {

    private long id;
    private String codigo;
    private String cidade;
    private String horarios;
    private String itinerarios;
    private String paradas;
    private String valorPassagem;
    private String acessoPcd;


    public Intermunicipal(){
        id = 0; /*Definindo o valor do id, para verificação quando for atualizar. */
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getItinerarios() {
        return itinerarios;
    }

    public void setItinerarios(String itinerarios) {
        this.itinerarios = itinerarios;
    }

    public String getParadas() {
        return paradas;
    }

    public void setParadas(String paradas) {
        this.paradas = paradas;
    }

    public String getValorPassagem() {
        return valorPassagem;
    }

    public void setValorPassagem(String valorPassagem) {
        this.valorPassagem = valorPassagem;
    }

    public String getAcessoPcd() {
        return acessoPcd;
    }

    public void setAcessoPcd(String acessoPcd) {
        this.acessoPcd = acessoPcd;
    }

    /*Método para filtrar. Classe filtrarCidade*/
    public String toString(){
        return cidade;
    }
}
