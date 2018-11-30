package com.android.utp.praticasintegrativascomplementares.health_unit;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.utp.praticasintegrativascomplementares.R;
import com.android.utp.praticasintegrativascomplementares.databinding.ActivityHealthUnitBinding;
import com.android.utp.praticasintegrativascomplementares.main.MainActivity;
import com.android.utp.praticasintegrativascomplementares.models.ubs.GetUBSResponse;
import com.android.utp.praticasintegrativascomplementares.models.ubs.UBS;
import com.android.utp.praticasintegrativascomplementares.network.NetworkManager;
import com.android.utp.praticasintegrativascomplementares.util.Session;
import com.facebook.login.LoginManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthUnitActivity extends AppCompatActivity {

    private Intent intent;
    private UBS ubs;
    private static final String TAG = "HealthUnitActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityHealthUnitBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_health_unit);

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


}
