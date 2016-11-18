package com.example.deniswilson.itabus.Mapas;


import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.deniswilson.itabus.Administrador.BD;
import com.example.deniswilson.itabus.Administrador.Rotas;
import com.example.deniswilson.itabus.R;
import com.example.deniswilson.itabus.Tabs.InfoUsuarios;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RotasMaps extends SupportMapFragment implements OnMapReadyCallback {

    /*FragmentActivity, é para usar em versões anteriores dos aplicativos. */
    private GoogleMap mMap; /*Objeto, que contem informações relacionadas ao mapa
                            definindo coordenadas, marcar posição no mapa.*/


    private BD database;
    private SQLiteDatabase conexao;
    private Marker marker;


    private Polyline polyline;
    private List<LatLng> list;
    private long distance;

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
        /*Simbolo da minha localização*/
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
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
            Cursor cursor = conexao.rawQuery("SELECT "  + BD.COLUNA_IDR + ","
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
                getRoute(new LatLng(Double.parseDouble(cursor.getString(2)),
                         Double.parseDouble(cursor.getString(4))),
                         new LatLng(Double.parseDouble(cursor.getString(3)),
                         Double.parseDouble(cursor.getString(5)))
                );


                LatLng origem = new LatLng(Double.parseDouble(cursor.getString(2)),
                                           Double.parseDouble(cursor.getString(4)));

                mMap.addMarker(new MarkerOptions()
                                .position(origem)
                                .title("Origem")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.markori))

                                            /*
                                            * Se eu quiser colocar umma imagem de ícone:
                                            * .icon(BitmapDescriptorFactory.fromResource(R.id.drowable...))
                                            * */

                );


                LatLng destino = new LatLng(Double.parseDouble(cursor.getString(3)),
                        Double.parseDouble(cursor.getString(5)));

                mMap.addMarker(new MarkerOptions()
                                .position(destino)
                                .title("Destino")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.markdest))

                                                        /*
                                                        * Se eu quiser colocar umma imagem de ícone:
                                                        * .icon(BitmapDescriptorFactory.fromResource(R.id.drowable...))
                                                        * */

                );

                /*Posicionando o mapa mais próximo da cidade de Itabirito.
                * Ou seja, ao invés de utilizar o mapa do país completo, aparecerá apenas o mapa da
                * cidade de Itabirito.
                * */
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(origem));

                CameraPosition position = new CameraPosition.Builder().target(origem).zoom(14).build();
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







    public void drawRoute(){
        PolylineOptions po;

        if(polyline == null){
            po = new PolylineOptions();

            for(int i = 0, tam = list.size(); i < tam; i++){
                po.add(list.get(i));
            }

            po.color(Color.BLUE).width(4);
            polyline = mMap.addPolyline(po);
        }
        else{
            polyline.setPoints(list);
        }
    }





    // WEB CONNECTION
    //public void getRoute(final String origin, final String destination){
    public void getRoute(final LatLng origin, final LatLng destination){
        new Thread(){
            public void run(){
						/*String url= "http://maps.googleapis.com/maps/api/directions/json?origin="
								+ origin+"&destination="
								+ destination+"&sensor=false";*/
                String url= "http://maps.googleapis.com/maps/api/directions/json?origin="
                        + origin.latitude+","+origin.longitude+"&destination="
                        + destination.latitude+","+destination.longitude+"&sensor=false";


                HttpResponse response;
                HttpGet request;
                DefaultHttpClient client =  new DefaultHttpClient();


                request = new HttpGet(url);
                try {
                    response = client.execute(request);
                    final String answer = EntityUtils.toString(response.getEntity());

                    getActivity().runOnUiThread(new Runnable(){
                        public void run(){
                            try {
                                //Log.i("Script", answer);
                                list = buildJSONRoute(answer);
                                drawRoute();
                            }
                            catch(JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }




    // PARSER JSON
    public List<LatLng> buildJSONRoute(String json) throws JSONException{
        JSONObject result = new JSONObject(json);
        JSONArray routes = result.getJSONArray("routes");

        distance = routes.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONObject("distance").getInt("value");

        JSONArray steps = routes.getJSONObject(0).getJSONArray("legs").getJSONObject(0).getJSONArray("steps");
        List<LatLng> lines = new ArrayList<LatLng>();

        for(int i=0; i < steps.length(); i++) {
            Log.i("Script", "STEP: LAT: "+steps.getJSONObject(i).getJSONObject("start_location").getDouble("lat")+" | LNG: "+steps.getJSONObject(i).getJSONObject("start_location").getDouble("lng"));


            String polyline = steps.getJSONObject(i).getJSONObject("polyline").getString("points");

            for(LatLng p : decodePolyline(polyline)) {
                lines.add(p);
            }

            Log.i("Script", "STEP: LAT: "+steps.getJSONObject(i).getJSONObject("end_location").getDouble("lat")+" | LNG: "+steps.getJSONObject(i).getJSONObject("end_location").getDouble("lng"));
        }

        return(lines);
    }




    // DECODE POLYLINE
    private List<LatLng> decodePolyline(String encoded) {

        List<LatLng> listPoints = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)), (((double) lng / 1E5)));
            Log.i("Script", "POL: LAT: "+p.latitude+" | LNG: "+p.longitude);
            listPoints.add(p);
        }
        return listPoints;
    }











































}








