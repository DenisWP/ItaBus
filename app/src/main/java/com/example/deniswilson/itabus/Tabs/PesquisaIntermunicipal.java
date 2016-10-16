package com.example.deniswilson.itabus.Tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.deniswilson.itabus.Listar.ListarIntermunicipal;
import com.example.deniswilson.itabus.Listar.ListarMunicipal;
import com.example.deniswilson.itabus.R;

/**
 * Created by Denis Wilson on 08/10/2016.
 */

public class PesquisaIntermunicipal extends Fragment{

    private Button bPesqCidade;
    private Button bPesqEmpresas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_tag_intermunicipal, container, false);

        /*
        *  Criando função OnclickListener, para chamar a tela de lista. Porque estou usando o Fragment
        * */
        bPesqCidade = (Button) rootView.findViewById(R.id.btagI_pesqcidade);
        bPesqCidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irIntermunicipal = new Intent(getActivity(), ListarIntermunicipal.class);
                startActivity(irIntermunicipal);
            }
        });

        return rootView;
    }
}
