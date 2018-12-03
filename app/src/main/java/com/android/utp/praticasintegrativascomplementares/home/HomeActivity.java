package com.android.utp.praticasintegrativascomplementares.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.android.utp.praticasintegrativascomplementares.therapy.ListTherapyFragment;
import com.android.utp.praticasintegrativascomplementares.main.MainActivity;
import com.android.utp.praticasintegrativascomplementares.map.MapViewFragment;
import com.android.utp.praticasintegrativascomplementares.profile.ProfileFragment;
import com.android.utp.praticasintegrativascomplementares.R;
import com.android.utp.praticasintegrativascomplementares.util.Session;
import com.android.utp.praticasintegrativascomplementares.databinding.ActivityHomeBinding;
import com.facebook.login.LoginManager;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private MapViewFragment mMapViewFragment;
    private ListTherapyFragment mListTherapyFragment;
    private ProfileFragment mProfileFragment;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        binding.bottombar.setOnNavigationItemSelectedListener(this);

        setSupportActionBar(binding.toolbar);

        mMapViewFragment = new MapViewFragment();
        mListTherapyFragment = new ListTherapyFragment();
        mProfileFragment = new ProfileFragment();


        managerFragment(mListTherapyFragment);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigationMap:
                managerFragment(mMapViewFragment);
                return true;
            case R.id.navigationTherapies:
                managerFragment(mListTherapyFragment);
                return true;
            case R.id.navigationProfile:
                managerFragment(mProfileFragment);
                return true;
        }
        return false;
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

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void managerFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public void logout() {

        LoginManager.getInstance().logOut();
        Session.setLogged(this,false);

    }

}
