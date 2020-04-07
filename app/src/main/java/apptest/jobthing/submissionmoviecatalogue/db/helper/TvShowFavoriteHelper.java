package apptest.jobthing.submissionmoviecatalogue.db.helper;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import apptest.jobthing.submissionmoviecatalogue.model.TvShowResultItem;
import io.realm.Realm;
import io.realm.RealmResults;

public class TvShowFavoriteHelper {

    private Realm mRealm;

    public TvShowFavoriteHelper(Realm realm) {
        this.mRealm = realm;
    }

    public void save(final TvShowResultItem movieResponse) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                movieResponse.setFavorite(true);
                realm.copyToRealm(movieResponse);
            }
        });
    }

    public ArrayList<TvShowResultItem> getAllTvShowFav() {
        RealmResults<TvShowResultItem> resultItems = mRealm.where(TvShowResultItem.class).findAll();
        return new ArrayList<>(mRealm.copyFromRealm(resultItems));
    }

    public boolean favorite(Integer id) {
        TvShowResultItem model = mRealm.where(TvShowResultItem.class).equalTo("id", id).findFirst();
        if (model != null) {
            return model.isFavorite();
        } else {
            return false;
        }
    }

    public void delete(Integer id) {
        final RealmResults<TvShowResultItem> model = mRealm.where(TvShowResultItem.class).equalTo("id", id).findAll();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                model.deleteAllFromRealm();
            }
        });
    }
}
