package com.example.asus.adminpanel;


import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;



public class AddInfo extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ADDPageAdapter addPageAdapter;
    TabItem tabChats;
    TabItem tabStatus;
    TabItem tabCalls;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_add_info );


        toolbar =  findViewById(R.id.toolbar);
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("VariableName", 0);

        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        //toolbar.setTitle(getResources().getString(R.string.app_name));
        if(intValue==1)
            toolbar.setTitle("Mist Computer Club");
        else if(intValue==2)
            toolbar.setTitle("Mist Literature Club");
        else if(intValue==3)
            toolbar.setTitle("Mist Drama and Film Society");
        else if(intValue==4)
            toolbar.setTitle("Mist Robotics Club");
        else if(intValue==5)
            toolbar.setTitle("Mist Photographic Society");


        //}
//        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tablayout);
        tabChats = findViewById(R.id.tabChats);

        tabCalls = findViewById(R.id.tabCalls);
        viewPager = findViewById(R.id.viewPager);



        addPageAdapter = new ADDPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount(),intValue);
        viewPager.setAdapter( addPageAdapter );

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                /*if(tab.getPosition() == 1)
                {
                    toolbar.setBackgroundColor(ContextCompat.getColor(ClubDetailActivity.this, R.color.colorAccent));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(ClubDetailActivity.this , R.color.colorAccent));

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    {
                        getWindow().setStatusBarColor(ContextCompat.getColor(ClubDetailActivity.this,R.color.colorAccent));
                    }
                }
                else if(tab.getPosition() == 2)
                {
                    toolbar.setBackgroundColor(ContextCompat.getColor(ClubDetailActivity.this, R.color.colorPrimary));
                    tabLayout.setBackgroundColor(ContextCompat.getColor(ClubDetailActivity.this , R.color.colorPrimary));

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    {
                        getWindow().setStatusBarColor(ContextCompat.getColor(ClubDetailActivity.this,R.color.colorPrimary));
                    }
                }
                else if(tab.getPosition() == 3)
                {*/
                toolbar.setBackgroundColor( ContextCompat.getColor(AddInfo.this, R.color.colorPrimaryDark));
                tabLayout.setBackgroundColor(ContextCompat.getColor(AddInfo.this , R.color.colorPrimaryDark));

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    getWindow().setStatusBarColor(ContextCompat.getColor(AddInfo.this,R.color.colorPrimaryDark));
                }
                //}
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

}
