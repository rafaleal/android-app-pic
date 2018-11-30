package com.android.utp.praticasintegrativascomplementares.map;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.utp.praticasintegrativascomplementares.R;
import com.android.utp.praticasintegrativascomplementares.health_unit.HealthUnitActivity;
import com.android.utp.praticasintegrativascomplementares.models.ubs.GetUBSResponse;
import com.android.utp.praticasintegrativascomplementares.models.ubs.UBS;
import com.android.utp.praticasintegrativascomplementares.network.NetworkManager;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapViewFragment extends Fragment implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {
    MapView mMapView;
    private GoogleMap mMap;
    private List<MarkerOptions> markerOptionsList;
    Intent intent;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Map");

        this.getData();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);

        return view;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (MarkerOptions markerOptions : markerOptionsList) {
            Log.d("MarkerOptions", markerOptions.getPosition().toString());
            mMap.addMarker(markerOptions);
        }
        LatLng curitiba = new LatLng(-25.4347208,-49.2913352);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(curitiba).zoom(11.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);
//        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
        mMap.setOnInfoWindowClickListener(this);
    }

    private void createMapMarkers(List<UBS> ubsList) {
        markerOptionsList = new ArrayList<>();

        for (UBS ubs: ubsList) {
            LatLng latLng = new LatLng(Double.valueOf(ubs.getLongitude()), Double.valueOf(ubs.getLatitude()));
            MarkerOptions newMarkerOption = new MarkerOptions()
                    .position(latLng)
                    .title(ubs.getNome())
                    .snippet(ubs.getEndereco());
            markerOptionsList.add(newMarkerOption);
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        intent = new Intent(getActivity(), HealthUnitActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.map_toolbar, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Map");
    }

    private void getData() {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), "1");
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        requestBodyMap.put("distrito_id", body);
        NetworkManager.service().getUbs(requestBodyMap).enqueue(new Callback<GetUBSResponse>() {
            @Override
            public void onResponse(final Call<GetUBSResponse> call, final Response<GetUBSResponse> response) {
                List<UBS> ubsList = response.body().getObjectPhp().getArray();
                createMapMarkers(ubsList);
            }

            @Override
            public void onFailure(Call<GetUBSResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Erro ao receber resposta!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private final View mWindow;

        private final View mContents;

        CustomInfoWindowAdapter() {
            mWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);
            mContents = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            render(marker, mWindow);
            return mWindow;
        }

        @Override
        public View getInfoContents(Marker marker) {
            render(marker, mContents);
            return mContents;
        }
    }

    private void render(Marker marker, View view) {
        int imagePic = R.drawable.ic_us_hauer;
        ((ImageView) view.findViewById(R.id.badge)).setImageResource(imagePic);

        String title = marker.getTitle();
        TextView titleUi = view.findViewById(R.id.title);
        titleUi.setText(title);


    }
}
