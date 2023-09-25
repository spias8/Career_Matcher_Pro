package com.trodev.careermatcherpro.mcq_part.english;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class englishPagerAdapter extends FragmentPagerAdapter {
    public englishPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new English_oneFragment();
        } else if (position == 1) {
            return new English_twoFragment();
        } else if(position == 2) {
            return new English_threeFragment();
        } else if (position == 3) {
            return new English_fourFragment();

        } else{
            return new English_fiveFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return "01";
        } else if (position == 1) {
            return "02";
        } else if (position == 2) {
            return "03";
        } else if (position == 3) {
            return "04";
        } else {
            return "05";
        }

    }
}
