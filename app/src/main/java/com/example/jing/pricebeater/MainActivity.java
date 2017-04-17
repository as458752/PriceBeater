package com.example.jing.pricebeater;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jing.myapplication.backend.myApi.model.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LocationManager locationManager;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 0;
    private static final String TAG = "MainActivity";
    Location cur_location = null;
    List<String> shoppingList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                if(shoppingList == null){
                    shoppingList = new ArrayList<>();
                }
                shoppingList.add(((EditText)findViewById(R.id.editText)).getText().toString());
                shoppingList.add(((EditText)findViewById(R.id.editText2)).getText().toString());

                JsonObject requestJson = new JsonObject();


                JsonObject locationJson = new JsonObject();
                locationJson.put("latitude", String.valueOf(cur_location.getLatitude()));
                locationJson.put("longtitude", String.valueOf(cur_location.getLongitude()));
                requestJson.put("shoppingList", shoppingList);
                requestJson.put("location", locationJson);

                new EndpointsAsyncTask().execute(requestJson);

            }
        });



        locationInit();


    }

    private void locationInit() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            private Context context;
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
               // makeUseOfNewLocation(location);
                cur_location = location;
                String result = "Latitude: "+location.getLatitude()+". getLongitude: "+location.getLongitude();
                Toast.makeText(context, result, Toast.LENGTH_LONG).show();
                LOGD(result);

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

// Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        String locationProvider = LocationManager.NETWORK_PROVIDER;
// Or use LocationManager.GPS_PROVIDER
        boolean network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if(network_enabled) {
            cur_location = locationManager.getLastKnownLocation(locationProvider);
            String result = "Latitude: " + cur_location.getLatitude() + ". getLongitude: " + cur_location.getLongitude();
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            LOGD(result);
        }else{
            LOGD("network is not enabled");
        }
        boolean gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(gps_enabled) {
            cur_location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            String result = "Latitude: " + cur_location.getLatitude() + ". getLongitude: " + cur_location.getLongitude();
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            LOGD(result);
        }else{
            LOGD("gps is not enabled");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void LOGD(String str){
        Log.d(TAG, str);
    }

}
