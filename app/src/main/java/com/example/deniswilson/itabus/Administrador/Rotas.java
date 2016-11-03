package com.example.deniswilson.itabus.Administrador;

import java.io.Serializable;

/**
 * Created by Denis Wilson on 03/11/2016.
 */

public class Rotas implements Serializable {

    private long id;
    private String codigo;
    private String latitude_inicial;
    private String longitude_inicial;
    private String latitude_final;
    private String longitude_final;

    public Rotas(){
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

    public String getLatitude_inicial() {
        return latitude_inicial;
    }

    public void setLatitude_inicial(String latitude_inicial) {
        this.latitude_inicial = latitude_inicial;
    }

    public String getLongitude_inicial() {
        return longitude_inicial;
    }

    public void setLongitude_inicial(String longitude_inicial) {
        this.longitude_inicial = longitude_inicial;
    }

    public String getLatitude_final() {
        return latitude_final;
    }

    public void setLatitude_final(String latitude_final) {
        this.latitude_final = latitude_final;
    }

    public String getLongitude_final() {
        return longitude_final;
    }

    public void setLongitude_final(String longitude_final) {
        this.longitude_final = longitude_final;
    }

    /*Método para filtrar. Classe filtrarEmpresa*/
    public String toString(){
        return codigo;
    }
}
