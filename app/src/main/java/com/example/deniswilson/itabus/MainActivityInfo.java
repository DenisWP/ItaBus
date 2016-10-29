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
import com.example.deniswilson.itabus.TabsPageAdapter.PageAdapterInfo;

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
        viewpagerInfo.setAdapter(new PageAdapterInfo(getSupportFragmentManager(), getResources().getStringArray(R.array.titulos_tag_pesqInfo)));
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
}
