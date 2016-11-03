package com.example.deniswilson.itabus.Mapas;


import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class RotasMaps extends SupportMapFragment implements OnMapReadyCallback {

    /*FragmentActivity, é para usar em versões anteriores dos aplicativos. */
    private GoogleMap mMap; /*Objeto, que contem informações relacionadas ao mapa
                            definindo coordenadas, marcar posição no mapa.*/



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

        LatLng sydney = new LatLng(-20.252740, -43.803107); /*Latitude e Longitude*/

        /*
        * Instanciando um marcador, que ficará posicionado sobre a cidade de Itabirito.
        * E adicionando o marcador, e posicionando no mapa na posição.
        * */
        MarkerOptions marker = new MarkerOptions();
        marker.position(sydney);
        marker.title("Itabirito");
        mMap.addMarker(marker);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        /*Posicionando o mapa mais próximo da cidade de Itabirito.
        * Ou seja, ao invés de utilizar o mapa do país completo, aparecerá apenas o mapa da
        * cidade de Itabirito.
        * */
        CameraPosition position = new CameraPosition.Builder().target(sydney).zoom(15).build();
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
        mMap.moveCamera(update);

        /*https://www.youtube.com/watch?v=CCZPUeY94MU*/
    }



}
