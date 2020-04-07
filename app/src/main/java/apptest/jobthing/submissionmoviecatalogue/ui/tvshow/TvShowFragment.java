package apptest.jobthing.submissionmoviecatalogue.ui.tvshow;


import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.Locale;

import apptest.jobthing.submissionmoviecatalogue.R;
import apptest.jobthing.submissionmoviecatalogue.adapter.TvShowListAdapter;
import apptest.jobthing.submissionmoviecatalogue.model.TvShow;
import apptest.jobthing.submissionmoviecatalogue.model.TvShowResponse;
import apptest.jobthing.submissionmoviecatalogue.model.TvShowResultItem;
import apptest.jobthing.submissionmoviecatalogue.ui.movie.MovieViewModel;
import apptest.jobthing.submissionmoviecatalogue.utils.AppConstants;
import apptest.jobthing.submissionmoviecatalogue.utils.CommonUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment{

    private static final String TAG = TvShowFragment.class.getSimpleName();

    private TvShowViewModel tvShowViewModel;
    private RecyclerView rvMain;
    private TvShowListAdapter adapter;
    private ShimmerFrameLayout placeholderList;
    private SwipeRefreshLayout mSwipe;
    private View viewError;
    private ArrayList<TvShowResultItem> tvShows = new ArrayList<>();

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        tvShowViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TvShowViewModel.class);

        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMain = view.findViewById(R.id.rv_main);
        mSwipe = view.findViewById(R.id.swipe);
        placeholderList = view.findViewById(R.id.placeholder);
        viewError = view.findViewById(R.id.view_error);
        prepare();

    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onPause() {
        super.onPause();
        placeholderList.stopShimmer();
    }

    private void loadData() {
        showPlacehHolder(true);
        if (Locale.getDefault().getLanguage().equals("in")) {
            tvShowViewModel.loadTvShowList(AppConstants.ID_LANGUAGE);
        } else {
            tvShowViewModel.loadTvShowList(AppConstants.EN_LANGUAGE);
        }

        tvShowViewModel.getListMovie().observe(getActivity(), new Observer<ArrayList<TvShowResultItem>>() {
            @Override
            public void onChanged(ArrayList<TvShowResultItem> resultsItems) {
                if (resultsItems != null) {
                    viewError.setVisibility(View.GONE);
                    adapter.setList(resultsItems);
                } else {
                    viewError.setVisibility(View.VISIBLE);
                }
                showPlacehHolder(false);
            }
        });

    }

    private void showPlacehHolder(boolean isVisible) {
        if (isVisible) {
            placeholderList.setVisibility(View.VISIBLE);
            placeholderList.startShimmer();
        } else {
            placeholderList.setVisibility(View.GONE);
            placeholderList.stopShimmer();
        }
    }

    private void prepare() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        adapter = new TvShowListAdapter(tvShows, getActivity());
        rvMain.setHasFixedSize(true);
        rvMain.setLayoutManager(layoutManager);
        rvMain.setAdapter(adapter);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                mSwipe.setRefreshing(false);
            }
        });
    }
}
