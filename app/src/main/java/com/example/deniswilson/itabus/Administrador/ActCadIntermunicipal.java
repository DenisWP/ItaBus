package com.example.deniswilson.itabus.Administrador;

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

import com.example.deniswilson.itabus.R;

/**
 * Created by Denis Wilson on 11/10/2016.
 */

public class ActCadIntermunicipal extends AppCompatActivity {
    EditText pesquisar;
    EditText codigo;
    EditText cidade;
    EditText horarios;
    EditText itinerarios;
    EditText paradas;
    EditText valorPassagem;
    EditText acesspPcd;

    ImageButton pesquisarCod;
    Button gravar;
    Button excluir;

    private Intermunicipal intermunicipal = new Intermunicipal();
    private Interacoes interacoes;
    private BD database;
    private SQLiteDatabase conexao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_intermunicipal);

        pesquisar = (EditText) findViewById(R.id.edtPesquisarI);
        codigo = (EditText) findViewById(R.id.edtCodigoI);
        cidade = (EditText) findViewById(R.id.edtCidadeI);
        horarios = (EditText) findViewById(R.id.edtHoráriosI);
        itinerarios = (EditText) findViewById(R.id.edtItinerarioI);
        paradas = (EditText) findViewById(R.id.edtParadasI);
        valorPassagem = (EditText) findViewById(R.id.edtValorPassagemI);
        acesspPcd = (EditText) findViewById(R.id.edtAcessoI);
        pesquisarCod = (ImageButton) findViewById(R.id.imagePesquisarI);
        gravar = (Button) findViewById(R.id.bGravarI);
        excluir = (Button) findViewById(R.id.bExcluirI);

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
            intermunicipal.setCodigo(codigo.getText().toString());
            intermunicipal.setCidade(cidade.getText().toString());
            intermunicipal.setHorarios(horarios.getText().toString());
            intermunicipal.setItinerarios(itinerarios.getText().toString());
            intermunicipal.setParadas(paradas.getText().toString());
            intermunicipal.setValorPassagem(valorPassagem.getText().toString());
            intermunicipal.setAcessoPcd(acesspPcd.getText().toString());

            if (       intermunicipal.getCodigo().length() == 0
                    || intermunicipal.getCidade().length() == 0
                    || intermunicipal.getHorarios().length() == 0
                    || intermunicipal.getItinerarios().length() == 0
                    || intermunicipal.getParadas().length() == 0
                    || intermunicipal.getValorPassagem().length() == 0
                    || intermunicipal.getAcessoPcd().length() == 0 ){

                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();

            }else {
                if (intermunicipal.getId() == 0){
                    interacoes.InserirIntermunicipal(intermunicipal);
                    Toast.makeText(this, "Transporte inserido com sucesso.", Toast.LENGTH_SHORT).show();
                    limparDados();
                }else {
                    interacoes.AtualizarIntermunicipal(intermunicipal);
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
            interacoes.ExcluirIntermunicipal(intermunicipal);
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
        cidade.setText("");
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

            Cursor cursor = conexao.rawQuery("SELECT "  +BD.COLUNA_IDI+","
                    +BD.COLUNA_CODIGO+","
                    +BD.COLUNA_CIDADE+","
                    +BD.COLUNA_HORARIOS+","
                    +BD.COLUNA_ITINERARIOS+","
                    +BD.COLUNA_PARADAS+","
                    +BD.COLUNA_VALOR_PASSAGEM+","
                    +BD.COLUNA_ACESSO_PCD+
                    " FROM " +BD.TABELA_INTERMUNICIPAL+" WHERE "+BD.COLUNA_CODIGO+" = " + pesqCodigo, null);

            if (cursor.moveToFirst()) {

                intermunicipal.setId(cursor.getLong(0));
                codigo.setText(cursor.getString(1));
                cidade.setText(cursor.getString(2));
                horarios.setText(cursor.getString(3));
                itinerarios.setText(cursor.getString(4));
                paradas.setText(cursor.getString(5));
                valorPassagem.setText(cursor.getString(6));
                acesspPcd.setText(cursor.getString(7));

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
}
