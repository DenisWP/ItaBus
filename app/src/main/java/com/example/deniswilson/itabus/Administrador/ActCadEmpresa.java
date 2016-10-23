package com.example.deniswilson.itabus.Administrador;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
 * Created by Denis Wilson on 11/10/2016.
 */

public class ActCadEmpresa extends AppCompatActivity {
    EditText pesquisar;
    EditText codigoE;
    EditText nomeE;
    EditText siglaE;
    EditText contatoE;


    ImageButton pesquisarCod;
    Button gravar;
    Button excluir;

    private Empresa empresa = new Empresa();
    private Interacoes interacoes;
    private BD database;
    private SQLiteDatabase conexao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_empresa);

        pesquisar = (EditText) findViewById(R.id.edtPesquisarE);
        codigoE = (EditText) findViewById(R.id.edtCodigoE);
        nomeE = (EditText) findViewById(R.id.edtNomeE);
        siglaE = (EditText) findViewById(R.id.edtSiglaE);
        contatoE = (EditText) findViewById(R.id.edtContatoE);
        pesquisarCod = (ImageButton) findViewById(R.id.imagePesquisarE);
        gravar = (Button) findViewById(R.id.bGravarE);
        excluir = (Button) findViewById(R.id.bExcluirE);

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

    public void salvarEmpresa(View view){
        try {
            empresa.setCodigo(codigoE.getText().toString());
            empresa.setNome(nomeE.getText().toString());
            empresa.setSigla(siglaE.getText().toString());
            empresa.setContato(contatoE.getText().toString());

            if (       empresa.getCodigo().length() == 0
                    || empresa.getNome().length() == 0
                    || empresa.getSigla().length() == 0
                    || empresa.getContato().length() == 0 ){

                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();

            }else {
                if (empresa.getId() == 0){
                    interacoes.InserirEmpresa(empresa);
                    Toast.makeText(this, "Dados inseridos com sucesso.", Toast.LENGTH_SHORT).show();
                    limparDados();
                }else {
                    interacoes.AtualizarEmpresa(empresa);
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

    public void excluirEmpresa(View view){
        try{
            interacoes.ExcluirEmpresa(empresa);
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

        pesquisar.setText("");
        codigoE.setText("");
        nomeE.setText("");
        siglaE.setText("");
        contatoE.setText("");

    }

    public void pesquisarEmpresa(View view){
        try {

            database = new BD(this); // Criando a referencia do banco.
            conexao = database.getWritableDatabase();

            String pesqCodigoE = pesquisar.getText().toString();

            Cursor cursor = conexao.rawQuery("SELECT "  +BD.COLUNA_IDE+","
                                                        +BD.COLUNA_CODIGO_EMPRESA+","
                                                        +BD.COLUNA_NOME_EMPRESA+","
                                                        +BD.COLUNA_SIGLA+","
                                                        +BD.COLUNA_CONTATO+
                                                        " FROM " +BD.TABELA_EMPRESA+" WHERE "
                                                        +BD.COLUNA_CODIGO_EMPRESA+" = " + pesqCodigoE, null);

            if (cursor.moveToFirst()) {

                empresa.setId(cursor.getLong(0));
                codigoE.setText(cursor.getString(1));
                nomeE.setText(cursor.getString(2));
                siglaE.setText(cursor.getString(3));
                contatoE.setText(cursor.getString(4));

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

    public void voltarM(View view){
        Intent irMain = new Intent();
        irMain.setClass(this, MainActivity.class ); //Setar a classe cadCliente
        startActivity(irMain); // Iniciar a intent
    }

}
