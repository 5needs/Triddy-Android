package edu.eci.ieti.triddy.android.ui.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import edu.eci.ieti.triddy.android.adapter.ChatListAdapter;
import edu.eci.ieti.triddy.android.model.Chat;
import edu.eci.ieti.triddy.android.storage.Storage;

import com.google.android.material.navigation.NavigationView;
import com.ieti.triddy.viewModel.ChatViewModel;
import edu.eci.ieti.triddy.android.R;

public class ChatListActivity extends AppCompatActivity implements ChatListAdapter.OnChatListener, NavigationView.OnNavigationItemSelectedListener {
    private Storage storage;
    private ChatListAdapter chatListAdapter;
    private RecyclerView recyclerView;
    public static final String CHATID = "edu.eci.ieti.triddy.android.ui.activity.CHATID";
    public static final String USERCHAT = "edu.eci.ieti.triddy.android.ui.activity.USERCHAT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatlist);
        storage = new Storage( this );
        //setTitle("Mis preguntas");
        chatListAdapter = new ChatListAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_ChatList);

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        DrawerLayout drawer = findViewById( R.id.chatList);
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
        recyclerView.setAdapter( chatListAdapter );
        recyclerView.setLayoutManager(layoutManager);
        ChatViewModel chatsViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        chatsViewModel.getChats(storage.getToken(), storage.getEmail()).observe(this, chats -> {
            runOnUiThread(() -> {
                chatListAdapter.updateChat(chats);
            });
        });
    }

    @Override
    public void onChatClicked(int position) {
        Chat chat = chatListAdapter.getChat(position);
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(CHATID, chat.getId());
        intent.putExtra(USERCHAT, chat.getUser2());
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

        if (id == R.id.my_rents){
            Intent intent = new Intent(this, MyRentsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById( R.id.chatList );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }


}