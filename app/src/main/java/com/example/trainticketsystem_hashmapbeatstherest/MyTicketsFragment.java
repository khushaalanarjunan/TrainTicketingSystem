package com.example.trainticketsystem_hashmapbeatstherest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trainticketsystem_hashmapbeatstherest.adapter.MyTicketsFragmentPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class MyTicketsFragment extends Fragment {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_my_tickets, container, false);
        //toolbar
        toolbar = root.findViewById(R.id.toolbar_my_tickets);
        tabLayout = root.findViewById(R.id.tabs_my_tickets);
        viewPager = root.findViewById(R.id.view_pager_my_tickets);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setupViewPager();
        viewPager.setOffscreenPageLimit(10);
        return root;
    }
    private void setupViewPager(){
        //don't use parent frag manager, tab will not display content when switching nav
        MyTicketsFragmentPagerAdapter adapter = new MyTicketsFragmentPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new PastTicketsFragment(),"Past");
        adapter.addFrag(new UpcomingTicketsFragment(), "Upcoming");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}