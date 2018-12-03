package com.android.utp.praticasintegrativascomplementares.health_unit;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.utp.praticasintegrativascomplementares.R;
import com.android.utp.praticasintegrativascomplementares.databinding.ActivityHealthUnitBinding;
import com.android.utp.praticasintegrativascomplementares.main.MainActivity;
import com.android.utp.praticasintegrativascomplementares.models.pic.GetPICResponse;
import com.android.utp.praticasintegrativascomplementares.models.pic.PIC;
import com.android.utp.praticasintegrativascomplementares.models.ubs.GetUBSResponse;
import com.android.utp.praticasintegrativascomplementares.models.ubs.UBS;
import com.android.utp.praticasintegrativascomplementares.network.NetworkManager;
import com.android.utp.praticasintegrativascomplementares.therapy.TherapyActivity;
import com.android.utp.praticasintegrativascomplementares.therapy.TherapyRecycleAdapter;
import com.android.utp.praticasintegrativascomplementares.util.Session;
import com.facebook.login.LoginManager;

import org.parceler.Parcels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthUnitActivity extends AppCompatActivity implements TherapyRecycleAdapter.TherapyAdapterListener{

    private Intent intent;
    private UBS ubs;
    private PIC pic;
    private static final String TAG = "HealthUnitActivity";
    private TherapyRecycleAdapter recycleAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    ActivityHealthUnitBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_health_unit);

        recyclerView = binding.healthUnitPicList;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        progressBar = binding.mainProgressbar;

        ubs = Parcels.unwrap(getIntent().getExtras().getParcelable("UBS"));

        if(ubs == null) {
            getUBSData();
        } else {
            binding.setUbs(ubs);
            binding.ubsToolbar.setTitle(ubs.getNome());
            setSupportActionBar(binding.ubsToolbar);
        }

        getPICData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.therapy_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                this.logout();
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            case R.id.action_share:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void logout() {

        LoginManager.getInstance().logOut();
        Session.setLogged(this, false);

    }

    private void getUBSData() {
        progressBar.setVisibility(View.VISIBLE);
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), "1");
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        requestBodyMap.put("distrito_id", body);
        NetworkManager.service().getUbs(requestBodyMap).enqueue(new Callback<GetUBSResponse>() {
            @Override
            public void onResponse(final Call<GetUBSResponse> call, final Response<GetUBSResponse> response) {
                List<UBS> ubsList = response.body().getObjectPhp().getArray();
                ubs = ubsList.get(0);
                binding.setUbs(ubs);
                binding.ubsToolbar.setTitle(ubs.getNome());
                setSupportActionBar(binding.ubsToolbar);

                Log.d("UBS", ubs.getInfo());
            }

            @Override
            public void onFailure(Call<GetUBSResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao receber resposta!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getPICData() {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), "2");
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        requestBodyMap.put("ubs_id", body);
        NetworkManager.service().getPicsByUBS(requestBodyMap).enqueue(new Callback<GetPICResponse>() {
            @Override
            public void onResponse(final Call<GetPICResponse> call, final Response<GetPICResponse> response) {
                List<PIC> picList = response.body().getObject().getArray();
                recycleAdapter = new TherapyRecycleAdapter(picList, HealthUnitActivity.this::onTherapySelected);
                recyclerView.setAdapter(recycleAdapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GetPICResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao receber resposta!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onTherapySelected(PIC therapyItem) {
        try {
            Log.d("list onTherapySelected", therapyItem.toString());
            Intent newTherapyActivity = new Intent(this, TherapyActivity.class);
            Bundle extras = new Bundle();
            extras.putParcelable("PIC", Parcels.wrap(therapyItem));
            newTherapyActivity.putExtras(extras);
            startActivity(newTherapyActivity);

        } catch (Exception e) {
            throw e;
        }
    }


}
