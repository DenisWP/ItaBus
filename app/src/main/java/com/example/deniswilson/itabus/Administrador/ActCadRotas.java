package com.example.deniswilson.itabus.Administrador;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.deniswilson.itabus.MainActivity;
import com.example.deniswilson.itabus.R;

/**
 * Created by Denis Wilson on 03/11/2016.
 */

public class ActCadRotas extends AppCompatActivity {

    EditText pesquisarR, codigoR, latitudeI, longitudeI, latitudeF, longitudeF;

    ImageButton pesquisarCod;
    Button gravarR;
    Button excluirR;

    private Rotas rotas = new Rotas();
    private Interacoes interacoes;
    private BD database;
    private SQLiteDatabase conexao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_rotas);

        pesquisarR = (EditText) findViewById(R.id.edtPesquisarR);
        codigoR = (EditText) findViewById(R.id.edtCodigoR);
        latitudeI = (EditText) findViewById(R.id.edtLatitudeI);
        longitudeI = (EditText) findViewById(R.id.edtLongitudeI);
        latitudeF = (EditText) findViewById(R.id.edtLatitudeF);
        longitudeF = (EditText) findViewById(R.id.edtLongitudeF);
        pesquisarCod = (ImageButton) findViewById(R.id.imagePesquisarR);
        gravarR = (Button) findViewById(R.id.bGravarR);
        excluirR = (Button) findViewById(R.id.bExcluirR);

        try{
            database = new BD(this); // Criando a referencia do banco.
            conexao = database.getWritableDatabase();
            interacoes = new Interacoes(conexao);

        }catch (Exception e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro !" + e.getMessage()); //Retornando o erro que ocorrer.
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    public void salvarRotas(View view){
        try {
            rotas.setCodigo(codigoR.getText().toString());
            rotas.setLatitude_inicial(latitudeI.getText().toString());
            rotas.setLongitude_inicial(longitudeI.getText().toString());
            rotas.setLatitude_final(latitudeF.getText().toString());
            rotas.setLongitude_final(longitudeF.getText().toString());

            if (       rotas.getCodigo().length() == 0
                    || rotas.getLatitude_inicial().length() == 0
                    || rotas.getLongitude_inicial().length() == 0
                    || rotas.getLatitude_final().length() == 0
                    || rotas.getLongitude_final().length() == 0){

                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();

            }else {
                if (rotas.getId() == 0){
                    interacoes.InserirRotas(rotas);
                    Toast.makeText(this, "Dados inseridos com sucesso.", Toast.LENGTH_SHORT).show();
                    limparDados();
                }else {
                    interacoes.AtualizarRotas(rotas);
                    Toast.makeText(this, "Atualização realizada com sucesso.", Toast.LENGTH_SHORT).show();
                    limparDados();
                }
            }

        }catch (Exception e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro !" + e.getMessage()); //Retornando o erro que ocorrer.
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    public void excluirRotas(View view){
        try{
            interacoes.ExcluirRotas(rotas);
            Toast.makeText(this, "Excluído !", Toast.LENGTH_SHORT).show();
            limparDados();
        }catch (Exception e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro !" + e.getMessage()); //Retornando o erro que ocorrer.
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    public void limparDados(){
        pesquisarR.setText("");
        codigoR.setText("");
        latitudeI.setText("");
        longitudeI.setText("");
        latitudeF.setText("");
        longitudeF.setText("");
    }

    public void pesquisarRotas(View view){
        try {

            database = new BD(this); // Criando a referencia do banco.
            conexao = database.getWritableDatabase();

            String pesqCodigoR = pesquisarR.getText().toString();

            Cursor cursor = conexao.rawQuery("SELECT "  +BD.COLUNA_IDR+","
                                                        +BD.COLUNA_CODIGO_ROTAS+","
                                                        +BD.COLUNA_LATITUDE_INICIAL+","
                                                        +BD.COLUNA_LONGITUDE_INICIAL+","
                                                        +BD.COLUNA_LATITUDE_FINAL+","
                                                        +BD.COLUNA_LONGITUDE_FINAL+
                                                        " FROM " +BD.TABELA_ROTAS+" WHERE "
                                                        +BD.COLUNA_CODIGO_ROTAS+" = " + pesqCodigoR, null);

            if (cursor.moveToFirst()) {

                rotas.setId(cursor.getLong(0));
                codigoR.setText(cursor.getString(1));
                latitudeI.setText(cursor.getString(2));
                longitudeI.setText(cursor.getString(3));
                latitudeF.setText(cursor.getString(4));
                longitudeF.setText(cursor.getString(5));


            }else {
                Toast.makeText(this, "Código não encontrado.", Toast.LENGTH_SHORT).show();
            }conexao.close();

        }catch (Exception e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Erro !" + e.getMessage()); //Retornando o erro que ocorrer.
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }

    public void voltarRotas(View view){
        Intent irMain = new Intent();
        irMain.setClass(this, MainActivity.class ); //Setar a classe cadCliente
        startActivity(irMain); // Iniciar a intent
    }
}
