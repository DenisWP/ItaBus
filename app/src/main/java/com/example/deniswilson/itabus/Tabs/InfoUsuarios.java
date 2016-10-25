package com.example.deniswilson.itabus.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deniswilson.itabus.R;

/**
 * Created by Denis Wilson on 24/10/2016.
 */

public class InfoUsuarios extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_tela_infousuarios, container, false);

        return rootView;
    }
}
