package com.example.deniswilson.itabus.Listar;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import com.example.deniswilson.itabus.Administrador.BD;
import com.example.deniswilson.itabus.Administrador.Interacoes;
import com.example.deniswilson.itabus.Administrador.Intermunicipal;
import com.example.deniswilson.itabus.R;

/**
 * Created by Denis Wilson on 12/10/2016.
 */

public class ListarIntermunicipal extends ActionBarActivity implements AdapterView.OnItemClickListener {

    /*
* Foi implementado um evento "AdapterView.OnItemClickListener ",
* para que, quando um item da lista for clicado, ele carregar no
* formulario.
* */


    private BD database;
    private Interacoes interacoes;
    private SQLiteDatabase conexao;
    private ListView listarIntermunicipal;
    private ArrayAdapter<Intermunicipal> adpIntermunicipal;
    private EditText pesq_intermunicipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_listar_intermunicipal);

        pesq_intermunicipal = (EditText) findViewById(R.id.pesq_intermunicipal);

        listarIntermunicipal = (ListView) findViewById(R.id.listarIntermunicipal);
        listarIntermunicipal.setOnItemClickListener(this); // Instanciando o evento de clicar no intem

        try{
                /*
                * Feito para analisar se a conexão com o bando de dados
                * foi realizada corretamente.
                * */
            database = new BD(this);
            conexao = database.getWritableDatabase();

            interacoes = new Interacoes(conexao);
            adpIntermunicipal = interacoes.ListarIntermunicipal(this);

                /*Vinculando ao objeto no ListView
                * para exibir os resultados encontrados.
                * */
            listarIntermunicipal.setAdapter(adpIntermunicipal);

            filtrarCidades filtrarCidades = new filtrarCidades(adpIntermunicipal);
            pesq_intermunicipal.addTextChangedListener(filtrarCidades);

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

    private class filtrarCidades implements TextWatcher{

        private ArrayAdapter<Intermunicipal> arrayAdapter;
        private filtrarCidades(ArrayAdapter<Intermunicipal> arrayAdapter){
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

