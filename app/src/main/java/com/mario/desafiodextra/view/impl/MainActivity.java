package com.mario.desafiodextra.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mario.desafiodextra.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Mario Feles dos Santos Junior
 * @email mario_feles@live.com
 * @date 17/05/17 15:45
 * @project GoodLaundry
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation) BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(this);

        transactionFragment(LunchFragment.newInstance());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_lanches:
                selectedFragment = LunchFragment.newInstance();
                break;
            case R.id.navigation_promocoes:
                selectedFragment = PromotionFragment.newInstance();
                break;
            case R.id.navigation_carrinho:
                selectedFragment = CartFragment.newInstance();
                break;
        }

        transactionFragment(selectedFragment);
        return true;
    }

    public void transactionFragment(Fragment selectedFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
    }
}
