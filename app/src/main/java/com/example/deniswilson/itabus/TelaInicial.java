package com.example.deniswilson.itabus;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Denis Wilson on 09/10/2016.
 */

public class TelaInicial extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_tela_inicial);
    }

    public void IrMainActivity (View v){
        Intent irMain = new Intent();
        irMain.setClass(this, MainActivity.class ); //Setar a classe cadCliente
        startActivity(irMain); // Iniciar a intent
    }
}
