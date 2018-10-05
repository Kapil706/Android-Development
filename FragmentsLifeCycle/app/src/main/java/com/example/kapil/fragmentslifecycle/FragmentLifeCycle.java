package com.example.kapil.fragmentslifecycle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.util.Log;

public class FragmentLifeCycle extends Fragment {

    private static final String COMMON_TAG = "CombinedLifeCycle";
    private static final String FRAGMENT_NAME = FragmentLifeCycle.class.getSimpleName();

    private static final String TAG = COMMON_TAG;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, FRAGMENT_NAME +" onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, FRAGMENT_NAME +" onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lifecycle,
                container,
                false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, FRAGMENT_NAME +" onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.i(TAG, FRAGMENT_NAME +" onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, FRAGMENT_NAME +" onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, FRAGMENT_NAME +" onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, FRAGMENT_NAME +" onStop");
        super.onStop();
    }


    @Override
    public void onDestroyView() {
        Log.i(TAG, FRAGMENT_NAME +" onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, FRAGMENT_NAME +" onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, FRAGMENT_NAME +" onDetach");
        super.onDetach();
    }}

