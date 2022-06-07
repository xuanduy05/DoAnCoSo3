package com.example.doancoso3.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.doancoso3.MenuMonAnFragment;
import com.example.doancoso3.MenuPhongFragment;
import com.example.doancoso3.ui.gallery.GalleryFragment;
import com.example.doancoso3.ui.slideshow.SlideshowFragment;


public class MenuAdapter extends FragmentPagerAdapter {
    private String listTab[] = {"Food","Room"};
    private SlideshowFragment slideshowFragment;
    private GalleryFragment galleryFragment;
    private MenuMonAnFragment menuMonAnFragment;
    private MenuPhongFragment menuPhongFragment;
    public MenuAdapter(@NonNull FragmentManager fm,int behavior) {
        super(fm,behavior);
//        slideshowFragment = new SlideshowFragment();
//        galleryFragment = new GalleryFragment();
        menuMonAnFragment = new MenuMonAnFragment();
        menuPhongFragment = new MenuPhongFragment();

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MenuMonAnFragment();
            case 1:
                return new MenuPhongFragment();
            default:
                return new MenuPhongFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTab[position];
    }
}
