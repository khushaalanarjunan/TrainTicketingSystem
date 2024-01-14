package com.example.trainticketsystem_hashmapbeatstherest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.trainticketsystem_hashmapbeatstherest.adapter.SelectTrainFragmentPagerAdapter;
import com.example.trainticketsystem_hashmapbeatstherest.trainCoach.CoachAFragment;
import com.example.trainticketsystem_hashmapbeatstherest.trainCoach.CoachBFragment;
import com.example.trainticketsystem_hashmapbeatstherest.trainCoach.CoachCFragment;
import com.google.android.material.tabs.TabLayout;


public class SelectTrainFragment extends Fragment {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_select_train, container, false);
        //init toolbar
        toolbar = root.findViewById(R.id.toolbar_select_train);
        tabLayout = root.findViewById(R.id.tabs_select_train);
        viewPager = root.findViewById(R.id.view_pager_select_train);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Select Departing Train");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupViewPager();
        viewPager.setOffscreenPageLimit(10);
        return root;
    }
    public SelectTrainFragment() {
    }

    private void setupViewPager(){
        //don't use parent frag manager, tab will not display content when switching nav
        SelectTrainFragmentPagerAdapter adapter = new SelectTrainFragmentPagerAdapter(getChildFragmentManager());
        adapter.addFrag(new CoachAFragment(),"Coach A");
        adapter.addFrag(new CoachBFragment(), "Coach B");
        adapter.addFrag(new CoachCFragment(), "Coach C");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}