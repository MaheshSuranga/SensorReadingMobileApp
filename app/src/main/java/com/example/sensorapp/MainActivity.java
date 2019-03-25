package com.example.sensorapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener,OnClickListener {
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private TextView text;
    private Button button;
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        text = (TextView) findViewById(R.id.textView);

        button = (Button)findViewById(R.id.btn1);
        button.setOnClickListener(this);
        button.setText("Start");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            text.setText(""+event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onClick(View v) {
        if(running){
            running = false;
            button.setText("Start");
            sensorManager.unregisterListener(this);
        }
        else{
            running = true;
            button.setText("Stop");
            sensorManager.registerListener(this,lightSensor,SensorManager.SENSOR_DELAY_FASTEST);
        }
    }
}
