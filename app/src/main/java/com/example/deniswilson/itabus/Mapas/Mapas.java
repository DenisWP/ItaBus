package com.example.deniswilson.itabus.Mapas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.deniswilson.itabus.MainActivity;
import com.example.deniswilson.itabus.MainActivityInfo;
import com.example.deniswilson.itabus.R;

/**
 * Created by Denis Wilson on 01/11/2016.
 */

public class Mapas extends AppCompatActivity {

    private FragmentManager fragmentManager; /*Responsável para remover ou alterar um fragmento*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_rota_mapa);

        fragmentManager = getSupportFragmentManager(); // Recuperando o objeto fragmentManager.

        /*Iniciar uma transação, para add um fragmento no meu activity*/
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        /*Add o fragmento ao meu activity*/
        transaction.add(R.id.exbMapa, new RotasMaps(),"MapsFragment");
        /*Confirmando a alteração*/
        transaction.commitAllowingStateLoss();
    }

    public void voltarInfo(View view){
        Intent irInfo = new Intent();
        irInfo.setClass(this, MainActivityInfo.class ); //Setar a classe
        startActivity(irInfo); // Iniciar a intent
    }
}
