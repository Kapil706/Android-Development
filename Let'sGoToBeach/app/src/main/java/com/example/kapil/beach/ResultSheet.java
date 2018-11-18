package com.example.kapil.beach;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kapil.beach.rest.model.Ultraviolet;

public class ResultSheet extends BottomSheetDialogFragment {
    public static ResultSheet newInstance() {
        return new ResultSheet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // get the views and attach the listener

        return inflater.inflate(R.layout.result, container,
                false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView maxDurationTV, uvStatusTV, uvMaxTV, zeroUvResult;
        ConstraintLayout nonZeroResult;

        maxDurationTV = view.findViewById(R.id.max_duration_result);
        uvMaxTV = view.findViewById(R.id.max_time_result);
        uvStatusTV = view.findViewById(R.id.uv_status_result);
        nonZeroResult = view.findViewById(R.id.non_zero_result);
        zeroUvResult = view.findViewById(R.id.zero_uv_message);

        Ultraviolet uvReading = Repository.getInstance().getUVReading();

        String status;
        if (uvReading.getResult().getUv() >= 13.7) {
            status = "Unsafe";
            uvStatusTV.setTextColor(uvStatusTV.getContext().getResources().getColor(android.R.color.holo_red_light));
        }
        else {
            status = "Safe";
            uvStatusTV.setTextColor(uvStatusTV.getContext().getResources().getColor(android.R.color.holo_green_dark));
        }

        if (uvReading.getResult().getUv() != 0) {
            uvStatusTV.setText(uvStatusTV.getContext().getString(R.string.status_value_placeholder,
                    status,
                    String.valueOf(uvReading.getResult().getUv())));

            maxDurationTV.setText(maxDurationTV.getContext().getString(R.string.med_placeholder,
                    String.valueOf(Repository.getInstance().getSkinType()),
                    String.valueOf(Repository.getInstance().calculateSafeExposureTime())));
            uvMaxTV.setText(uvMaxTV.getContext().getString(R.string.status_value_placeholder,
                    Repository.getInstance().getMaxUvTime(),
                    String.valueOf(Repository.getInstance().getMaxUvValue())));
        }
        else {
            nonZeroResult.setVisibility(View.GONE);
            zeroUvResult.setVisibility(View.VISIBLE);
            zeroUvResult.setText(zeroUvResult.getContext().getString(R.string.zero_uv_placeholder,
                    Repository.getInstance().getPlace().getName()));
        }

    }
}
