package com.bintracking.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bintracking.R;
import com.bintracking.data.User;
import com.bintracking.util.GoogleMapHelper;

import com.bintracking.clusterRenderer.MarkerClusterRenderer;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.maps.android.clustering.ClusterManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MapActivity extends AppCompatActivity {

    //get instance
    LocationManager locationManager;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    Marker marker;
    LocationListener locationListener;
    FirebaseAuth auth;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_map);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        //navigation view hide
        getSupportActionBar().hide();
        //getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        assert supportMapFragment != null;
        supportMapFragment.getMapAsync(googleMap -> {
            GoogleMapHelper.defaultMapSettings(googleMap);
            setUpClusterManager(googleMap);
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]
                                {Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_PERMISSION);
            }
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    //get the location name from latitude and longitude
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addresses=geocoder.getFromLocation(latitude, longitude, 1);

                        String place = addresses.get(0).getLocality() + ":";

                        place += addresses.get(0).getCountryName();

                        LatLng latLng = new LatLng(latitude, longitude);

                        if (marker != null) {
                            marker.remove();
                            marker = googleMap.addMarker(new MarkerOptions().position(latLng).title(place));
                            googleMap.setMaxZoomPreference(100);
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13.0f));
                        } else {
                            marker = googleMap.addMarker(new MarkerOptions().position(latLng).title(place));
                            googleMap.setMaxZoomPreference(100);
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13.0f));
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);



        });

    }

    private void setUpClusterManager(GoogleMap googleMap) {
        ClusterManager<User> clusterManager = new ClusterManager<>(this, googleMap);


        MarkerClusterRenderer markerClusterRenderer = new MarkerClusterRenderer(this, googleMap, clusterManager);

        clusterManager.setRenderer(markerClusterRenderer);

        List<User> items = getItems();

        clusterManager.addItems(items);

        clusterManager.cluster();
    }


    private List<User> getItems() {
        return Arrays.asList(
                new User("45%", new LatLng(27.699456, 85.351058), "Nearest"),
                new User("25%", new LatLng(27.699148, 85.351369), "Nearest"),
                new User("30%", new LatLng(27.699183, 85.351520), "Nearest"),
                new User("50%", new LatLng(27.699338, 85.350456), "Nearest"),
                new User("23%", new LatLng(27.700769, 85.300140), "Far")


        );
    }
    @Override
    protected void onResume() {
        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(MapActivity.this, LoginActivity.class));
            finish();
        }
        super.onResume();
    }

}
