package com.android.utp.praticasintegrativascomplementares.therapy;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.utp.praticasintegrativascomplementares.R;
import com.android.utp.praticasintegrativascomplementares.databinding.ActivityTherapyBinding;
import com.android.utp.praticasintegrativascomplementares.health_unit.HealthUnitActivity;
import com.android.utp.praticasintegrativascomplementares.main.MainActivity;
import com.android.utp.praticasintegrativascomplementares.models.pic.PIC;
import com.android.utp.praticasintegrativascomplementares.models.ubs.GetUBSResponse;
import com.android.utp.praticasintegrativascomplementares.models.ubs.UBS;
import com.android.utp.praticasintegrativascomplementares.network.NetworkManager;
import com.android.utp.praticasintegrativascomplementares.ubs.UBSRecycleAdapter;
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

public class TherapyActivity extends AppCompatActivity implements UBSRecycleAdapter.UBSAdapterListener {

    private static final String LOG_TAG = TherapyActivity.class.getSimpleName();
    PIC pic;
    Intent intent;
    private UBSRecycleAdapter ubsRecycleAdapter;
    private RecyclerView recyclerView;
    ActivityTherapyBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapy);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        pic = Parcels.unwrap(extras.getParcelable("PIC"));
        Log.d(LOG_TAG, "Inside onCreate: " + pic.toString());

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_therapy);
        mBinding.setPic(pic);

        recyclerView = mBinding.recyclerListUbs;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        getListUBSData();

        mBinding.toolbar.setTitle(pic.getNome());
        setSupportActionBar(mBinding.toolbar);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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

    private void getListUBSData() {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), "2");
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        requestBodyMap.put("pic_id", body);

        NetworkManager.service().getUbsByPic(requestBodyMap).enqueue(new Callback<GetUBSResponse>() {
            @Override
            public void onResponse(Call<GetUBSResponse> call, Response<GetUBSResponse> response) {
                List<UBS> ubsList = response.body().getObjectPhp().getArray();
                ubsRecycleAdapter = new UBSRecycleAdapter(ubsList, TherapyActivity.this::onUbsSelected);
                recyclerView.setAdapter(ubsRecycleAdapter);
            }

            @Override
            public void onFailure(Call<GetUBSResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao receber resposta", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onUbsSelected(UBS ubsItem) {
        try {
            Log.d(LOG_TAG, ubsItem.toString());
            Intent newHealthUnitActivity = new Intent(this, HealthUnitActivity.class);
            Bundle extras = new Bundle();
            extras.putParcelable("UBS", Parcels.wrap(ubsItem));
            newHealthUnitActivity.putExtras(extras);
            startActivity(newHealthUnitActivity);
        } catch (Exception e) {
            throw e;
        }
    }
}
