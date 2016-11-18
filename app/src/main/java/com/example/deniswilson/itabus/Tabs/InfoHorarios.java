package com.example.deniswilson.itabus.Tabs;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deniswilson.itabus.Administrador.BD;
import com.example.deniswilson.itabus.Administrador.Horarios;
import com.example.deniswilson.itabus.Administrador.Interacoes;
import com.example.deniswilson.itabus.Administrador.Municipal;
import com.example.deniswilson.itabus.MainActivity;
import com.example.deniswilson.itabus.R;

import static com.example.deniswilson.itabus.Tabs.InfoUsuarios.horario;


/**
 * Created by Denis Wilson on 24/10/2016.
 */

public class InfoHorarios extends Fragment{

    TextView txvSegunda, txvTerça, txvQuarta, txvQuinta, txvSexta, txvSabado, txvDomingo;
    Button voltarInicio;
    private Horarios horarios = new Horarios();
    private BD database;
    private SQLiteDatabase conexao;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_tela_infohorarios, container, false);

        txvSegunda = (TextView) rootView.findViewById(R.id.txvSegunda);
        txvTerça = (TextView) rootView.findViewById(R.id.txvTerça);
        txvQuarta = (TextView) rootView.findViewById(R.id.txvQuarta);
        txvQuinta = (TextView) rootView.findViewById(R.id.txvQuinta);
        txvSexta = (TextView) rootView.findViewById(R.id.txvSexta);
        txvSabado = (TextView) rootView.findViewById(R.id.txvSabado);
        txvDomingo = (TextView) rootView.findViewById(R.id.txvDomingo);
        /*
        * Método onClick para voltar a página inicial.
        * */
        voltarInicio = (Button) rootView.findViewById(R.id.voltarIn);
        voltarInicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent irInicio = new Intent();
                irInicio.setClass(getActivity(), MainActivity.class ); //Setar a classe
                startActivity(irInicio); // Iniciar a intent
            }
        });

        exibirHorario(); /*Exibindo os dados na tela de informações*/
        return rootView;
    }

    public void exibirHorario(){
        try {

            InfoUsuarios horario = new InfoUsuarios(); // Chamando a classe, para pegar o código do horário
            String codHorario = horario.horario; // Pegando o código do horário recebido na classe InfoUsuarios, e atribuindo a uma variável.

            Toast.makeText(getActivity(), "-> "+codHorario+"", Toast.LENGTH_SHORT).show();

            database = new BD(getActivity()); // Criando a referencia do banco.
            conexao = database.getWritableDatabase();
            /*
            * Fazendo o select na tabela horário e listando todos os horários cadastrados.
            * */
            Cursor cursor = conexao.rawQuery("SELECT "  +BD.COLUNA_IDH+","
                                                        +BD.COLUNA_CODIGO_HORARIO+","
                                                        +BD.COLUNA_SEGUNDA+","
                                                        +BD.COLUNA_TERÇA+","
                                                        +BD.COLUNA_QUARTA+","
                                                        +BD.COLUNA_QUINTA+","
                                                        +BD.COLUNA_SEXTA +","
                                                        +BD.COLUNA_SABADO+","
                                                        +BD.COLUNA_DOMINGO+
                                                        " FROM " +BD.TABELA_HORARIOS+" WHERE "
                                                        +BD.COLUNA_CODIGO_HORARIO+" = " +codHorario, null);

            if (cursor.moveToFirst()) {

                horarios.setId(cursor.getLong(0));

                txvSegunda.setText(cursor.getString(2));
                txvTerça.setText(cursor.getString(3));
                txvQuarta.setText(cursor.getString(4));
                txvQuinta.setText(cursor.getString(5));
                txvSexta.setText(cursor.getString(6));
                txvSabado.setText(cursor.getString(7));
                txvDomingo.setText(cursor.getString(8));


            }else {
                Toast.makeText(getActivity(), "Erro ao exibir horários.", Toast.LENGTH_SHORT).show();
            }conexao.close();

        }catch (Exception e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
            dlg.setMessage("Erro !" + e.getMessage()); //Retornando o erro que ocorrer.
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }
}
