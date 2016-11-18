package com.example.deniswilson.itabus.Tabs;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.deniswilson.itabus.Listar.ListarMunicipal;
import com.example.deniswilson.itabus.R;

/**
 * Created by Denis Wilson on 08/10/2016.
 */

public class Inicio extends Fragment {

    ImageButton ligue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.act_tela_inicial, container, false);


        ligue = (ImageButton) rootView.findViewById(R.id.imgligue);
        ligue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("tel: 08000246844");
                Intent intent = new Intent(Intent.ACTION_CALL,uri);
                startActivity(intent);
            }
        });

        return rootView;
    }

}
