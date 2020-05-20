package com.example.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private SensorManager sensorManager;
    private List<Sensor> sensorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        List<String> names = new ArrayList<String>();

        for (Sensor s : sensorList) {
            names.add(s.getName() + " - " + s.getVendor() + " - " + s.getType());
        }

        ListView listView = (ListView) findViewById(R.id.sensorsList);
        listView.setOnItemClickListener(this);
        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layout, names);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Sensor sensor = sensorList.get(i);
        String msg = sensor.getName() + " - " + sensor.getVendor();
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SensorActivity.class);
        intent.putExtra("position", i);
        startActivity(intent);
    }
}
