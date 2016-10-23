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

    /*INTERAÇÕES - TRANSPORTE MUNICIPAL*/

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

    public void ExcluirMunicipal (Municipal municipal){
        conexao.delete(BD.TABELA_MUNICIPAL, BD.COLUNA_IDM + " = ? ",
                new String[]{String.valueOf(municipal.getId())});

    }

    public ArrayAdapter<Municipal> ListarMunicipal(Context context){
        /*Colocando ClienteArrayAdapter que já foi criada, mudei o arrayadapter<municipal>*/
        ArrayAdapter<Municipal> adpMunicipal = new ArrayAdapter<Municipal>(context, android.R.layout.simple_list_item_1 );

        Cursor cursor = conexao.query(BD.TABELA_MUNICIPAL, null, null, null, null, null, null);

        if(cursor.getCount() > 0){
            /*Percorrer todos os registros, moverá para o próximo registro
            enquanto houver registro.*/
            cursor.moveToFirst(); // Posicionando no primeiro registro

            do {

                Municipal municipal = new Municipal();
                municipal.setId(cursor.getLong(0));
                municipal.setCodigo(cursor.getString(1));
                municipal.setBairro(cursor.getString(2));
                municipal.setHorarios(cursor.getString(3));
                municipal.setItinerarios(cursor.getString(4));
                municipal.setParadas(cursor.getString(5));
                municipal.setValorPassagem(cursor.getString(6));
                municipal.setAcessoPcd(cursor.getString(7));

                adpMunicipal.add(municipal);

            }while (cursor.moveToNext());
        }
        return adpMunicipal;
    }


    /*INTERAÇÕES - EMPRESA */

    public void InserirEmpresa(Empresa empresa){
        ContentValues dadosEmpresa = new ContentValues();

        dadosEmpresa.put(BD.COLUNA_CODIGO_EMPRESA, empresa.getCodigo());
        dadosEmpresa.put(BD.COLUNA_NOME_EMPRESA, empresa.getNome());
        dadosEmpresa.put(BD.COLUNA_SIGLA, empresa.getSigla());
        dadosEmpresa.put(BD.COLUNA_CONTATO, empresa.getContato());

        conexao.insert(BD.TABELA_EMPRESA, null, dadosEmpresa);
    }

    public void AtualizarEmpresa(Empresa empresa){
        ContentValues dadosEmpresa = new ContentValues();

        dadosEmpresa.put(BD.COLUNA_CODIGO_EMPRESA, empresa.getCodigo());
        dadosEmpresa.put(BD.COLUNA_NOME_EMPRESA, empresa.getNome());
        dadosEmpresa.put(BD.COLUNA_SIGLA, empresa.getSigla());
        dadosEmpresa.put(BD.COLUNA_CONTATO, empresa.getContato());

        conexao.update(BD.TABELA_EMPRESA, dadosEmpresa, BD.COLUNA_IDE + " = ? ", new String[]{String.valueOf(empresa.getId())});
    }

    public void ExcluirEmpresa (Empresa empresa){
        conexao.delete(BD.TABELA_EMPRESA, BD.COLUNA_IDE + " = ? ",
                new String[]{String.valueOf(empresa.getId())});

    }


    /*INTERAÇÕES HORARIOS*/

    public void InserirHorarios(Horarios horarios){
        ContentValues dadosHorarios = new ContentValues();

        dadosHorarios.put(BD.COLUNA_CODIGO_HORARIO, horarios.getCodigo());
        dadosHorarios.put(BD.COLUNA_SEGUNDA, horarios.getSegunda());
        dadosHorarios.put(BD.COLUNA_TERÇA, horarios.getTerça());
        dadosHorarios.put(BD.COLUNA_QUARTA, horarios.getQuarta());
        dadosHorarios.put(BD.COLUNA_QUINTA, horarios.getQuinta());
        dadosHorarios.put(BD.COLUNA_SEXTA, horarios.getSexta());
        dadosHorarios.put(BD.COLUNA_SABADO, horarios.getSabado());
        dadosHorarios.put(BD.COLUNA_DOMINGO, horarios.getDomingo());

        conexao.insert(BD.TABELA_HORARIOS, null, dadosHorarios);
    }

    public void AtualizarHorarios(Horarios horarios){
        ContentValues dadosHorarios = new ContentValues();

        dadosHorarios.put(BD.COLUNA_CODIGO_HORARIO, horarios.getCodigo());
        dadosHorarios.put(BD.COLUNA_SEGUNDA, horarios.getSegunda());
        dadosHorarios.put(BD.COLUNA_TERÇA, horarios.getTerça());
        dadosHorarios.put(BD.COLUNA_QUARTA, horarios.getQuarta());
        dadosHorarios.put(BD.COLUNA_QUINTA, horarios.getQuinta());
        dadosHorarios.put(BD.COLUNA_SEXTA, horarios.getSexta());
        dadosHorarios.put(BD.COLUNA_SABADO, horarios.getSabado());
        dadosHorarios.put(BD.COLUNA_DOMINGO, horarios.getDomingo());

        conexao.update(BD.TABELA_HORARIOS, dadosHorarios, BD.COLUNA_IDH + " = ? ", new String[]{String.valueOf(horarios.getId())});
    }

    public void ExcluirHorarios (Horarios horarios){
        conexao.delete(BD.TABELA_HORARIOS, BD.COLUNA_IDH + " = ? ",
                new String[]{String.valueOf(horarios.getId())});

    }

}
