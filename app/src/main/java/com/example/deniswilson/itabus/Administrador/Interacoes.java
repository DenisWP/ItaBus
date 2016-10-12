package com.example.deniswilson.itabus.Administrador;

/**
 * Created by Denis Wilson on 11/10/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.widget.ArrayAdapter;

public class Interacoes {

    private SQLiteDatabase conexao;

    public Interacoes(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public void InserirMunicipal(Municipal municipal){
        ContentValues dadosMunicipal = new ContentValues();

        dadosMunicipal.put(BD.COLUNA_CODIGO, municipal.getCodigo());
        dadosMunicipal.put(BD.COLUNA_BAIRRO, municipal.getBairro());
        dadosMunicipal.put(BD.COLUNA_HORARIOS, municipal.getHorarios());
        dadosMunicipal.put(BD.COLUNA_ITINERARIOS, municipal.getItinerarios());
        dadosMunicipal.put(BD.COLUNA_PARADAS, municipal.getParadas());
        dadosMunicipal.put(BD.COLUNA_VALOR_PASSAGEM, municipal.getValorPassagem());
        dadosMunicipal.put(BD.COLUNA_ACESSO_PCD, municipal.getAcessoPcd());

        conexao.insert(BD.TABELA_MUNICIPAL, null, dadosMunicipal);
    }

    public void InserirIntermunicipal(Intermunicipal intermunicipal){
        ContentValues dadosIntermunicipal = new ContentValues();

        dadosIntermunicipal.put(BD.COLUNA_CODIGO, intermunicipal.getCodigo());
        dadosIntermunicipal.put(BD.COLUNA_CIDADE, intermunicipal.getCidade());
        dadosIntermunicipal.put(BD.COLUNA_HORARIOS, intermunicipal.getHorarios());
        dadosIntermunicipal.put(BD.COLUNA_ITINERARIOS, intermunicipal.getItinerarios());
        dadosIntermunicipal.put(BD.COLUNA_PARADAS, intermunicipal.getParadas());
        dadosIntermunicipal.put(BD.COLUNA_VALOR_PASSAGEM, intermunicipal.getValorPassagem());
        dadosIntermunicipal.put(BD.COLUNA_ACESSO_PCD, intermunicipal.getAcessoPcd());

        conexao.insert(BD.TABELA_INTERMUNICIPAL, null, dadosIntermunicipal);
    }

    public void AtualizarMunicipal(Municipal municipal){
        ContentValues dadosMunicipal = new ContentValues();

        dadosMunicipal.put(BD.COLUNA_CODIGO, municipal.getCodigo());
        dadosMunicipal.put(BD.COLUNA_BAIRRO, municipal.getBairro());
        dadosMunicipal.put(BD.COLUNA_HORARIOS, municipal.getHorarios());
        dadosMunicipal.put(BD.COLUNA_ITINERARIOS, municipal.getItinerarios());
        dadosMunicipal.put(BD.COLUNA_PARADAS, municipal.getParadas());
        dadosMunicipal.put(BD.COLUNA_VALOR_PASSAGEM, municipal.getValorPassagem());
        dadosMunicipal.put(BD.COLUNA_ACESSO_PCD, municipal.getAcessoPcd());

        conexao.update(BD.TABELA_MUNICIPAL, dadosMunicipal, BD.COLUNA_IDM + " = ? ", new String[]{String.valueOf(municipal.getId())});
    }

    public void AtualizarIntermunicipal(Intermunicipal intermunicipal){
        ContentValues dadosIntermunicipal = new ContentValues();

        dadosIntermunicipal.put(BD.COLUNA_CODIGO, intermunicipal.getCodigo());
        dadosIntermunicipal.put(BD.COLUNA_CIDADE, intermunicipal.getCidade());
        dadosIntermunicipal.put(BD.COLUNA_HORARIOS, intermunicipal.getHorarios());
        dadosIntermunicipal.put(BD.COLUNA_ITINERARIOS, intermunicipal.getItinerarios());
        dadosIntermunicipal.put(BD.COLUNA_PARADAS, intermunicipal.getParadas());
        dadosIntermunicipal.put(BD.COLUNA_VALOR_PASSAGEM, intermunicipal.getValorPassagem());
        dadosIntermunicipal.put(BD.COLUNA_ACESSO_PCD, intermunicipal.getAcessoPcd());

        conexao.update(BD.TABELA_INTERMUNICIPAL, dadosIntermunicipal, BD.COLUNA_IDI + " = ? ", new String[]{String.valueOf(intermunicipal.getId())});
    }

    public void ExcluirMunicipal (Municipal municipal){
        conexao.delete(BD.TABELA_MUNICIPAL, BD.COLUNA_IDM + " = ? ",
                new String[]{String.valueOf(municipal.getId())});

    }

    public void ExcluirIntermunicipal (Intermunicipal intermunicipal){
        conexao.delete(BD.TABELA_INTERMUNICIPAL, BD.COLUNA_IDI + " = ? ",
                new String[]{String.valueOf(intermunicipal.getId())});

    }

    public ArrayAdapter<String> ListarMunicipal(Context context){
        /*Colocando ClienteArrayAdapter que já foi criada, mudei o arrayadapter<cliente>*/
        ArrayAdapter adpMunicipal = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1 );

        Cursor cursor = conexao.query(BD.TABELA_MUNICIPAL, null, null, null, null, null, null);

        if(cursor.getCount() > 0){
            /*Percorrer todos os registros, moverá para o próximo registro
            enquanto houver registro.*/
            cursor.moveToFirst(); // Posicionando no primeiro registro

            do {

                Municipal municipal = new Municipal();
                String bairro = cursor.getString(2);
                adpMunicipal.add(bairro);



            }while (cursor.moveToNext());
        }
        return adpMunicipal;
    }
}
