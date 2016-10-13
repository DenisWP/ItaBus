package com.example.deniswilson.itabus.Listar;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

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
        private ArrayAdapter<Municipal> adpMunicipal;
        private EditText pesq_municipal;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.act_listar_municipal);

            pesq_municipal = (EditText) findViewById(R.id.pesq_municipal);

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

                filtrarBairros filtrarBairros = new filtrarBairros(adpMunicipal);
                pesq_municipal.addTextChangedListener(filtrarBairros);

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

    /*Classe para implementar uma interface, para capturar os dados que serão digitados
    * no componete texto TEXTWATCHER, no meu caso, será capturado enquando o usuário estiver
    * digitando*/

    private class filtrarBairros implements TextWatcher{

        private ArrayAdapter<Municipal> arrayAdapter;
        private filtrarBairros(ArrayAdapter<Municipal> arrayAdapter){
            this.arrayAdapter = arrayAdapter;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        /*Usarei este método, pois é o responsável por filtrar enquanto estiver digitando*/
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            arrayAdapter.getFilter().filter(charSequence);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


}

