package com.example.deniswilson.itabus.TabsPageAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.deniswilson.itabus.Listar.ListarMunicipal;
import com.example.deniswilson.itabus.Tabs.Inicio;
import com.example.deniswilson.itabus.Tabs.PesquisaBairro;

/**
 * Created by Denis Wilson on 08/10/2016.
 */
/*Funciona praticamente como BaseAdapter, uma lista, como aprendido em sala de aula*/
public class PageAdapter extends FragmentPagerAdapter{

    private String[] titulosTabs; /*Criando array de strings, para definir a quantidade de títulos*/

    public PageAdapter(FragmentManager fm, String[] titulosTabs ) {
        super(fm);
        this.titulosTabs = titulosTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Inicio(); /*Primeira tag, municipal*/
            case 1:
                return new PesquisaBairro(); /*Segunda tag, pesquisar*/
            default:
                return null;
        }
    }

    /*Utilizando o método getPageAdapter, para pegar o nome
    * da página e seta na tab*/

    @Override
    public CharSequence getPageTitle(int position) {
        return this.titulosTabs[position];
    }

    @Override
    /*Passando a quantidade de tags que irei utilizar, através do array de string com os títulos
    * ou seja, a quantidade de título que passar, será a quantidade de tags.*/
    public int getCount() {
        return this.titulosTabs.length;
    }
}
