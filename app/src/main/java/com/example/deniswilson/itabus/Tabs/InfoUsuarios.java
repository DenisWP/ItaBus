package com.example.deniswilson.itabus.Tabs;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.deniswilson.itabus.Administrador.BD;
import com.example.deniswilson.itabus.Administrador.Interacoes;
import com.example.deniswilson.itabus.Administrador.Municipal;
import com.example.deniswilson.itabus.Administrador.Rotas;
import com.example.deniswilson.itabus.Mapas.Mapas;
import com.example.deniswilson.itabus.Mapas.RotasMaps;
import com.example.deniswilson.itabus.R;

/**
 * Created by Denis Wilson on 24/10/2016.
 */



public class InfoUsuarios extends Fragment {

    public  Municipal municipal;
    private BD database;
    private Interacoes interacoes;
    private SQLiteDatabase conexao;
    public static String horario, rotas; /*Preciso saber o código do horário,
    as latitudes e longitudes para gerar a Lista de Horários e as rotas*/

    TextView txtCodigo, txtBairro, txtTrajeto, txtItinerario, txtParadas, txtValorPassagem, txtAcessoPcd;
    ImageButton imgRotas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_tela_infousuarios, container, false);

        txtCodigo = (TextView) rootView.findViewById(R.id.txtCodigo);
        txtBairro = (TextView) rootView.findViewById(R.id.txtBairro);
        txtTrajeto = (TextView) rootView.findViewById(R.id.txtTrajeto);
        //txtItinerario = (TextView) rootView.findViewById(R.id.txtItinerario);
        txtParadas = (TextView) rootView.findViewById(R.id.txtParadas);
        txtValorPassagem = (TextView) rootView.findViewById(R.id.txtValorPassagem);
        txtAcessoPcd = (TextView) rootView.findViewById(R.id.txtAcessoPcd);

        /*
        * Método onClick para a imagem de rotas. Funcionando.
        * */
        imgRotas = (ImageButton) rootView.findViewById(R.id.imgRotas); /*Imagem para activity das rotas*/
        imgRotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irMaps = new Intent();
                irMaps.setClass(getActivity(), Mapas.class ); //Setar a classe
                startActivity(irMaps); // Iniciar a intent
            }
        });



        /*Recuperando os dados*/
        Bundle bundle = getActivity().getIntent().getExtras();
        if((bundle != null) && (bundle.containsKey(BD.TABELA_MUNICIPAL))){
            municipal = (Municipal) bundle.getSerializable(BD.TABELA_MUNICIPAL);

            /*Pegando o código do horário da tabela transporte, através do ítem após clicar no bairro*/
            horario = municipal.getHorarios(); //Apenas para pegar o código do horário atribuído ao item deste click.

            /*Pegando o codigo das rotas após o clicar no bairro*/
            rotas = municipal.getItinerarios();

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
        txtTrajeto.setText(municipal.getTrajeto());
//        txtItinerario.setText(municipal.getItinerarios());
        txtParadas.setText(municipal.getParadas());
        txtValorPassagem.setText(municipal.getValorPassagem());
        txtAcessoPcd.setText(municipal.getAcessoPcd());
    }
}
