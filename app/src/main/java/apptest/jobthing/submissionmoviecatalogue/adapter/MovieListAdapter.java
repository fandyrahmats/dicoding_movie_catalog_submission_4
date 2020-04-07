package apptest.jobthing.submissionmoviecatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import apptest.jobthing.submissionmoviecatalogue.R;
import apptest.jobthing.submissionmoviecatalogue.model.MovieResultItem;
import apptest.jobthing.submissionmoviecatalogue.ui.DetailMovieActivity;
import apptest.jobthing.submissionmoviecatalogue.utils.CommonUtils;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private ArrayList<MovieResultItem> list;
    private Context context;

    public void setList(ArrayList<MovieResultItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public MovieListAdapter(ArrayList<MovieResultItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MovieResultItem movie = list.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvShortDesc.setText(movie.getOverview());
        holder.tvShortDesc.setMaxLines(2);
        holder.tvReleaseDate.setText(CommonUtils.dateFormatter(movie.getReleaseDate()));
        holder.tvRating.setText(String.valueOf(movie.getVoteAverage()));

        String imageUrl = CommonUtils.showingImage(movie.getPosterPath());

        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .placeholder(holder.itemView.getContext().getDrawable(R.drawable.placeholder_poster))
                .centerCrop()
                .into(holder.imgPoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.KEY_BUNDLE_MOVIE, movie);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvShortDesc, tvReleaseDate, tvRating;
        ImageView imgPoster;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvShortDesc = itemView.findViewById(R.id.tv_short_desc);
            tvReleaseDate = itemView.findViewById(R.id.tv_release_date);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvRating = itemView.findViewById(R.id.tv_rating);
        }
    }
}
