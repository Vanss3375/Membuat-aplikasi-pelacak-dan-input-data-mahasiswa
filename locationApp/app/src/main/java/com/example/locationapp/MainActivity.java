package com.example.locationapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private GeoPoint binusBandung = new GeoPoint(-6.9153653, 107.5886954);
    private GeoPoint braga = new GeoPoint(-6.9178283, 107.6045685);
    private GeoPoint alunAlun = new GeoPoint(-6.9218295, 107.6021967);
    private GeoPoint gazibu = new GeoPoint(-6.9002779, 107.6161296);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize OSMDroid configuration
        Configuration.getInstance().load(getApplicationContext(), androidx.preference.PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        mapView = findViewById(R.id.map);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);

        // Marker for Binus Bandung
        Marker binusMarker = new Marker(mapView);
        binusMarker.setPosition(binusBandung);
        binusMarker.setTitle("Binus Bandung\n-6.9153653, 107.5886954");
        binusMarker.setIcon(getResources().getDrawable(org.osmdroid.library.R.drawable.person));
        mapView.getOverlays().add(binusMarker);

        // Marker for Braga
        Marker bragaMarker = new Marker(mapView);
        bragaMarker.setPosition(braga);
        bragaMarker.setTitle("Jalan Braga\n-6.9178283, 107.6045685");
        bragaMarker.setIcon(getResources().getDrawable(org.osmdroid.library.R.drawable.person));
        mapView.getOverlays().add(bragaMarker);

        // Marker for Alun-Alun Kota Bandung
        Marker alunMarker = new Marker(mapView);
        alunMarker.setPosition(alunAlun);
        alunMarker.setTitle("Alun-Alun Kota Bandung\n-6.9218295, 107.6021967");
        alunMarker.setIcon(getResources().getDrawable(org.osmdroid.library.R.drawable.person));
        mapView.getOverlays().add(alunMarker);

        // Marker for Lapangan Gazibu
        Marker gazibuMarker = new Marker(mapView);
        gazibuMarker.setPosition(gazibu);
        gazibuMarker.setTitle("Lapangan Gazibu\n-6.9002779, 107.6161296");
        gazibuMarker.setIcon(getResources().getDrawable(org.osmdroid.library.R.drawable.person));
        mapView.getOverlays().add(gazibuMarker);

        mapView.getController().setCenter(binusBandung);
        mapView.getController().setZoom(15.0);

        // Button click listeners
        findViewById(R.id.Binus).setOnClickListener(v -> recenterMap(binusBandung));
        findViewById(R.id.Braga).setOnClickListener(v -> recenterMap(braga));
        findViewById(R.id.AlunAlun).setOnClickListener(v -> recenterMap(alunAlun));
        findViewById(R.id.Gazibu).setOnClickListener(v -> recenterMap(gazibu));
    }

    private void recenterMap(GeoPoint geoPoint) {
        mapView.getController().animateTo(geoPoint);
        mapView.getController().setZoom(15.0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
}
