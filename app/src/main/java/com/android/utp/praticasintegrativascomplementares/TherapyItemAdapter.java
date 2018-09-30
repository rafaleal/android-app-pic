package com.android.utp.praticasintegrativascomplementares;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TherapyItemAdapter extends ArrayAdapter<TherapyItem> {

    private static final String LOG_TAG = TherapyItemAdapter.class.getSimpleName();

    private int mColorResourceId;

    private Context ctxt;


    public TherapyItemAdapter(Activity context, ArrayList<TherapyItem> therapyItems, int colorResourceId) {
        super(context, 0, therapyItems);
        mColorResourceId = colorResourceId;
        ctxt = context;
    }

    @Override
    public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
              R.layout.therapy_item, parent, false
            );
        }

        TherapyItem currentTherapyItem = getItem(position);

        TextView nameTextView = listItemView.findViewById(R.id.therapy_name);
        nameTextView.setText(currentTherapyItem.getTherapyName());

        ImageView iconView = listItemView.findViewById(R.id.list_therapy_icon);
        iconView.setImageResource(currentTherapyItem.getImageResourceId());

        View textContainer = listItemView.findViewById(R.id.therapy_text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctxt, TherapyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctxt.startActivity(intent);
            }
        });

        return listItemView;
    }
}
