package com.example.deniswilson.itabus.Listar;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.deniswilson.itabus.Administrador.BD;
import com.example.deniswilson.itabus.Administrador.Interacoes;
import com.example.deniswilson.itabus.Administrador.Municipal;
import com.example.deniswilson.itabus.R;

/**
 * Created by Denis Wilson on 12/10/2016.
 */

public class ListarMunicipal extends ActionBarActivity implements AdapterView.OnItemClickListener {

    /*
* Foi implementado um evento "AdapterView.OnItemClickListener ",
* para que, quando um item da lista for clicado, ele carregar no
* formulario.
* */


        private BD database;
        private Interacoes interacoes;
        private SQLiteDatabase conexao;
        private ListView listarMunicipal;
        private ArrayAdapter<String> adpMunicipal;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.act_listar_municipal);

            listarMunicipal = (ListView) findViewById(R.id.listarMunicipal);
            listarMunicipal.setOnItemClickListener(this); // Instanciando o evento de clicar no intem

            try{
                /*
                * Feito para analisar se a conexão com o bando de dados
                * foi realizada corretamente.
                * */
                database = new BD(this);
                conexao = database.getWritableDatabase();

                interacoes = new Interacoes(conexao);
                adpMunicipal = interacoes.ListarMunicipal(this);

                /*Vinculando ao objeto no ListView
                * para exibir os resultados encontrados.
                * */
                listarMunicipal.setAdapter(adpMunicipal);

            }catch (SQLException ex){
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setMessage("Erro ! " + ex.getMessage());
                dlg.setNeutralButton("Ok", null);
                dlg.show();

            }

        }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}

