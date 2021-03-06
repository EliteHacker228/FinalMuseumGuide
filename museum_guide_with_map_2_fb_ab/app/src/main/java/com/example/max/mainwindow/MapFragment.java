package com.example.max.mainwindow;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.max.mainwindow.R;
import com.example.max.mainwindow.museumpackage.ListFragment;
import com.example.max.mainwindow.museumpackage.Museum;
import com.example.max.mainwindow.museumpackage.MuseumAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.TreeMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    ArrayList<Museum> museums = new ArrayList<>();
    TreeMap<String, Museum> musemsTree  = new TreeMap<>();



    public MapFragment() {


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(museums.isEmpty()) {
            museums = MainActivity.getMuseums();
        }
        View view= inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        return view;
    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMinZoomPreference(11.0f);


        LatLng Ural= new LatLng(56.841039, 60.622288);
        LatLng ekaterinburg= new LatLng(56.837319, 60.605603);



        for (Museum m: museums){
            LatLng name= new LatLng(m.getV(), m.getV1());
            mMap.addMarker(new MarkerOptions().position(name).title(m.getMname()));
        }

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ekaterinburg));
    }


    }






