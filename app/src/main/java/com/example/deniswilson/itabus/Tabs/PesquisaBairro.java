package com.example.deniswilson.itabus.Tabs;


import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.deniswilson.itabus.Administrador.BD;
import com.example.deniswilson.itabus.Administrador.Interacoes;
import com.example.deniswilson.itabus.Administrador.Municipal;
import com.example.deniswilson.itabus.Listar.ListarMunicipal;
import com.example.deniswilson.itabus.MainActivityInfo;
import com.example.deniswilson.itabus.R;

/**
 * Created by Denis Wilson on 08/10/2016.
 */

public class PesquisaBairro extends Fragment implements AdapterView.OnItemClickListener{

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_listar_municipal, container, false);

        pesq_municipal = (EditText) rootView.findViewById(R.id.pesq_municipal);

        listarMunicipal = (ListView) rootView.findViewById(R.id.listarMunicipal);
        listarMunicipal.setOnItemClickListener(this); // Instanciando o evento de clicar no intem

        try{
                /*
                * Feito para analisar se a conexão com o bando de dados
                * foi realizada corretamente.
                * */
            database = new BD(getActivity());
            conexao = database.getWritableDatabase();

            interacoes = new Interacoes(conexao);
            adpMunicipal = interacoes.ListarMunicipal(getActivity());

                /*Vinculando ao objeto no ListView
                * para exibir os resultados encontrados.
                * */
            listarMunicipal.setAdapter(adpMunicipal);

            filtrarBairros filtrarBairros = new filtrarBairros(adpMunicipal);
            pesq_municipal.addTextChangedListener(filtrarBairros);

        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
            dlg.setMessage("Erro ! " + ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();

        }

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        /*
        * adapterView: RETORNA REFERENCIA PARA O OBJETO ADAPTER (GETADAPTER)
        * view: RETORNA A REFERENCIA DO COMPONENTE LISTVIEW
        * I: POSIÇÃO DO ITEM SELECIONADO
        * long: ID DA LINHA* */

        /*Passando paramentros com a mesma classe it*/

        Municipal municipal = adpMunicipal.getItem(i);

        Intent it = new Intent(getActivity(), MainActivityInfo.class);
        /*Passando paramentros com a mesma classe it*/

        it.putExtra(BD.TABELA_MUNICIPAL, municipal);

        startActivityForResult(it, 0);

    }

    /*Classe para implementar uma interface, para capturar os dados que serão digitados
    * no componete texto TEXTWATCHER, no meu caso, será capturado enquando o usuário estiver
    * digitando*/

    private class filtrarBairros implements TextWatcher {

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
