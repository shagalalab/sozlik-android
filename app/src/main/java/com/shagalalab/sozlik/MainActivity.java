package com.shagalalab.sozlik;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.shagalalab.sozlik.about.AboutActivity;
import com.shagalalab.sozlik.dictionary.DictionaryFragment;
import com.shagalalab.sozlik.favorites.FavoritesFragment;
import com.shagalalab.sozlik.history.HistoryFragment;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        changeFragment(new DictionaryFragment(), DictionaryFragment.TAG);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;

        switch (item.getItemId()) {
            case R.id.nav_favorites:
                fragment = getSupportFragmentManager().findFragmentByTag(FavoritesFragment.TAG);
                if (fragment == null) {
                    changeFragment(new FavoritesFragment(), FavoritesFragment.TAG);
                }
                break;
            case R.id.nav_dictionary:
                fragment = getSupportFragmentManager().findFragmentByTag(DictionaryFragment.TAG);
                if (fragment == null) {
                    changeFragment(new DictionaryFragment(), DictionaryFragment.TAG);
                }
                break;
            case R.id.nav_history:
                fragment = getSupportFragmentManager().findFragmentByTag(HistoryFragment.TAG);
                if (fragment == null) {
                    changeFragment(new HistoryFragment(), HistoryFragment.TAG);
                }
                break;
            case R.id.nav_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            default:
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment, tag).commit();
    }

}
