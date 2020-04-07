package apptest.jobthing.submissionmoviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;

public class TvShowResultItem extends RealmObject implements Parcelable {

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("overview")
    private String overview;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("vote_average")
    private String voteAverage;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    @SerializedName("vote_count")
    private int voteCount;

    private boolean isFavorite;

    public TvShowResultItem(Parcel in) {
        firstAirDate = in.readString();
        overview = in.readString();
        originalLanguage = in.readString();
        posterPath = in.readString();
        backdropPath = in.readString();
        originalName = in.readString();
        popularity = in.readDouble();
        voteAverage = in.readString();
        name = in.readString();
        id = in.readInt();
        voteCount = in.readInt();
    }

    public static final Creator<TvShowResultItem> CREATOR = new Creator<TvShowResultItem>() {
        @Override
        public TvShowResultItem createFromParcel(Parcel in) {
            return new TvShowResultItem(in);
        }

        @Override
        public TvShowResultItem[] newArray(int size) {
            return new TvShowResultItem[size];
        }
    };

    public TvShowResultItem() {

    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public static Creator<TvShowResultItem> getCREATOR() {
        return CREATOR;
    }

    public void setFirstAirDate(String firstAirDate){
        this.firstAirDate = firstAirDate;
    }

    public String getFirstAirDate(){
        return firstAirDate;
    }

    public void setOverview(String overview){
        this.overview = overview;
    }

    public String getOverview(){
        return overview;
    }

    public void setOriginalLanguage(String originalLanguage){
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalLanguage(){
        return originalLanguage;
    }

    public void setPosterPath(String posterPath){
        this.posterPath = posterPath;
    }

    public String getPosterPath(){
        return posterPath;
    }

    public void setBackdropPath(String backdropPath){
        this.backdropPath = backdropPath;
    }

    public String getBackdropPath(){
        return backdropPath;
    }

    public void setOriginalName(String originalName){
        this.originalName = originalName;
    }

    public String getOriginalName(){
        return originalName;
    }

    public void setPopularity(double popularity){
        this.popularity = popularity;
    }

    public double getPopularity(){
        return popularity;
    }

    public void setVoteAverage(String voteAverage){
        this.voteAverage = voteAverage;
    }

    public String getVoteAverage(){
        return voteAverage;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setVoteCount(int voteCount){
        this.voteCount = voteCount;
    }

    public int getVoteCount(){
        return voteCount;
    }

    @Override
    public String toString(){
        return
                "ResultsItem{" +
                        "first_air_date = '" + firstAirDate + '\'' +
                        ",overview = '" + overview + '\'' +
                        ",original_language = '" + originalLanguage + '\'' +
                        ",poster_path = '" + posterPath + '\'' +
                        ",backdrop_path = '" + backdropPath + '\'' +
                        ",original_name = '" + originalName + '\'' +
                        ",popularity = '" + popularity + '\'' +
                        ",vote_average = '" + voteAverage + '\'' +
                        ",name = '" + name + '\'' +
                        ",id = '" + id + '\'' +
                        ",vote_count = '" + voteCount + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstAirDate);
        parcel.writeString(overview);
        parcel.writeString(originalLanguage);
        parcel.writeString(posterPath);
        parcel.writeString(backdropPath);
        parcel.writeString(originalName);
        parcel.writeDouble(popularity);
        parcel.writeString(voteAverage);
        parcel.writeString(name);
        parcel.writeInt(id);
        parcel.writeInt(voteCount);
    }
}
