package uche.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

//using the places SDK
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;

import static android.os.Build.VERSION_CODES.M;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;

    private static String apiKey = "AIzaSyD7IsrpwbdoavWSJKJlNjraoDNf1xR-Enk";

    private GoogleApiClient gac;

    private LocationRequest lr;

    private Location lastLocation;

    private Marker currentuserLocationmarker;

    private static final int  request_user_location_code = 99;


    // Initialize Places
    private Places p;

    // Create a new Places client instance.
    private PlacesClient placesClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        if (Build.VERSION.SDK_INT >= M){

            checkuserLocationPermission();
        }


        p.initialize(getApplicationContext(), apiKey);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {



            buildgoogleapiclient();

            mMap.setMyLocationEnabled(true);

        }

    }




    public boolean checkuserLocationPermission(){

      if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){

          if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){

              ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION },request_user_location_code);

          }else {

              ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION },request_user_location_code);

          }

           return false;
      }else{

          return  true;
      }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


       // super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){

              case request_user_location_code:
                 if(grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){

                     if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                         if(gac == null){

                             buildgoogleapiclient();

                         }
                         mMap.setMyLocationEnabled(true);
                     }else{

                         Toast.makeText(this,"Permission Denied..",Toast.LENGTH_LONG);
                     }
                     return;

                 }



        }




    }











    //connect with google api client
    protected synchronized void buildgoogleapiclient() {


        gac = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();


        gac.connect();
    }


    @Override
    public void onLocationChanged(Location location) {

        lastLocation = location;


        if(currentuserLocationmarker != null){

            currentuserLocationmarker.remove();
        }


        LatLng latLong  =  new LatLng(location.getLatitude(),location.getLongitude());

        MarkerOptions mo =  new MarkerOptions();
        mo.position(latLong);   //set postiton of latitude and longtiude

        mo.title("current person: Me");
        mo.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

        //add user loaction to location marker
        currentuserLocationmarker = mMap.addMarker(mo);


         //movemment of map camera and Zoom.
         mMap.moveCamera(CameraUpdateFactory.newLatLng(latLong));
         mMap.animateCamera(CameraUpdateFactory.zoomBy(14));  //float


        //clear Location updates

        if(gac != null){

            LocationServices.FusedLocationApi.removeLocationUpdates(gac,this);

        }


    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

        lr = new LocationRequest();
        lr.setInterval(1100);     //time in milliseconds
        lr.setFastestInterval(1100);
        lr.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        //fusedlocationapi will get current locationn of user/device on map.

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            LocationServices.FusedLocationApi.requestLocationUpdates(gac, lr, this);


        }


    }







    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
