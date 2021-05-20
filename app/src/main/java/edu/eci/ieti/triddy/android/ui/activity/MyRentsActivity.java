package edu.eci.ieti.triddy.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import edu.eci.ieti.triddy.android.adapter.RentAdapter;
import edu.eci.ieti.triddy.android.model.Rent;
import edu.eci.ieti.triddy.android.storage.Storage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.eci.ieti.triddy.android.R;
import edu.eci.ieti.triddy.android.viewModel.RentViewModel;

import android.view.Menu;
import android.view.MenuItem;

public class MyRentsActivity extends AppCompatActivity implements RentAdapter.OnRentListener, NavigationView.OnNavigationItemSelectedListener  {

    private Storage storage;
    private RentAdapter rentAdapter;
    private RecyclerView recyclerView;
    private RentViewModel rentViewModel;
    public static final String PRODUCTID = "edu.eci.ieti.triddy.android.ui.activity.PRODUCTID";
    public static final String USERPRODUCT = "edu.eci.ieti.triddy.android.ui.activity.USERPRODUCT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rents);
        storage = new Storage( this );

        rentAdapter = new RentAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_MyRents);

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        DrawerLayout drawer = findViewById( R.id.my_rents);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle( this, drawer, toolbar, R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        NavigationView navigationView = findViewById( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener( this );

        configureRecyclerView();
    }

    private void configureRecyclerView(){
        recyclerView.setHasFixedSize( true );
        LinearLayoutManager layoutManager = new LinearLayoutManager( this );
        recyclerView.setAdapter( rentAdapter );
        recyclerView.setLayoutManager(layoutManager);
        rentViewModel = new ViewModelProvider(this).get(RentViewModel.class);
        rentViewModel.getRents(storage.getToken(), storage.getEmail()).observe(this, rents -> {
            runOnUiThread(() -> {
                rentAdapter.updateRents(rents);
            });
        });
    }

    @Override
    public void onRentClicked(int position) {
        Rent rent = rentAdapter.getRent(position);
        Intent intent = new Intent(this, CalificationActivity.class);
        intent.putExtra(PRODUCTID, rent.getProductId());
        intent.putExtra(USERPRODUCT, rent.getUserOwner());
        startActivity(intent);
    }


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById( R.id.chatList );
        if ( drawer.isDrawerOpen( GravityCompat.START ) )
        {
            drawer.closeDrawer( GravityCompat.START );
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item )
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if ( id == R.id.action_settings )
        {
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings( "StatementWithEmptyBody" )
    @Override
    public boolean onNavigationItemSelected( MenuItem item ){
        int id = item.getItemId();

        if ( id == R.id.nav_logout )
        {
            storage.clear();
            startActivity( new Intent( this, LoginActivity.class ) );
            finish();
        }

        if (id == R.id.drawer_layout){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        if (id == R.id.chatList){
            Intent intent = new Intent(this, ChatListActivity.class);
            startActivity(intent);
        }
        if (id == R.id.profile){
            Intent intent = new Intent(this, MyProfileActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById( R.id.my_rents );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }

}