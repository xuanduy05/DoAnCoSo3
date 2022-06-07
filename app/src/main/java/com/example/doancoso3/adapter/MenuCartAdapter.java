package com.example.doancoso3.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.doancoso3.CartOrder1Fragment;
import com.example.doancoso3.ui.gallery.GalleryFragment;
import com.example.doancoso3.ui.slideshow.SlideshowFragment;

public class MenuCartAdapter extends FragmentPagerAdapter {
    private String listTab[] = {"Ordering","Ordered"};

    private SlideshowFragment slideshowFragment;
    private GalleryFragment galleryFragment;
    private CartOrder1Fragment cartOrder1Fragment;


    public MenuCartAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        slideshowFragment = new SlideshowFragment();
        galleryFragment = new GalleryFragment();
        cartOrder1Fragment = new CartOrder1Fragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new CartOrder1Fragment();
            case 1:
                return new SlideshowFragment();
            default:
                return new SlideshowFragment();
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
