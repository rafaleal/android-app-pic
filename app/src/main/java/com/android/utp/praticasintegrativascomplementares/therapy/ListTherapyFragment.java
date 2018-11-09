package com.android.utp.praticasintegrativascomplementares.therapy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.utp.praticasintegrativascomplementares.R;
import com.android.utp.praticasintegrativascomplementares.databinding.FragmentRecyclerTherapyBinding;
import com.android.utp.praticasintegrativascomplementares.model.TherapyItem;

import java.util.ArrayList;

public class ListTherapyFragment extends Fragment {

    private ArrayList<TherapyItem> therapyList;

    private TherapyRecycleAdapter adapter;

    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("List of Therapies");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        populateTherapyList();
        FragmentRecyclerTherapyBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler_therapy, container, false);

        recyclerView = binding.recyclerTherapy;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        adapter = new TherapyRecycleAdapter(therapyList, null);

        recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.default_toolbar, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("List of Therapies");
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
