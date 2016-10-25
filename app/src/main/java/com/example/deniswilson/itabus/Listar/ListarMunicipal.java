package com.example.deniswilson.itabus.Listar;

import android.content.Intent;
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
import com.example.deniswilson.itabus.MainActivity;
import com.example.deniswilson.itabus.MainActivityInfo;
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

    /*
    * Quando clicar em um bairro, irá carregar abrir uma nova act, com todas as informações.
    * */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             /*
        * adapterView: RETORNA REFERENCIA PARA O OBJETO ADAPTER (GETADAPTER)
        * view: RETORNA A REFERENCIA DO COMPONENTE LISTVIEW
        * I: POSIÇÃO DO ITEM SELECIONADO
        * long: ID DA LINHA* */

        /*Passando paramentros com a mesma classe it*/



        Intent irInfo = new Intent();
        irInfo.setClass(this, MainActivityInfo.class ); //Setar a classe
        startActivity(irInfo); // Iniciar a intent
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

