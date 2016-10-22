package com.example.deniswilson.itabus.Tabs;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.deniswilson.itabus.Listar.ListarMunicipal;
import com.example.deniswilson.itabus.R;

/**
 * Created by Denis Wilson on 08/10/2016.
 */

public class Inicio extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_tela_inicial, container, false);

        /*
        *  Criando função OnclickListener, para chamar a tela de lista. Porque estou usando o Fragment
        *
        bPesqBairro = (Button) rootView.findViewById(R.id.btagM_pesqbairro);
        bPesqBairro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irMunicipal = new Intent(getActivity(), ListarMunicipal.class);
                startActivity(irMunicipal);
            }
        });*/

        return rootView;
    }

}
