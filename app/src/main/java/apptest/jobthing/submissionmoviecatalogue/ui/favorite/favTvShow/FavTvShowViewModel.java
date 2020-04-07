package apptest.jobthing.submissionmoviecatalogue.ui.favorite.favTvShow;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import apptest.jobthing.submissionmoviecatalogue.db.helper.TvShowFavoriteHelper;
import apptest.jobthing.submissionmoviecatalogue.model.TvShowResultItem;
import io.realm.Realm;

public class FavTvShowViewModel extends ViewModel {

    private Realm realm;

    private static final String TAG = FavTvShowViewModel.class.getSimpleName();
    private MutableLiveData<ArrayList<TvShowResultItem>> listMovie = new MutableLiveData<>();

    public FavTvShowViewModel() {
        this.realm = Realm.getDefaultInstance();
        loadFavTvShowList();
    }

    void loadFavTvShowList() {
        realm = Realm.getDefaultInstance();
        TvShowFavoriteHelper favoriteHelper = new TvShowFavoriteHelper(realm);
        listMovie.postValue(favoriteHelper.getAllTvShowFav());
    }

    MutableLiveData<ArrayList<TvShowResultItem>> getListMovie() {
        return listMovie;
    }
    
}
