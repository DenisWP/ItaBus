package com.example.deniswilson.itabus.Administrador;

import android.content.Context;
import android.database.sqlite.*;

/**
 * Created by Denis Wilson on 09/10/2016.
 */

public class BD extends SQLiteOpenHelper{

    private static final String NOME_BD = "transporte";
    private static final int VERSAO_BD = 4;

    public static final String TABELA_MUNICIPAL = "municipal";
    public static final String COLUNA_IDM = "_id"; /*Chave primária para tabela municipal*/
    public static final String COLUNA_CODIGO = "codigo";
    public static final String COLUNA_BAIRRO = "bairro"; // Transporte municipal
    public static final String COLUNA_HORARIOS = "horarios";
    public static final String COLUNA_ITINERARIOS = "itinerarios";
    public static final String COLUNA_PARADAS = "paradas"; // Transporte
    public static final String COLUNA_VALOR_PASSAGEM = "valor_passagem";
    public static final String COLUNA_ACESSO_PCD = "acesso_pcd";

    public static final String TABELA_EMPRESA = "empresa";
    public static final String COLUNA_IDE = "_id"; /*Chave primária para tabela empresa*/
    public static final String COLUNA_CODIGO_EMPRESA = "codigo_empresa";
    public static final String COLUNA_NOME_EMPRESA = "nome_empresa";
    public static final String COLUNA_SIGLA = "sigla";
    public static final String COLUNA_CONTATO = "contato";

    public static final String TABELA_HORARIOS = "horarios";
    public static final String COLUNA_IDH = "_id"; /*Chave primária para tabela horarios*/
    public static final String COLUNA_CODIGO_HORARIO = "codigo_horario";
    public static final String COLUNA_SEGUNDA = "segunda";
    public static final String COLUNA_TERÇA = "terça";
    public static final String COLUNA_QUARTA = "quarta";
    public static final String COLUNA_QUINTA = "quinta";
    public static final String COLUNA_SEXTA = "sexta";
    public static final String COLUNA_SABADO = "sabado";
    public static final String COLUNA_DOMINGO = "domingo";

    public static final String TABELA_ROTAS = "rotas";
    public static final String COLUNA_IDR = "_id"; /*Chave primária para tabela rotas*/
    public static final String COLUNA_CODIGO_ROTAS = "codigo_rotas";
    public static final String COLUNA_LATITUDE_INICIAL = "latitude_inicial";
    public static final String COLUNA_LONGITUDE_INICIAL = "longitude_inicial";
    public static final String COLUNA_LATITUDE_FINAL = "latitude_final";
    public static final String COLUNA_LONGITUDE_FINAL = "longitude_final";


    public BD(Context context){
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(
                " CREATE TABLE IF NOT EXISTS "
                        + TABELA_MUNICIPAL + " ( "
                        + COLUNA_IDM + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                        + COLUNA_CODIGO + " TEXT, "
                        + COLUNA_BAIRRO + " TEXT, "
                        + COLUNA_HORARIOS + " TEXT, "
                        + COLUNA_ITINERARIOS + " TEXT, "
                        + COLUNA_PARADAS + " TEXT, "
                        + COLUNA_VALOR_PASSAGEM + " TEXT, "
                        + COLUNA_ACESSO_PCD + " TEXT " + " ) "
        );

        bd.execSQL(
                " CREATE TABLE IF NOT EXISTS "
                        + TABELA_EMPRESA + " ( "
                        + COLUNA_IDE + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                        + COLUNA_CODIGO_EMPRESA + " TEXT, "
                        + COLUNA_NOME_EMPRESA + " TEXT, "
                        + COLUNA_SIGLA + " TEXT, "
                        + COLUNA_CONTATO + " TEXT " + " ) "
        );

        bd.execSQL(
                " CREATE TABLE IF NOT EXISTS "
                        + TABELA_HORARIOS + " ( "
                        + COLUNA_IDH + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                        + COLUNA_CODIGO_HORARIO + " TEXT, "
                        + COLUNA_SEGUNDA + " TEXT, "
                        + COLUNA_TERÇA + " TEXT, "
                        + COLUNA_QUARTA + " TEXT, "
                        + COLUNA_QUINTA + " TEXT, "
                        + COLUNA_SEXTA + " TEXT, "
                        + COLUNA_SABADO + " TEXT, "
                        + COLUNA_DOMINGO + " TEXT " + " ) "
        );

        bd.execSQL(
                " CREATE TABLE IF NOT EXISTS "
                        + TABELA_ROTAS + " ( "
                        + COLUNA_IDR + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                        + COLUNA_CODIGO_ROTAS + " TEXT, "
                        + COLUNA_LATITUDE_INICIAL + " TEXT, "
                        + COLUNA_LONGITUDE_INICIAL + " TEXT, "
                        + COLUNA_LATITUDE_FINAL + " TEXT, "
                        + COLUNA_LONGITUDE_FINAL + " TEXT " + " ) "
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {
        /*
        * Destruindo as tabelas, e construindo novamente caso haja atualização de banco de dados.
        * */
        bd.execSQL("drop table "+TABELA_MUNICIPAL+ " ");
        bd.execSQL("drop table "+TABELA_EMPRESA+ " ");
        bd.execSQL("drop table "+TABELA_HORARIOS+ " ");
        bd.execSQL("drop table "+TABELA_ROTAS+ " ");
        onCreate(bd);
    }
}
