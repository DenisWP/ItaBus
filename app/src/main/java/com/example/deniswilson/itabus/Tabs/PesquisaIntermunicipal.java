package com.example.deniswilson.itabus.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deniswilson.itabus.R;

/**
 * Created by Denis Wilson on 08/10/2016.
 */

public class PesquisaIntermunicipal extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*Retornando a act_tag_intermunicipa, quando for acionado*/
        return inflater.inflate(R.layout.act_tag_intermunicipal, container, false);
    }
}
