package apptest.jobthing.submissionmoviecatalogue.ui.favorite.favMovie;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import apptest.jobthing.submissionmoviecatalogue.db.helper.MovieFavoriteHelper;
import apptest.jobthing.submissionmoviecatalogue.model.MovieResultItem;
import apptest.jobthing.submissionmoviecatalogue.ui.movie.MovieViewModel;
import io.realm.Realm;

public class FavMovieViewModel extends ViewModel {

    Realm realm;

    private static final String TAG = MovieViewModel.class.getSimpleName();
    private MutableLiveData<ArrayList<MovieResultItem>> listMovie = new MutableLiveData<>();

    public FavMovieViewModel() {
        this.realm = Realm.getDefaultInstance();
        loadFavMovieList();
    }

    void loadFavMovieList() {
        realm = Realm.getDefaultInstance();
        MovieFavoriteHelper favoriteHelper = new MovieFavoriteHelper(realm);
        listMovie.postValue(favoriteHelper.getAllFavMovie());
        Log.d(TAG, "loadFavMovieList: "+favoriteHelper.getAllFavMovie().size());
    }

    MutableLiveData<ArrayList<MovieResultItem>> getListMovie() {
        return listMovie;
    }

}
