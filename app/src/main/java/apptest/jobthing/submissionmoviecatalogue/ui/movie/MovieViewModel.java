package apptest.jobthing.submissionmoviecatalogue.ui.movie;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;

import apptest.jobthing.submissionmoviecatalogue.BuildConfig;
import apptest.jobthing.submissionmoviecatalogue.model.MovieResponse;
import apptest.jobthing.submissionmoviecatalogue.model.MovieResultItem;
import apptest.jobthing.submissionmoviecatalogue.network.ApiEndPoint;

public class MovieViewModel extends ViewModel {

    private static final String TAG = MovieViewModel.class.getSimpleName();

    private MutableLiveData<ArrayList<MovieResultItem>> listMovie = new MutableLiveData<>();

    void loadMovieList(String language) {
        AndroidNetworking.get(ApiEndPoint.ENDPOINT_MOVIE)
                .addPathParameter("api_key", BuildConfig.API_KEY)
                .addPathParameter("language", language)
                .setTag(this)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(MovieResponse.class, new ParsedRequestListener<MovieResponse>() {
                    @Override
                    public void onResponse(MovieResponse response) {
                        Log.d(TAG, "onResponseMovie: " + response.toString());
                        listMovie.postValue(response.getResults());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, "onErrorKI");
                    }
                });
    }

    MutableLiveData<ArrayList<MovieResultItem>> getListMovie() {
        return listMovie;
    }
}
