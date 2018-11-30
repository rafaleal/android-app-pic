package com.android.utp.praticasintegrativascomplementares.therapy;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.utp.praticasintegrativascomplementares.R;
import com.android.utp.praticasintegrativascomplementares.databinding.FragmentRecyclerTherapyBinding;
import com.android.utp.praticasintegrativascomplementares.models.pic.GetPICResponse;
import com.android.utp.praticasintegrativascomplementares.models.pic.PIC;
import com.android.utp.praticasintegrativascomplementares.network.NetworkManager;

import org.parceler.Parcels;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTherapyFragment extends Fragment implements TherapyRecycleAdapter.TherapyAdapterListener {

    private List<PIC> therapyList;

    private TherapyRecycleAdapter recyclerAdapter;

    private RecyclerView recyclerView;

    private static final String TAG = "ListTherapyFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("List of Therapies");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentRecyclerTherapyBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler_therapy, container, false);

        recyclerView = binding.recyclerTherapy;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        getAPIData();

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

    private void getAPIData() {
        NetworkManager.service().getPics().enqueue(new Callback<GetPICResponse>() {
            @Override
            public void onResponse(final Call<GetPICResponse> call, final Response<GetPICResponse> response) {
                therapyList = response.body().getObject().getArray();
                Log.d(TAG, therapyList.get(0).getNome());
                recyclerAdapter = new TherapyRecycleAdapter(therapyList, ListTherapyFragment.this::onTherapySelected);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onFailure(Call<GetPICResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Erro ao receber resposta!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onTherapySelected(PIC therapyItem) {
        try {
            Log.d("list onTherapySelected", therapyItem.toString());
            Intent newTherapyActivity = new Intent(getActivity(), TherapyActivity.class);
            Bundle extras = new Bundle();
            extras.putParcelable("PIC", Parcels.wrap(therapyItem));
            newTherapyActivity.putExtras(extras);
            startActivity(newTherapyActivity);

        } catch (Exception e) {
            throw e;
        }
    }
}
