package com.example.deniswilson.itabus.Administrador;

import android.content.Context;
import android.database.sqlite.*;

/**
 * Created by Denis Wilson on 09/10/2016.
 */

public class BD extends SQLiteOpenHelper{

    private static final String NOME_BD = "transporte";
    private static final int VERSAO_BD = 1;

    public static final String TABELA_MUNICIPAL = "municipal";
    public static final String COLUNA_IDM = "_id"; /*Chave primária para tabela municipal*/

    public static final String TABELA_INTERMUNICIPAL = "intermunicipal";
    public static final String COLUNA_IDI = "_id"; /*Chave primária para tabela intermunicipal*/

    public static final String COLUNA_CODIGO = "codigo";
    public static final String COLUNA_BAIRRO = "bairro"; // Transporte municipal
    public static final String COLUNA_CIDADE = "cidade"; // Transporte intermunicipal
    public static final String COLUNA_HORARIOS = "horarios";
    public static final String COLUNA_ITINERARIOS = "itinerarios";
    public static final String COLUNA_PARADAS = "paradas"; // Transporte Intermunicipal
    public static final String COLUNA_VALOR_PASSAGEM = "valor_passagem";
    public static final String COLUNA_ACESSO_PCD = "acesso_pcd";


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
                        + TABELA_INTERMUNICIPAL + " ( "
                        + COLUNA_IDI + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                        + COLUNA_CODIGO + " TEXT, "
                        + COLUNA_CIDADE + " TEXT, "
                        + COLUNA_HORARIOS + " TEXT, "
                        + COLUNA_ITINERARIOS + " TEXT, "
                        + COLUNA_PARADAS + " TEXT, "
                        + COLUNA_VALOR_PASSAGEM + " TEXT, "
                        + COLUNA_ACESSO_PCD + " TEXT " + " ) "
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {
        /*
        * Destruindo as tabelas, e construindo novamente caso haja atualização de banco de dados.
        * */
        bd.execSQL("drop table "+TABELA_MUNICIPAL+ " ");
        bd.execSQL("drop table "+TABELA_INTERMUNICIPAL+ " ");
        onCreate(bd);
    }
}
