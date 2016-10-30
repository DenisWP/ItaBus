package com.example.deniswilson.itabus.Tabs;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deniswilson.itabus.Administrador.BD;
import com.example.deniswilson.itabus.Administrador.Horarios;
import com.example.deniswilson.itabus.Administrador.Interacoes;
import com.example.deniswilson.itabus.Administrador.Municipal;
import com.example.deniswilson.itabus.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis Wilson on 24/10/2016.
 */



public class InfoUsuarios extends Fragment {

    public  Municipal municipal;
    private BD database;
    private Interacoes interacoes;
    private SQLiteDatabase conexao;
    public static String h; /*Preciso saber o código do horário para gerar a Lista de Horários da tabela Horário*/

    TextView txtCodigo, txtBairro, txtItinerario, txtParadas, txtValorPassagem, txtAcessoPcd;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_tela_infousuarios, container, false);

        txtCodigo = (TextView) rootView.findViewById(R.id.txtCodigo);
        txtBairro = (TextView) rootView.findViewById(R.id.txtBairro);
        txtItinerario = (TextView) rootView.findViewById(R.id.txtItinerario);
        txtParadas = (TextView) rootView.findViewById(R.id.txtParadas);
        txtValorPassagem = (TextView) rootView.findViewById(R.id.txtValorPassagem);
        txtAcessoPcd = (TextView) rootView.findViewById(R.id.txtAcessoPcd);

        /*Recuperando os dados*/
        Bundle bundle = getActivity().getIntent().getExtras();
        if((bundle != null) && (bundle.containsKey(BD.TABELA_MUNICIPAL))){
            municipal = (Municipal) bundle.getSerializable(BD.TABELA_MUNICIPAL);
            h = municipal.getHorarios(); //Apenas para pegar o código do horário atribuído ao item deste click.
            exibirDados(); /*Exibindo os dados na tela de informações*/
        }else {
            municipal = new Municipal();
        }

        try{
            database = new BD(getActivity()); // Criando a referencia do banco.
            conexao = database.getWritableDatabase();
            interacoes = new Interacoes(conexao);

        }catch (Exception e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
            dlg.setMessage("Erro !" + e.getMessage()); //Retornando o erro que ocorrer.
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }

        return rootView;
    }

    private void exibirDados(){
        txtCodigo.setText(municipal.getCodigo());
        txtBairro.setText(municipal.getBairro());
        txtItinerario.setText(municipal.getItinerarios());
        txtParadas.setText(municipal.getParadas());
        txtValorPassagem.setText(municipal.getValorPassagem());
        txtAcessoPcd.setText(municipal.getAcessoPcd());
    }



    public String F(){
        String h = municipal.getHorarios();
        return h;
    }

}
