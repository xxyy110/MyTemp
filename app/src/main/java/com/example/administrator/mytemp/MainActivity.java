/*
 * Copyright (c) MyPrivateCo.
 * Author:           Administrator
 * Last Change : 10/06/16 3:45 PM
 * All Rights Reserved.
 */

package com.example.administrator.mytemp;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Entry point of this app
 */
public class MainActivity extends AppCompatActivity  {

    private SensorManager sensorManager;
    private Sensor tempSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager)this.getSystemService(this.SENSOR_SERVICE);
        tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (tempSensor != null) {
            sensorManager.registerListener(tempSensorListener, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(this, "Temperature Sensor Not Found", Toast.LENGTH_LONG).show();
        }
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater actionBarMenuInflater = getMenuInflater();
        actionBarMenuInflater.inflate(R.menu.actionbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                return true;
            case R.id.action_settings:
                return true;
            case R.id.action_about:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private SensorEventListener tempSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float sensorTemp = event.values[0];
            DisplayTemperature((int)sensorTemp);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void DisplayTemperature(int tempVal){
        ((TextView)this.findViewById(R.id.tempValue)).setText(tempVal);
    }
}
