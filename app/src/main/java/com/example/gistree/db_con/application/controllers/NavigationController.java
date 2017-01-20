package com.example.gistree.db_con.application.controllers;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.example.gistree.db_con.R;
import com.example.gistree.db_con.application.activities.cortantes.scrollabletabs.MainScrollableTabs;

public class NavigationController implements NavigationView.OnNavigationItemSelectedListener {
    private Activity act;
    public NavigationController(Activity act){
        this.act = act;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(act, MainScrollableTabs.class);
            act.startActivity(i);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }
        DrawerLayout drawer = (DrawerLayout) act.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

