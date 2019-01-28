package com.example.activitiesandstack;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{


    protected static ActivityManager activityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_a);

        if(activityManager == null){
            activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        }
    }
    protected void startActivity(Activity activity,Class targetActivityClass){
        Intent intent = new Intent(activity,targetActivityClass);

        //intent flags are used for runtime changes in launchmodes

        if(targetActivityClass.equals(Activity_B.class)){
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        startActivity(intent);
    }


    protected static int getNumberofTasks(){
        return activityManager.getAppTasks().size();
    }

    protected static String getAppTaskState(){
        StringBuilder stringBuilder=new StringBuilder();
        int totalNumberOfTasks=activityManager.getRunningTasks(10).size();//Returns total number of tasks - stacks
        stringBuilder.append("\nTotal Number of Tasks: "+totalNumberOfTasks+"\n\n");

        List<ActivityManager.RunningTaskInfo> taskInfo =activityManager.getRunningTasks(10);//returns List of RunningTaskInfo - corresponding to tasks - stacks

        for(ActivityManager.RunningTaskInfo info:taskInfo){
            stringBuilder.append("Task Id: "+info.id+", Number of Activities : "+info.numActivities+"\n");//Number of Activities in task - stack

            // Display the root Activity of task-stack
            stringBuilder.append("TopActivity: "+info.topActivity.getClassName().
                    toString().replace("lauchmodesdemo","")+"\n");

            // Display the top Activity of task-stack
            stringBuilder.append("BaseActivity:"+info.baseActivity.getClassName().
                    toString().replace("lauchmodesdemo.","")+"\n");
            stringBuilder.append("\n\n");
        }
        return stringBuilder.toString();
    }
}
