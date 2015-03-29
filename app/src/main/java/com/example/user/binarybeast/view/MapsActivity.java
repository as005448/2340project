package com.example.user.binarybeast.view;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.user.binarybeast.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private String location;
    private String description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Bundle extras = getIntent().getExtras();
        location = (String)extras.get("location");
        description = (String)extras.get("description");
        setUpMapIfNeeded();
        Geocoder coder = new Geocoder(this);
        try {
            List<Address> addrs = coder.getFromLocation(33.755, -84.39, 2);
            for (Address add : addrs) {
                Toast t = Toast.makeText(this, add.toString(),Toast.LENGTH_LONG);
                t.show();
            }

            addrs = coder.getFromLocationName(location+" Atlanta", 4, 34, -85, 33.74, -84.3);
            for (Address add : addrs) {
                Toast t = Toast.makeText(this, add.toString(),Toast.LENGTH_LONG);
                t.show();
                addLoc(add);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        LatLng coords = new LatLng(33.755, -84.39);
        mMap.addMarker(new MarkerOptions().position(coords).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coords, 13));
    }

    private void addLoc(Address a) {
        LatLng coords = new LatLng(a.getLatitude(), a.getLongitude());
        mMap.addMarker(new MarkerOptions().position(coords).title("DEAL!!"));
    }
}
