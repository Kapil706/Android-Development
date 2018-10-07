package com.example.kapil.stepcounterusingsensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.SensorEventListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView tv_mes;
    SensorManager sm;
    boolean running = false;
    private long steps = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_mes =  findViewById(R.id.tv_mes);
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
      //  getDistanceRun(steps);


    }

    @Override
    protected void onStart() {
        running =true;
        super.onStart();
       // Sensor Step = sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        Sensor Step = sm.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
      //  if(Step!=null) {
            sm.registerListener(this, Step, SensorManager.SENSOR_DELAY_UI);
     //   }
    //    else{
      //      Toast.makeText(this, "Sensor not found",Toast.LENGTH_SHORT).show();
     //   }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
       float[] values = event.values;
      int value = -1;

       if (values.length > 0) {
           value = (int) values[0];
       }


     if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
           steps++;
       }
       getDistanceRun(steps);

   //     tv_mes.setText(String.valueOf(event.values[0]));
        tv_mes.setText(String.valueOf(steps));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

   public float getDistanceRun(long steps){
       float distance = (float)(steps*78)/(float)100000;
     return distance;
   }

    @Override
    protected void onStop() {
      //  running =false;
        super.onStop();
        sm.unregisterListener(this);
    }
}
