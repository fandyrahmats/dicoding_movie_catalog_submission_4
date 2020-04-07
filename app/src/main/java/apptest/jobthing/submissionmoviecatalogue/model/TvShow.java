package apptest.jobthing.submissionmoviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class TvShow implements Parcelable {

    private String title;
    private String releaseDate;
    private int poster;
    private String description;
    private String rating;

    protected TvShow(Parcel in) {
        title = in.readString();
        releaseDate = in.readString();
        poster = in.readInt();
        description = in.readString();
        rating = in.readString();
    }

    public TvShow() {

    }

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(releaseDate);
        parcel.writeInt(poster);
        parcel.writeString(description);
        parcel.writeString(rating);
    }
}
