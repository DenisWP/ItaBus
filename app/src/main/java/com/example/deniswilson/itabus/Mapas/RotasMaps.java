package com.example.deniswilson.itabus.Mapas;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.deniswilson.itabus.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
