package com.android.utp.praticasintegrativascomplementares;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ListTherapyActivity extends AppCompatActivity {

    public ArrayList<TherapyItem> therapyList;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_therapy);
        populateTherapyList();

        TherapyItemAdapter therapyAdapter = new TherapyItemAdapter(this, therapyList, R.color.colorAccent);
        listView = findViewById(R.id.listview_therapy);
        listView.setAdapter(therapyAdapter);
    }

    private void populateTherapyList() {
        therapyList = new ArrayList<>();
        therapyList.add(new TherapyItem("Apiterapia", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Aromaterapia", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Ayurveda", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Biodança", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Bioenergética", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Constelação familiar", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Cromoterapia", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Dança circular", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Geoterapia", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Hipnoterapia", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Homeopatia", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Imposição de mãos", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Medicina antroposófica/antroposofia aplicada à saúde", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Medicina Tradicional Chinesa – acupuntura", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Meditação", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Musicoterapia", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Naturopatia", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Osteopatia", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Ozonioterapia", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Plantas medicinais – fitoterapia", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Quiropraxia", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Reflexoterapia", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Reiki", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Shantala", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Terapia Comunitária Integrativa", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Terapia de florais", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Termalismo social/crenoterapia", R.drawable.ic_logo_colorful));
        therapyList.add(new TherapyItem("Yoga", R.drawable.ic_logo_colorful));
    }
}
