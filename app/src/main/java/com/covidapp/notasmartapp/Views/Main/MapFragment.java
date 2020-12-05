package com.covidapp.notasmartapp.Views.Main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.covidapp.notasmartapp.Interfaces.MainContract;
import com.covidapp.notasmartapp.POJO.Result;
import com.covidapp.notasmartapp.Presenters.MapPresenter;
import com.covidapp.notasmartapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class MapFragment extends Fragment implements MainContract.MapView {

    private final String TAG = "map fragment";
    public GoogleMap map=null;
    private MapPresenter presenter;
    private final int FINE_LOCATION_REQ_CODE = 1;
    private FusedLocationProviderClient locationProviderClient;
    private final int COARSE_LOCATION_REQ_CODE = 2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map,container,false);
        SupportMapFragment mapFragment = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map));
        presenter = new MapPresenter(this,getContext(),getActivity());
        locationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map=googleMap;
                Log.d(TAG, "onMapReady:value if map is : "+map);

            }

        });
        
        getCurrentLocation();

        return view;
    }
    
    private void getCurrentLocation(){
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{ Manifest.permission.ACCESS_FINE_LOCATION},FINE_LOCATION_REQ_CODE);
        }else if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},COARSE_LOCATION_REQ_CODE);
        }else{
            Log.d(TAG, "getCurrentLocation: all permission check");
            locationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null){
                        presenter.loadLocation(location);
                    }else{
                        Toast.makeText(getContext(),"cant get the current location",Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case FINE_LOCATION_REQ_CODE:
            case COARSE_LOCATION_REQ_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getCurrentLocation();
                }else{
                    Toast.makeText(getContext(),"Permission Required",Toast.LENGTH_LONG).show();
                }
                break;


        }
    }

    @Override
    public void onSuccess(List<Result> loclist) {
        LatLng latLng=null;
        LatLngBounds.Builder builder = LatLngBounds.builder();
        for(Result result : loclist){
            latLng= new LatLng(result.getGeometry().getLocation().getLat(),result.getGeometry().getLocation().getLng());
            MarkerOptions options = new MarkerOptions().position(latLng).title(result.getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            map.addMarker(options);
            builder.include(latLng);

        }
        LatLngBounds bounds = builder.build();
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 25,25,1);
        map.animateCamera(cu);


    }

    @Override
    public void onFailed(String error) {


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
