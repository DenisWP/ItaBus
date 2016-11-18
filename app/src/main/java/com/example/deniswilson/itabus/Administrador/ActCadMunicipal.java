package com.example.deniswilson.itabus.Administrador;

import android.content.Context;
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
import com.example.deniswilson.itabus.Tabs.Inicio;

/**
 * Created by Denis Wilson on 11/10/2016.
 */

public class ActCadMunicipal extends AppCompatActivity {
    EditText pesquisar;
    EditText codigo;
    EditText bairro;
    EditText trajeto;
    EditText horarios;
    EditText itinerarios;
    EditText paradas;
    EditText valorPassagem;
    EditText acesspPcd;

    ImageButton pesquisarCod;
    Button gravar;
    Button excluir;

    private Municipal municipal = new Municipal();
    private Interacoes interacoes;
    private BD database;
    private SQLiteDatabase conexao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_municipal);

        pesquisar = (EditText) findViewById(R.id.edtPesquisarM);
        codigo = (EditText) findViewById(R.id.edtCodigoM);
        bairro = (EditText) findViewById(R.id.edtBairroM);
        trajeto = (EditText) findViewById(R.id.edtTrajetoM);
        horarios = (EditText) findViewById(R.id.edtHoráriosM);
        itinerarios = (EditText) findViewById(R.id.edtItinerarioM);
        paradas = (EditText) findViewById(R.id.edtParadasM);
        valorPassagem = (EditText) findViewById(R.id.edtValorPassagemM);
        acesspPcd = (EditText) findViewById(R.id.edtAcessoM);
        pesquisarCod = (ImageButton) findViewById(R.id.imagePesquisarM);
        gravar = (Button) findViewById(R.id.bGravarM);
        excluir = (Button) findViewById(R.id.bExcluirM);

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

    public void salvarMunicipal(View view){
        try {
            municipal.setCodigo(codigo.getText().toString());
            municipal.setBairro(bairro.getText().toString());
            municipal.setTrajeto(trajeto.getText().toString());
            municipal.setHorarios(horarios.getText().toString());
            municipal.setItinerarios(itinerarios.getText().toString());
            municipal.setParadas(paradas.getText().toString());
            municipal.setValorPassagem(valorPassagem.getText().toString());
            municipal.setAcessoPcd(acesspPcd.getText().toString());

            if (       municipal.getCodigo().length() == 0
                    || municipal.getBairro().length() == 0
                    || municipal.getTrajeto().length() == 0
                    || municipal.getHorarios().length() == 0
                    || municipal.getItinerarios().length() == 0
                    || municipal.getParadas().length() == 0
                    || municipal.getValorPassagem().length() == 0
                    || municipal.getAcessoPcd().length() == 0 ){

                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();

            }else {
                if (municipal.getId() == 0){
                    interacoes.InserirMunicipal(municipal);
                    Toast.makeText(this, "Transporte inserido com sucesso.", Toast.LENGTH_SHORT).show();
                    limparDados();
                }else {
                    interacoes.AtualizarMunicipal(municipal);
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

    public void excluirMunicipal(View view){
        try{
            interacoes.ExcluirMunicipal(municipal);
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
        codigo.setText("");
        bairro.setText("");
        trajeto.setText("");
        horarios.setText("");
        itinerarios.setText("");
        paradas.setText("");
        valorPassagem.setText("");
        acesspPcd.setText("");

    }

    public void pesquisarMunicipal(View view){
        try {

            database = new BD(this); // Criando a referencia do banco.
            conexao = database.getWritableDatabase();

            String pesqCodigo = pesquisar.getText().toString();

            Cursor cursor = conexao.rawQuery("SELECT "  +BD.COLUNA_IDM+","
                                                        +BD.COLUNA_CODIGO+","
                                                        +BD.COLUNA_BAIRRO+","
                                                        +BD.COLUNA_TRAJETO+","
                                                        +BD.COLUNA_HORARIOS+","
                                                        +BD.COLUNA_ITINERARIOS+","
                                                        +BD.COLUNA_PARADAS+","
                                                        +BD.COLUNA_VALOR_PASSAGEM+","
                                                        +BD.COLUNA_ACESSO_PCD+
                                                        " FROM " +BD.TABELA_MUNICIPAL+" WHERE "
                                                        +BD.COLUNA_CODIGO+" = " + pesqCodigo, null);

            if (cursor.moveToFirst()) {

                municipal.setId(cursor.getLong(0));
                codigo.setText(cursor.getString(1));
                bairro.setText(cursor.getString(2));
                trajeto.setText(cursor.getString(3));
                horarios.setText(cursor.getString(4));
                itinerarios.setText(cursor.getString(5));
                paradas.setText(cursor.getString(6));
                valorPassagem.setText(cursor.getString(7));
                acesspPcd.setText(cursor.getString(8));

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
