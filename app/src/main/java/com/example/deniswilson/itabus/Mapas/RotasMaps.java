package com.example.deniswilson.itabus.Mapas;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.deniswilson.itabus.Administrador.BD;
import com.example.deniswilson.itabus.Administrador.Rotas;
import com.example.deniswilson.itabus.Tabs.InfoUsuarios;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RotasMaps extends SupportMapFragment implements OnMapReadyCallback {

    /*FragmentActivity, é para usar em versões anteriores dos aplicativos. */
    private GoogleMap mMap; /*Objeto, que contem informações relacionadas ao mapa
                            definindo coordenadas, marcar posição no mapa.*/


    private BD database;
    private SQLiteDatabase conexao;
    private Marker marker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }


    /*
    *  Método implementado pela interface OnMapReadyCallback,
    *  para realização de procedimentos, como marcação de localização no mapa
    *  ou outras informações. Método será executado quando o mapa for exibido
    *
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Cidade de Sidney (Teste)
        /*
        * Habilitando o botão de Zoom*/
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); /*Estilo do mapa*/

        /*LatLng sydney = new LatLng(-20.252740, -43.803107); /*Latitude e Longitude*/

        /*
        * Instanciando um marcador, que ficará posicionado sobre a cidade de Itabirito.
        * E adicionando o marcador, e posicionando no mapa na posição.
        *
        MarkerOptions marker = new MarkerOptions();
        marker.position(sydney);
        marker.title("Itabirito");
        mMap.addMarker(marker);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        CameraPosition position = new CameraPosition.Builder().target(sydney).zoom(15).build();
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
        mMap.moveCamera(update);*/

        exibirRotas();


    }


    public void exibirRotas() {
        try {
            /* Instanciando a classe infoUsuarios, e atribuindo os valores static */

            InfoUsuarios codRotas = new InfoUsuarios();
            String rotas = codRotas.rotas;

            database = new BD(getActivity()); // Criando a referencia do banco.
            conexao = database.getWritableDatabase();
            /*
            * Fazendo o select na tabela rotas para exibir as rotas.
            * */
            Cursor cursor = conexao.rawQuery("SELECT " + BD.COLUNA_IDR + ","
                    + BD.COLUNA_CODIGO_ROTAS + ","
                    + BD.COLUNA_LATITUDE_INICIAL + ","
                    + BD.COLUNA_LATITUDE_FINAL + ","
                    + BD.COLUNA_LONGITUDE_INICIAL + ","
                    + BD.COLUNA_LONGITUDE_FINAL +
                    " FROM " + BD.TABELA_ROTAS + " WHERE "
                    + BD.COLUNA_CODIGO_ROTAS + " = " + rotas, null);

            if (cursor.moveToFirst()) {

                /*
                * Tenho que passar para Double, pois minhas declarações foram Strings.
                *
                * 1° - Passagando a origem, no caso, todos os onibus se deslocam da garagem , usando o Marker.
                * 2° - Passando o destino, usando o addPolyline.
                * */

                LatLng origem = new LatLng(Double.parseDouble(cursor.getString(2)),
                        Double.parseDouble(cursor.getString(4)));

                mMap.addMarker(new MarkerOptions()
                                .position(origem)
                                .title("Origem")

                                            /*
                                            * Se eu quiser colocar umma imagem de ícone:
                                            * .icon(BitmapDescriptorFactory.fromResource(R.id.drowable...))
                                            * */

                );


                LatLng destino = new LatLng(Double.parseDouble(cursor.getString(3)),
                        Double.parseDouble(cursor.getString(5)));

                mMap.addMarker(new MarkerOptions()
                                .position(origem)
                                .title("Destino")

                                                        /*
                                                        * Se eu quiser colocar umma imagem de ícone:
                                                        * .icon(BitmapDescriptorFactory.fromResource(R.id.drowable...))
                                                        * */

                );

                mMap.addPolyline(new PolylineOptions().add(origem, destino)
                        .width(10)
                        .color(Color.BLUE)
                );

                /*Posicionando o mapa mais próximo da cidade de Itabirito.
                * Ou seja, ao invés de utilizar o mapa do país completo, aparecerá apenas o mapa da
                * cidade de Itabirito.
                * */
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(origem));

                CameraPosition position = new CameraPosition.Builder().target(origem).zoom(12).build();
                CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
                //mMap.moveCamera(update);

                /*Animaçao*/
                mMap.animateCamera(update, 3000, new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {
                    }

                    @Override
                    public void onCancel() {
                    }
                });

            } else {
                Toast.makeText(getActivity(), "Erro ao carregar rotas.", Toast.LENGTH_SHORT).show();
            }
            conexao.close();

        } catch (Exception e) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
            dlg.setMessage("Erro !" + e.getMessage()); //Retornando o erro que ocorrer.
            dlg.setNeutralButton("OK", null);
            dlg.show();
        }
    }




}








