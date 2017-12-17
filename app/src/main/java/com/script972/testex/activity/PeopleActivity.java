package com.script972.testex.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.script972.testex.R;
import com.script972.testex.presenter.DrawableMenuPresenterImp;
import com.script972.testex.presenter.PeoplePresenterIml;
import com.script972.testex.presenter.iterafaces.DrawableMenuPresenter;
import com.script972.testex.presenter.iterafaces.db.PeoplePresenter;
import com.script972.testex.view.PeopleView;

import java.util.List;


public class PeopleActivity extends AppCompatActivity implements PeopleView,
        NavigationView.OnNavigationItemSelectedListener{

    private DrawableMenuPresenter presenterMenu;

    private PeoplePresenter presenter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        presenter=new PeoplePresenterIml(this);
        presenterMenu=new DrawableMenuPresenterImp(this);
        initView();
        presenter.onCreate();

    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listviewpeople);
        initToolbarAndNavigation();
    }

    private void initToolbarAndNavigation() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        presenterMenu.selectItem(id);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showListUsers(List<String> personList) {

    }
}
