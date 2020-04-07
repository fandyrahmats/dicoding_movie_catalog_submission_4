package apptest.jobthing.submissionmoviecatalogue.db.helper;
import androidx.annotation.NonNull;

import java.util.ArrayList;

import apptest.jobthing.submissionmoviecatalogue.model.MovieResultItem;
import io.realm.Realm;
import io.realm.RealmResults;

public class MovieFavoriteHelper {

    private Realm mRealm;

    public MovieFavoriteHelper(Realm realm) {
        this.mRealm = realm;
    }

    public void save(final MovieResultItem movieResponse) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                movieResponse.setFavorite(true);
                realm.copyToRealm(movieResponse);
            }
        });
    }

    public ArrayList<MovieResultItem> getAllFavMovie() {
        RealmResults<MovieResultItem> resultItems = mRealm.where(MovieResultItem.class).findAll();
        return new ArrayList<>(mRealm.copyFromRealm(resultItems));
    }

    public boolean favorite(Integer id) {
        MovieResultItem model = mRealm.where(MovieResultItem.class).equalTo("id", id).findFirst();
        if (model != null) {
            return model.isFavorite();
        } else {
            return false;
        }
    }

    public void delete(Integer id) {
        final RealmResults<MovieResultItem> model = mRealm.where(MovieResultItem.class).equalTo("id", id).findAll();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                model.deleteAllFromRealm();
            }
        });
    }
}
