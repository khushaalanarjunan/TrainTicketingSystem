package com.example.trainticketsystem_hashmapbeatstherest;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    //bottom navigation 20-25
    static BottomNavigationView bottomNavigationView;
     BuyTicketFragment buyTicketFragment;
     MyTicketsFragment myTicketsFragment ;
     EWalletFragment eWalletFragment ;
     AccountFragment accountFragment ;

     SelectTrainFragment selectTrainFragment = new SelectTrainFragment();



    //    Firebase Realtimedatabase Link
    //    https://hashmapbeatstherest-default-rtdb.firebaseio.com/


    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent intent = new Intent(MainActivity.this, AuthenticationActivity.class);
            startActivity(intent);
            return;
        };

        setContentView(R.layout.activity_main);

        buyTicketFragment= new BuyTicketFragment();
        myTicketsFragment = new MyTicketsFragment();
        eWalletFragment = new EWalletFragment();
        accountFragment = new AccountFragment();
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
            Intent intent = new Intent(MainActivity.this, AuthenticationActivity.class);
            startActivity(intent);
        }
    }

    //logout button click
    public void logout(){
        mFirebaseAuth.signOut();

        Intent intent = new Intent(MainActivity.this, AuthenticationActivity.class);
        startActivity(intent);
    }

    public String getUserEmail(){
        if(mFirebaseUser != null){
            return mFirebaseUser.getEmail();
        }
        else{
         return "null";
        }
    }
}