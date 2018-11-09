package com.android.utp.praticasintegrativascomplementares.health_unit;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.android.utp.praticasintegrativascomplementares.R;
import com.android.utp.praticasintegrativascomplementares.databinding.ActivityHealthUnitBinding;
import com.android.utp.praticasintegrativascomplementares.main.MainActivity;
import com.android.utp.praticasintegrativascomplementares.util.Session;
import com.facebook.login.LoginManager;

public class HealthUnitActivity extends AppCompatActivity {

    private Session session;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityHealthUnitBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_health_unit);
        setSupportActionBar(binding.toolbar);

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
        session.setLogged(false);

    }


}
