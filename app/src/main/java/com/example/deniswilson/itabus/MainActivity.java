package com.example.deniswilson.itabus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.deniswilson.itabus.Administrador.ActCadEmpresa;
import com.example.deniswilson.itabus.Administrador.ActCadHorarios;
import com.example.deniswilson.itabus.Administrador.ActCadMunicipal;
import com.example.deniswilson.itabus.Administrador.ActCadRotas;
import com.example.deniswilson.itabus.TabsPageAdapter.PageAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout mtablayout; /*Variável para a TabLayout*/
    private ViewPager mviewpager; /*Variável para as viewpager*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mtablayout = (TabLayout) findViewById(R.id.tab_layout); /*referenciando os componentes*/
        mviewpager = (ViewPager) findViewById(R.id.view_pager); /**/

        /*
        * Setando o adapter, para o viewpager. O que vai gerar as view pager.
        * e pegando as strings atraves do método getResources, e acesso os métodos
        * e pego o array de strings.
        * */
        mviewpager.setAdapter(new PageAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.titulos_tag_pesq)));
        /*
        *dizendo para o tablayout, que ele irá trabalhar junto com o view pager.
        * */
        mtablayout.setupWithViewPager(mviewpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true; /*Falso para versão usuário*/
    }

    @Override
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
                }else {
                    if(id == R.id.menu_cadRotas){
                        Intent irCadR = new Intent();
                        irCadR.setClass(this, ActCadRotas.class);
                        startActivity(irCadR);
                    }
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
