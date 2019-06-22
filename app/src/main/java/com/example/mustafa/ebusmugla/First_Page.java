package com.example.mustafa.ebusmugla;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentController;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class First_Page extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner from_spin, to_spin;
    Button search;
    String destin_from, destin_to;
    Marker marker_from, marker_to;
    myDbAdapter myDbAdapter;
    Map map;
    LatLng[] positions = {new LatLng(37.212661, 28.362642),
                          new LatLng(37.168797, 28.382023),
                          new LatLng(37.165777, 28.403449),
                          new LatLng(37.182292, 28.364781),
                          new LatLng(37.164527, 28.367450)
                         };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first__page);
        myDbAdapter = new myDbAdapter(this);
        map = new Map();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragment,map).commit();


        from_spin = findViewById(R.id.from_spin);
        ArrayAdapter<CharSequence> adap_from = ArrayAdapter.createFromResource(this, R.array.from, R.layout.spinnerlay);
        adap_from.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from_spin.setAdapter(adap_from);
        from_spin.setOnItemSelectedListener(this);

        to_spin = findViewById(R.id.to_spin);
        ArrayAdapter<CharSequence> adap_to = ArrayAdapter.createFromResource(this, R.array.to, R.layout.spinnerlay);
        adap_to.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        to_spin.setAdapter(adap_to);
        to_spin.setOnItemSelectedListener(this);

        search = findViewById(R.id.search_but);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myDbAdapter.isTableExists(destin_from+destin_to)){
                    Intent intent = new Intent(First_Page.this, Second_Page.class);
                    intent.putExtra("destin_from", destin_from);
                    intent.putExtra("destin_to", destin_to);
                    startActivity(intent);
                }else if(destin_from.equals(destin_to)){
                    Toast.makeText(getApplicationContext(), "Wrong choice!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "To get "+ destin_to+ " from " + destin_from + ", first you need to go Merkez", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getId() == R.id.from_spin){
            if(marker_from != null)
                marker_from.remove();
            destin_from = adapterView.getItemAtPosition(i).toString();
            marker_from = map.setMarker(positions[i], destin_from);
        }else if(adapterView.getId() == R.id.to_spin){
            if(marker_to != null)
                marker_to.remove();
            destin_to = adapterView.getItemAtPosition(i).toString();
            marker_to = map.setMarker(positions[i], destin_to);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
