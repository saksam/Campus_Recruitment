package com.wix.traitsoft.tpo_mnnit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import static android.R.attr.name;
import static com.wix.traitsoft.tpo_mnnit.R.id.fab;
import static com.wix.traitsoft.tpo_mnnit.R.styleable.NavigationView;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView t1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //to display name
       /* String name = "";
        Bundle extras = getIntent().getExtras();    //Intent receiving
        if (extras != null) {
            name = extras.getString("username");
        }*/



        // no change
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
/*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        //t1 = (TextView)header.findViewById(R.id.myname);
        //t1.setText(name);

        FragmentManager fmanager=getSupportFragmentManager();
        fmanager.beginTransaction().replace(R.id.content,new Homefrag()).commit();

    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Session session = new Session(this);
            session.clear();
            Intent intent = new Intent(this,Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fmanager=getSupportFragmentManager();

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fmanager.beginTransaction().replace(R.id.content,new Homefrag()).commit();
        } else if (id == R.id.nav_profile) {

            fmanager.beginTransaction().replace(R.id.content,new Fragment_Profile()).commit();

            /*Intent anIntent = new Intent(getApplicationContext(), Profile_View.class);
            anIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(anIntent);
            drawer.closeDrawers();*/


        } else if (id == R.id.nav_companies) {
            fmanager.beginTransaction().replace(R.id.content,new Fragment_company()).commit();

        } else if (id == R.id.nav_placement) {
            fmanager.beginTransaction().replace(R.id.content,new Fragment_Statistics()).commit();
        }

         else if (id == R.id.nav_feedback) {

        }
        else if (id == R.id.nav_chat) {
            fmanager.beginTransaction().replace(R.id.content,new Tab_chat()).commit();

        }
        else if (id == R.id.nav_contact) {
           // startActivity(new Intent(this,contacts.class));
            fmanager.beginTransaction().replace(R.id.content,new Fragment_Contacts()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
