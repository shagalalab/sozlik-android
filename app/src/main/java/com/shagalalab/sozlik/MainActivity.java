package com.shagalalab.sozlik;

import android.app.Activity;
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
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.shagalalab.sozlik.about.AboutActivity;
import com.shagalalab.sozlik.dictionary.DictionaryFragment;
import com.shagalalab.sozlik.favorites.FavoritesFragment;
import com.shagalalab.sozlik.history.HistoryFragment;
import com.shagalalab.sozlik.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        changeFragment(new DictionaryFragment(), DictionaryFragment.TAG);
    }

    @Override
    protected void onStart() {
        super.onStart();
        drawer.addDrawerListener(drawerListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        drawer.removeDrawerListener(drawerListener);
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
            case R.id.nav_settings:
                startActivity(new Intent(this, SettingsActivity.class));
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

    private DrawerLayout.DrawerListener drawerListener = new DrawerLayout.SimpleDrawerListener() {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            super.onDrawerSlide(drawerView, slideOffset);
            getInputMethodManager().hideSoftInputFromWindow(drawerView.getWindowToken(), 0);
        }
    };

    private InputMethodManager getInputMethodManager() {
        if (inputMethodManager == null) {
            inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        }
        return inputMethodManager;
    }
}
