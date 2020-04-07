package apptest.jobthing.submissionmoviecatalogue.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;

import apptest.jobthing.submissionmoviecatalogue.R;
import apptest.jobthing.submissionmoviecatalogue.db.helper.MovieFavoriteHelper;
import apptest.jobthing.submissionmoviecatalogue.db.helper.TvShowFavoriteHelper;
import apptest.jobthing.submissionmoviecatalogue.model.MovieResultItem;
import apptest.jobthing.submissionmoviecatalogue.model.TvShowResultItem;
import apptest.jobthing.submissionmoviecatalogue.utils.CommonUtils;
import io.realm.Realm;

public class DetailMovieActivity extends AppCompatActivity {

    private static final String TAG = DetailMovieActivity.class.getSimpleName();

    public static String KEY_BUNDLE_MOVIE = "key_bundle_movie";
    public static String KEY_BUNDLE_TV = "key_bundle_tv";
    private MovieFavoriteHelper movieFavoriteHelper;
    private TvShowFavoriteHelper tvShowFavoriteHelper;

    private MovieResultItem movie;
    private TvShowResultItem tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        prepare();
    }

    private void prepare() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        movieFavoriteHelper = new MovieFavoriteHelper(realm);
        tvShowFavoriteHelper = new TvShowFavoriteHelper(realm);

        TextView tvTitle = findViewById(R.id.tv_movie_title);
        TextView tvReleaseDate = findViewById(R.id.tv_release_date);
        TextView tvRating = findViewById(R.id.tv_rating);
        TextView tvDescription = findViewById(R.id.tv_movie_description);
        ImageView imgCover = findViewById(R.id.img_movie_cover);
        Intent intent = getIntent();
        if (intent != null) {
            movie = intent.getExtras().getParcelable(KEY_BUNDLE_MOVIE);
            tvShow = intent.getExtras().getParcelable(KEY_BUNDLE_TV);
            if (movie != null) {
                tvTitle.setText(movie.getTitle());
                tvDescription.setText(movie.getOverview());
                tvReleaseDate.setText(CommonUtils.dateFormatter(movie.getReleaseDate()));
                tvRating.setText(String.valueOf(movie.getVoteAverage()));

                String imageUrl = CommonUtils.showingImage(movie.getPosterPath());

                Glide.with(this)
                        .load(imageUrl)
                        .centerCrop()
                        .into(imgCover);

                getSupportActionBar().setTitle(movie.getTitle());
            } else if (tvShow != null) {
                tvTitle.setText(tvShow.getName());
                tvDescription.setText(tvShow.getOverview());
                tvReleaseDate.setText(String.valueOf(tvShow.getPopularity()));
                tvRating.setText(tvShow.getVoteAverage());

                String imageUrl = CommonUtils.showingImage(tvShow.getPosterPath());

                Glide.with(this)
                        .load(imageUrl)
                        .centerCrop()
                        .into(imgCover);

                getSupportActionBar().setTitle(tvShow.getName());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        MenuItem item = menu.findItem(R.id.menu_favorite);
        item.setActionView(R.layout.switch_favorite);
        final ToggleButton favBtn = item.getActionView().findViewById(R.id.switch_favorite);
        favBtn.setChecked(checkCategory());
        favBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    if (movie!=null) {
                        movieFavoriteHelper.save(movie);
                        Toast.makeText(DetailMovieActivity.this, getResources().getString(R.string.added_to_favorite), Toast.LENGTH_SHORT).show();
                    } else {
                        tvShowFavoriteHelper.save(tvShow);
                        Toast.makeText(DetailMovieActivity.this, getResources().getString(R.string.added_to_favorite_tv), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (movie!=null) {
                        movieFavoriteHelper.delete(movie.getId());
                        Toast.makeText(DetailMovieActivity.this, getResources().getString(R.string.deleted_from_favorite), Toast.LENGTH_SHORT).show();
                    } else {
                        tvShowFavoriteHelper.delete(tvShow.getId());
                        Toast.makeText(DetailMovieActivity.this, getResources().getString(R.string.deleted_from_favorite_tv), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private boolean checkCategory() {
        if (movie!=null) {
            final boolean isFavMovie = movieFavoriteHelper.favorite(movie.getId());
            if (isFavMovie) {
                return true;
            } else {
                return false;
            }
        } else {
            final boolean isFavTv = tvShowFavoriteHelper.favorite(tvShow.getId());
            if (isFavTv) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
