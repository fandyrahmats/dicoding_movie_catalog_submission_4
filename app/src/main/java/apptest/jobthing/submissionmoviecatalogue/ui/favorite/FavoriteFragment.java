package apptest.jobthing.submissionmoviecatalogue.ui.favorite;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import apptest.jobthing.submissionmoviecatalogue.R;
import apptest.jobthing.submissionmoviecatalogue.adapter.ViewPagerAdapter;
import apptest.jobthing.submissionmoviecatalogue.ui.favorite.favMovie.FavMovieFragment;
import apptest.jobthing.submissionmoviecatalogue.ui.favorite.favTvShow.FavTvShowFragment;
import apptest.jobthing.submissionmoviecatalogue.ui.movie.MovieFragment;
import apptest.jobthing.submissionmoviecatalogue.ui.tvshow.TvShowFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    private static final String TAG = FavoriteFragment.class.getSimpleName();

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "FavoriteFragmentLoaded");
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = view.findViewById(R.id.viewpager);
        TabLayout tabLayout = view.findViewById(R.id.tabs);
        prepareViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void prepareViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new FavMovieFragment(), getResources().getString(R.string.title_tab_movie));
        viewPagerAdapter.addFragment(new FavTvShowFragment(), getResources().getString(R.string.title_tab_tv));
        viewPager.setAdapter(viewPagerAdapter);
    }
}
