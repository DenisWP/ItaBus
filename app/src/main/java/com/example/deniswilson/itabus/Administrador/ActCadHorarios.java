package com.example.deniswilson.itabus.Administrador;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
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
 * Created by Denis Wilson on 23/10/2016.
 */

public class ActCadHorarios extends AppCompatActivity {

    EditText pesquisarH;
    EditText codigoH;
    EditText segunda;
    EditText terça;
    EditText quarta;
    EditText quinta;
    EditText sexta;
    EditText sabado;
    EditText domingo;

    ImageButton pesquisarCod;
    Button gravar;
    Button excluir;

    private Horarios horarios = new Horarios();
    private Interacoes interacoes;
    private BD database;
    private SQLiteDatabase conexao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_horarios);

        pesquisarH = (EditText) findViewById(R.id.edtPesquisarH);
        codigoH = (EditText) findViewById(R.id.edtCodigoH);
        segunda = (EditText) findViewById(R.id.edtSegunda);
        terça = (EditText) findViewById(R.id.edtTerça);
        quarta = (EditText) findViewById(R.id.edtQuarta);
        quinta = (EditText) findViewById(R.id.edtQuinta);
        sexta = (EditText) findViewById(R.id.edtSexta);
        sabado = (EditText) findViewById(R.id.edtSabado);
        domingo = (EditText) findViewById(R.id.edtDomingo);

        pesquisarCod = (ImageButton) findViewById(R.id.imagePesquisarH);
        gravar = (Button) findViewById(R.id.bGravarH);
        excluir = (Button) findViewById(R.id.bExcluirH);

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

    public void salvarHorarios(View view){
        try {
            horarios.setCodigo(codigoH.getText().toString());
            horarios.setSegunda(segunda.getText().toString());
            horarios.setTerça(terça.getText().toString());
            horarios.setQuarta(quarta.getText().toString());
            horarios.setQuinta(quinta.getText().toString());
            horarios.setSexta(sexta.getText().toString());
            horarios.setSabado(sabado.getText().toString());
            horarios.setDomingo(domingo.getText().toString());


            if (       horarios.getCodigo().length() == 0
                    || horarios.getSegunda().length() == 0
                    || horarios.getTerça().length() == 0
                    || horarios.getQuarta().length() == 0
                    || horarios.getQuinta().length() == 0
                    || horarios.getSexta().length() == 0
                    || horarios.getSabado().length() == 0
                    || horarios.getDomingo().length() == 0 ){

                Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show();

            }else {
                if (horarios.getId() == 0){
                    interacoes.InserirHorarios(horarios);
                    Toast.makeText(this, "Horários inseridos com sucesso.", Toast.LENGTH_SHORT).show();
                    limparDados();
                }else {
                    interacoes.AtualizarHorarios(horarios);
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

    public void excluirHorarios(View view){
        try{
            interacoes.ExcluirHorarios(horarios);
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

        pesquisarH.setText("");
        codigoH.setText("");
        segunda.setText("");
        terça.setText("");
        quarta.setText("");
        quinta.setText("");
        sexta.setText("");
        sabado.setText("");
        domingo.setText("");

    }

    public void pesquisarHorarios(View view){
        try {

            database = new BD(this); // Criando a referencia do banco.
            conexao = database.getWritableDatabase();

            String pesqCodigoE = pesquisarH.getText().toString();

            Cursor cursor = conexao.rawQuery("SELECT "  +BD.COLUNA_IDH+","
                                                        +BD.COLUNA_CODIGO_HORARIO+","
                                                        +BD.COLUNA_SEGUNDA+","
                                                        +BD.COLUNA_TERÇA+","
                                                        +BD.COLUNA_QUARTA+","
                                                        +BD.COLUNA_QUINTA+","
                                                        +BD.COLUNA_SEXTA+","
                                                        +BD.COLUNA_SABADO+","
                                                        +BD.COLUNA_DOMINGO+
                                                        " FROM " +BD.TABELA_HORARIOS+" WHERE "
                                                        +BD.COLUNA_CODIGO_HORARIO+" = " + pesqCodigoE, null);

            if (cursor.moveToFirst()) {

                horarios.setId(cursor.getLong(0));
                codigoH.setText(cursor.getString(1));
                segunda.setText(cursor.getString(2));
                terça.setText(cursor.getString(3));
                quarta.setText(cursor.getString(4));
                quinta.setText(cursor.getString(5));
                sexta.setText(cursor.getString(6));
                sabado.setText(cursor.getString(7));
                domingo.setText(cursor.getString(8));

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
