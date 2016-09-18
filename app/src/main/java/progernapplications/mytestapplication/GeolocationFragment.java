package progernapplications.mytestapplication;

import android.Manifest;
import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.OnReverseGeocodingListener;
import io.nlopez.smartlocation.SmartLocation;


public class GeolocationFragment extends Fragment {

    private LocationManager mLocationManager;
    private TextView locationText;
    Location currentLocation;

    String locText = "1234";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_geolocation, container, false);

        mLocationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        locationText = (TextView) view.findViewById(R.id.locationTextView);
        boolean gps_enabled = false;
        boolean network_location_enabled = false;

        PermissionListener mPermissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Snackbar positiveSnackBar = Snackbar.make(view, "Permission granted", Snackbar.LENGTH_SHORT);
                positiveSnackBar.show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Snackbar negativeSnackBar = Snackbar.make(view, "No permission to read files from storage", Snackbar.LENGTH_LONG);
                negativeSnackBar.show();
            }
        };

        new TedPermission(getActivity().getApplicationContext())
                .setPermissionListener(mPermissionListener)
                .setDeniedMessage("If you deny this permission, the application won't be able to get your location details")
                .setPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .check();


        // TODO Airplane mode
        gps_enabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        network_location_enabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!gps_enabled && !network_location_enabled) {
            Snackbar negativeSnackBar = Snackbar.make(view, "No GPS or Network connection established", Snackbar.LENGTH_LONG);
            negativeSnackBar.show();
        } else {
            Snackbar positiveSnackBar = Snackbar.make(view, "GPS & Network connection established", Snackbar.LENGTH_LONG);
            positiveSnackBar.show();

            SmartLocation.with(getContext()).location()
                    .oneFix()
                    .start(new OnLocationUpdatedListener() {
                        @Override
                        public void onLocationUpdated(Location location) {

                            SmartLocation.with(getContext()).geocoding()
                                    .reverse(location, new OnReverseGeocodingListener() {
                                        @Override
                                        public void onAddressResolved(Location location, List<Address> list) {
                                            Address currentAddress = list.get(0);
                                            locationText.setText(currentAddress.getCountryName() + " , " + currentAddress.getLocality()
                                            +" , " + currentAddress.getThoroughfare() + " " + currentAddress.getFeatureName());
                                        }

                                    });
                        }
                    });

        }

        return view;
    }



}


