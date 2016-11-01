package com.example.deniswilson.itabus.Mapas;


import android.os.Bundle;
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
        // Cidade de Sidney (Teste)

        /*
        * Habilitando o botão de Zoom*/
        mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng sydney = new LatLng(-33.87365, 151.20689);

        MarkerOptions marker = new MarkerOptions();
        marker.position(sydney);
        marker.title("Teste");

        mMap.addMarker(marker);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


    }
}
