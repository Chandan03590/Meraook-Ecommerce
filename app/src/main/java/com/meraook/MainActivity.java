package com.meraook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.meraook.Authentication.LoginActivity;
import com.meraook.Authentication.SessionManager;
import com.meraook.Fragment.AccountFragment;
import com.meraook.Fragment.HomeFragment;
import com.meraook.Fragment.OrderFragment;


public class MainActivity extends AppCompatActivity {

    private ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chipNavigationBar = findViewById(R.id.bottom_navigation);

        String mobile_number = getIntent().getStringExtra("mobile_number");


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

        chipNavigationBar.setItemSelected(R.id.home_item,true);

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {

                Fragment temp=null;
                switch (id){
                    case R.id.home_item:
                        temp=new HomeFragment();
                        break;
                    case R.id.order:
                        temp=new OrderFragment();
                        break;
                    case R.id.account:
                        temp=new AccountFragment();
                        break;
                }
                if (temp !=null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,temp).commit();
                }
            }
        });
    }

    public void logOut(View view) {
        new SessionManager(MainActivity.this).removeUser();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

}