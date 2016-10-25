package com.example.deniswilson.itabus.TabsPageAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.deniswilson.itabus.Tabs.InfoHorarios;
import com.example.deniswilson.itabus.Tabs.InfoUsuarios;

/**
 * Created by Denis Wilson on 24/10/2016.
 */

public class PageAdapterInfo  extends FragmentPagerAdapter{

    private String[] titulosTabInfo; // Para passar a quantidade de títulos.

    public PageAdapterInfo(FragmentManager fm, String[] titulosTabInfo) {
        super(fm);
        this.titulosTabInfo = titulosTabInfo;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new InfoHorarios(); /*Primeira tag, horários*/
            case 1:
                return new InfoUsuarios(); /*Segunda tag, Informações*/
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.titulosTabInfo.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.titulosTabInfo[position];
    }

}
