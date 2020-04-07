package apptest.jobthing.submissionmoviecatalogue.ui.favorite.favMovie;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import apptest.jobthing.submissionmoviecatalogue.R;
import apptest.jobthing.submissionmoviecatalogue.adapter.MovieListAdapter;
import apptest.jobthing.submissionmoviecatalogue.model.MovieResultItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavMovieFragment extends Fragment {

    private static final String TAG = FavMovieFragment.class.getSimpleName();

    private FavMovieViewModel movieViewModel;
    private RecyclerView rvMain;
    private MovieListAdapter adapter;
    private SwipeRefreshLayout mSwipe;
    private ShimmerFrameLayout placeholderList;
    private View viewError;
    private ArrayList<MovieResultItem> movieList = new ArrayList<>();

    public FavMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        movieViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
                .get(FavMovieViewModel.class);
        return inflater.inflate(R.layout.fragment_movie, container, false);
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

    private void prepare() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        adapter = new MovieListAdapter(movieList, getActivity());
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

    private void showPlacehHolder(boolean isVisible) {
        if (isVisible) {
            placeholderList.setVisibility(View.VISIBLE);
            placeholderList.startShimmer();
        } else {
            placeholderList.setVisibility(View.GONE);
            placeholderList.stopShimmer();
        }
    }

    private void loadData() {
        showPlacehHolder(true);
        movieViewModel.loadFavMovieList();
        movieViewModel.getListMovie().observe(this, new Observer<ArrayList<MovieResultItem>>() {
            @Override
            public void onChanged(ArrayList<MovieResultItem> movieResultItems) {
                if (movieResultItems != null && movieResultItems.size() >= 1) {
                    viewError.setVisibility(View.GONE);
                    adapter.setList(movieResultItems);
                } else {
                    viewError.setVisibility(View.VISIBLE);
                }
                showPlacehHolder(false);
            }
        });
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


}
