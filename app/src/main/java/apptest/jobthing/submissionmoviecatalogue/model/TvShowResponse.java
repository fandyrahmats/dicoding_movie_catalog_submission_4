package apptest.jobthing.submissionmoviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class TvShowResponse implements Parcelable {

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private ArrayList<TvShowResultItem> results;

	@SerializedName("total_results")
	private int totalResults;

	protected TvShowResponse(Parcel in) {
		page = in.readInt();
		totalPages = in.readInt();
		results = in.createTypedArrayList(TvShowResultItem.CREATOR);
		totalResults = in.readInt();
	}

	public static final Creator<TvShowResponse> CREATOR = new Creator<TvShowResponse>() {
		@Override
		public TvShowResponse createFromParcel(Parcel in) {
			return new TvShowResponse(in);
		}

		@Override
		public TvShowResponse[] newArray(int size) {
			return new TvShowResponse[size];
		}
	};

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setResults(ArrayList<TvShowResultItem> results){
		this.results = results;
	}

	public ArrayList<TvShowResultItem> getResults(){
		return results;
	}

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	@Override
 	public String toString(){
		return 
			"TvShowResponse{" + 
			"page = '" + page + '\'' + 
			",total_pages = '" + totalPages + '\'' + 
			",results = '" + results + '\'' + 
			",total_results = '" + totalResults + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeInt(page);
		parcel.writeInt(totalPages);
		parcel.writeTypedList(results);
		parcel.writeInt(totalResults);
	}


}