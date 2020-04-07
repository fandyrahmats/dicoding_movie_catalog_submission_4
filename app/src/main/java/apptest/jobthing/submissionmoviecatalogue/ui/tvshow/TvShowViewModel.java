package apptest.jobthing.submissionmoviecatalogue.ui.tvshow;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.ArrayList;

import apptest.jobthing.submissionmoviecatalogue.BuildConfig;
import apptest.jobthing.submissionmoviecatalogue.model.TvShowResponse;
import apptest.jobthing.submissionmoviecatalogue.model.TvShowResultItem;
import apptest.jobthing.submissionmoviecatalogue.network.ApiEndPoint;

public class TvShowViewModel extends ViewModel {

    private static final String TAG = TvShowViewModel.class.getSimpleName();

    private MutableLiveData<ArrayList<TvShowResultItem>> listMovie = new MutableLiveData<>();

    void loadTvShowList(String language) {
        AndroidNetworking.get(ApiEndPoint.ENDPOINT_TV_SHOW)
                .addPathParameter("api_key", BuildConfig.API_KEY)
                .addPathParameter("language", language)
                .setTag(this)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(TvShowResponse.class, new ParsedRequestListener<TvShowResponse>() {
                    @Override
                    public void onResponse(TvShowResponse response) {
                        Log.d(TAG, "onResponseMovie: "+response.toString());
                        listMovie.postValue(response.getResults());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(TAG, "onError: ", anError);
                    }
                });
    }

    MutableLiveData<ArrayList<TvShowResultItem>> getListMovie() {
        return listMovie;
    }
}
