package com.example.kapil.uithreadworking;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
// Here In this application we will seeing how UIThread Works in android
    //UI Thread in android is used to process all the tasks for User Interface
    //It is default in android that UI tasks are performed in UI thread
    // But there is a catch if your task is taking more than 5 sec then it must not be run in UI thread
    // that task must have to run in another thread
    // Let's see how  it works
    // It works fine but if we want to inflate the UI with the Count of the thread created we have to use looper and handler concept
    // Now android maintains the message queue and it runs on different thread and the interaction between threads is done through handler
    // handler will handle all interaction between different UI threads

    // Now Working with looper and handler is not easy if we want to use long running process so Android provides an easy to use class AsycTask to carry out long running tasks in a separate thread, without you having to worry about looper and handler.
    // so now we have to comment the new thread running using handler and looper
    public static final String TAG = MainActivity.class.getSimpleName();
    Button buttonStart, buttonstop;
    TextView tvThreadCount;
    int count = 0;
    MyAsyncTask myAsyncTask; // object of MyAsyncTask
    /*  Handler handler; // object of handler */

    private boolean threadstop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "Thread id:" + Thread.currentThread().getId());
        // Thread.currentThread() is returning the instance of the thread that is running
        buttonStart = findViewById(R.id.StartThread);
        buttonstop = findViewById(R.id.StopThread);
        tvThreadCount = findViewById(R.id.threadCount);


        buttonStart.setOnClickListener(this);
        buttonstop.setOnClickListener(this);
        // this handler will have the reference to the looper of main UI Thread
        /*  handler = new Handler(getApplicationContext().getMainLooper());  */


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.StartThread:
                threadstop = true;


              /*  new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (threadstop){
                            try{
                                Thread.sleep(1000);
                                count++;
                            }catch (InterruptedException e){
                                Log.i(TAG, e.getMessage());
                            }
                            Log.i(TAG,"Thread Id in while loop "+Thread.currentThread().getId()+ ",Count: "+ count);
                            //handler.post will post a task in looper which is of type Runnable
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    tvThreadCount.setText(""+count);
                                }
                            });
                        }
                    }
                }).start();

                Toast.makeText(MainActivity.this, "New Thread Started See LogCat", Toast.LENGTH_SHORT).show();  */

                myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute(count);
                // this will start my async task on button click and it passed with count because it needs an integer value
                Toast.makeText(this, "MyAsyncTask Started", Toast.LENGTH_SHORT).show();
                break;


            // now here the while loop  will continuously run and when we press StopThread button the app will crash
            // So why did happen it is because It is running on MainUI thread and it takes more than 5 seconds to complete the task thus hampering MainUI Thread
            //Now to solve this we have to make a new Thread and just place this code in that
//
//            while (threadstop){
//                Log.i(TAG,"Thread Id in while loop "+Thread.currentThread().getId());
//            } break;
            case R.id.StopThread:
                threadstop = false;
                Toast.makeText(this, "Thread Stooped see logCat", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // now AsyncTask class takes three parameter first is params means where the task starts
    // second is Progress where the updation takes place
    // Third is result
    private class MyAsyncTask extends AsyncTask<Integer, Integer, Integer> {
        private int Customcounter;

        @Override
        // Runs on Main UI Thread
        protected void onPreExecute() {
            super.onPreExecute();
            Customcounter = 0;
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            // So here integers are array which takes value for CustomCounter
            Customcounter = integers[0];
            // Now we have to run loop for background activity i.e task we want to do in background
            while (threadstop) {
                try {
                    Thread.sleep(1000);
                    Customcounter++;
                    // now we have to publish the Customcounter for progress update
                    publishProgress(Customcounter);
                } catch (InterruptedException e) {
                    Log.i(TAG, e.getMessage());
                }


            }
            return Customcounter;
        }

        @Override

        // this function will receive values from the publishprogress method form doInBackground
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvThreadCount.setText("" + values[0]);
        }

        @Override
        //  run on Main UI Thread
        // Here the value returned from the doInBackground will come in integer
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            tvThreadCount.setText("" + integer);
            count = integer;
        }
    }
}

