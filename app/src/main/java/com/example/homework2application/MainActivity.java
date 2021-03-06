package com.example.homework2application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.example.homework2application.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            binding.toolbar.setTitle(item.getTitle());
            final int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                binding.viewPager.setCurrentItem(0);
                return true;
            } else if (itemId == R.id.navigation_video) {
                binding.viewPager.setCurrentItem(1);
                return true;
            } else if (itemId == R.id.navigation_my) {
                binding.viewPager.setCurrentItem(2);
                return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ????????????
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //?????????snackbar????????????
        binding.floatingActionButton.setOnClickListener(view ->{
            Snackbar.make(view, "???????????????????????????", Snackbar.LENGTH_LONG).setAction("Action", null).show();

        });
//
//        // ???????????????Toolbar
//        setSupportActionBar(binding.toolbar);

        // ViewPager???????????????
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(adapter);
        // ??????fragment??????????????????????????????????????????Fragment
        binding.viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
        // ??????ViewPager???BottomNavigationView????????????
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                binding.navigation.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // ??????????????????
        binding.navigation.setOnNavigationItemSelectedListener(
                navigationItemSelectListener);
        // ?????????Toolbar?????????
        initTitle();
        // ??????NavigationView
        bindNavigationDrawer();
    }

    private void initTitle() {
        binding.toolbar.post(() -> binding.toolbar.setTitle(binding.navigation.getMenu().getItem(0).getTitle()));
    }

    private static class MainViewPagerAdapter extends FragmentPagerAdapter {
        public MainViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HomeFragment.newInstance();
                case 1:
                    return VideoFragment.newInstance();
                case 2:
                    return MyFragment.newInstance();
            }
            return HomeFragment.newInstance();
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    // FloatingActionButton???????????????
    public void onFabClicked(View view) {
        showSnackbar();
    }

    private void showSnackbar() {
        Snackbar snack = Snackbar.make(binding.coordinatorLayout, "Custom Snackbar", Snackbar.LENGTH_INDEFINITE);
        // ??????Action??????
        snack.setAction("Ok", v -> snack.dismiss());
        // ?????????????????????
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)
                snack.getView().getLayoutParams();
        params.setAnchorId(R.id.navigation);
        params.anchorGravity = Gravity.TOP;
        params.gravity = Gravity.TOP;
        snack.getView().setLayoutParams(params);
        // ????????????

        snack.show();
    }

    private void bindNavigationDrawer() {
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                showSnackbar(item.getTitle().toString());
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public void showSnackbar(String title) {
        Snackbar.make(binding.coordinatorLayout, title, Snackbar.LENGTH_SHORT).show();
    }

}