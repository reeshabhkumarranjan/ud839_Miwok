package com.example.android.miwok;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CustomFragmentPageAdapter extends FragmentPagerAdapter {
    public CustomFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        // position 0: number
        // position 1: family
        // position 2: color
        // position 3: phrase

        Fragment fragment=null;
        Bundle bundle=null;

        switch (position){

            case 0:

                fragment=new WordFragment();
                bundle=new Bundle();
                bundle.putString("title","Number");
                bundle.putInt("tileColorResourceID",R.color.category_numbers);
                bundle.putParcelableArrayList("wordList",WordList.getNumbersList());
                break;

            case 1:
                fragment=new WordFragment();
                bundle=new Bundle();
                bundle.putString("title","Family");
                bundle.putInt("tileColorResourceID",R.color.category_family);
                bundle.putParcelableArrayList("wordList",WordList.getFamilyList());
                break;

            case 2:
                fragment=new WordFragment();
                bundle=new Bundle();
                bundle.putString("title","Color");
                bundle.putInt("tileColorResourceID",R.color.category_colors);
                bundle.putParcelableArrayList("wordList",WordList.getColorsList());
                break;

            case 3:
                fragment=new WordFragment();
                bundle=new Bundle();
                bundle.putString("title","Phrases");
                bundle.putInt("tileColorResourceID",R.color.category_phrases);
                bundle.putParcelableArrayList("wordList",WordList.getPhrasesList());
                break;

        }

        fragment.setArguments(bundle);
        return fragment;
//        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "Numbers";
            case 1:
                return "Family";
            case 2:
                return "Colors";
            case 3:
                return "Phrase";
            default:
                return "Miwok";
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
