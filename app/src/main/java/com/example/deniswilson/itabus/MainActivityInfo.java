package com.example.deniswilson.itabus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.deniswilson.itabus.Administrador.ActCadEmpresa;
import com.example.deniswilson.itabus.Administrador.ActCadHorarios;
import com.example.deniswilson.itabus.Administrador.ActCadMunicipal;
import com.example.deniswilson.itabus.TabsPageAdapter.PageAdapter;

public class MainActivityInfo extends AppCompatActivity {

    private TabLayout infotablayout; /*Variável para a TabLayout*/
    private ViewPager viewpagerInfo; /*Variável para as viewpager*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maininfo);
        Toolbar toolbarInfo = (Toolbar) findViewById(R.id.toolbarInfo);
        setSupportActionBar(toolbarInfo);

        infotablayout = (TabLayout) findViewById(R.id.tab_layoutInfo); /*referenciando os componentes*/
        viewpagerInfo = (ViewPager) findViewById(R.id.view_pagerInfo); /**/

        /*
        * Setando o adapter, para o viewpager. O que vai gerar as view pager.
        * e pegando as strings atraves do método getResources, e acesso os métodos
        * e pego o array de strings.
        * */
        viewpagerInfo.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.titulos_tag_pesqInfo)));
        /*
        *dizendo para o tablayout, que ele irá trabalhar junto com o view pager.
        * */
        infotablayout.setupWithViewPager(viewpagerInfo);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabinfo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true; /*Falso para versão usuário
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Comparação para chamar as classes específicas.
        if (id == R.id.menu_cadMunicipal) {
            Intent irCadM = new Intent();
            irCadM.setClass(this, ActCadMunicipal.class); //Setar a classe cadCliente
            startActivity(irCadM); // I
        }else {
            if (id == R.id.menu_cadEmpresa){
                Intent irCadI = new Intent();
                irCadI.setClass(this, ActCadEmpresa.class); //Setar a classe cadCliente
                startActivity(irCadI); //
            }else {
                if(id == R.id.menu_cadHorarios){
                    Intent irCadH = new Intent();
                    irCadH.setClass(this,ActCadHorarios.class);
                    startActivity(irCadH);
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }*/
}
