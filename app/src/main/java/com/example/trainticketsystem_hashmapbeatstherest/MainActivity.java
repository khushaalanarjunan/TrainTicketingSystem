package com.example.trainticketsystem_hashmapbeatstherest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.trainticketsystem_hashmapbeatstherest.adapter.MyTicketsFragmentPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    //bottom navigation 20-25
    BottomNavigationView bottomNavigationView;
    BuyTicketFragment buyTicketFragment= new BuyTicketFragment();
    MyTicketsFragment myTicketsFragment = new MyTicketsFragment();
    EWalletFragment eWalletFragment = new EWalletFragment();
    AccountFragment accountFragment = new AccountFragment();

    //    Firebase Realtimedatabase Link
    //    https://hashmapbeatstherest-default-rtdb.firebaseio.com/


    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,buyTicketFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.buy_ticket) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, buyTicketFragment).commit();
                    return true;
                } else if (itemId == R.id.my_tickets) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, myTicketsFragment).commit();
                    return true;
                } else if (itemId == R.id.ewallet) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, eWalletFragment).commit();
                    return true;
                } else if (itemId == R.id.account) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, accountFragment).commit();
                    return true;
                }
                return false;
            }
        });

        //logout button click

    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if (mFirebaseUser == null){
            // go to login page
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    //logout button click
    public void logout(){
        mFirebaseAuth.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}