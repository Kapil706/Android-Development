package com.example.kapil.beach;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SkinColorAdapter extends ArrayAdapter<SkinColorAdapter.ColorViewHolder> {
    private String[] titles;
    private int[] skinColors;
    private Context context;

    SkinColorAdapter(@NonNull Context context, String[] titles, int[] skinColors) {
        super(context, R.layout.color_row);
        this.context = context;
        this.titles = titles;
        this.skinColors = skinColors;
    }

    class ColorViewHolder {
        private TextView colorTypeText;
        private View colorTypeCard;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ColorViewHolder viewHolder = new ColorViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.color_row, parent, false);
            viewHolder.colorTypeCard = convertView.findViewById(R.id.color_card);
            viewHolder.colorTypeText = convertView.findViewById(R.id.color_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ColorViewHolder) convertView.getTag();
        }
        viewHolder.colorTypeCard.setBackgroundColor(context.getResources().getColor(skinColors[position]));
        viewHolder.colorTypeText.setText(titles[position]);

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
