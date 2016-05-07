
package com.arun.rajora.movies.buzz.moviesbuzz.movie_api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class movie_list_data_model implements Parcelable{

	private int page;
	private List<Result> results;
	private int total_results;
	private int total_pages;
	movie_list_data_model()
	{
		results=new ArrayList<Result>();
	}

	protected movie_list_data_model(Parcel in) {
		page = in.readInt();
		results=new ArrayList<Result>();
		in.readTypedList(results,Result.CREATOR);
		total_results = in.readInt();
		total_pages = in.readInt();
		total_pages=in.readInt();
	}

	public static final Creator<movie_list_data_model> CREATOR = new Creator<movie_list_data_model>() {
		@Override
		public movie_list_data_model createFromParcel(Parcel in) {
			return new movie_list_data_model(in);
		}

		@Override
		public movie_list_data_model[] newArray(int size) {
			return new movie_list_data_model[size];
		}
	};

	/**
	 *
	 * @return
	 *     The page
	 */
	public int getPage() {
		return page;
	}

	/**
	 *
	 * @param page
	 *     The page
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 *
	 * @return
	 *     The results
	 */
	public List<Result> getResults() {
		return results;
	}

	/**
	 *
	 * @param results
	 *     The results
	 */
	public void setResults(List<Result> results) {
		this.results = results;
	}

	/**
	 *
	 * @return
	 *     The total_results
	 */
	public int getTotalResults() {
		return total_results;
	}

	/**
	 *
	 * @param total_results
	 *     The total_results
	 */
	public void setTotalResults(int total_results) {
		this.total_results = total_results;
	}

	/**
	 *
	 * @return
	 *     The total_pages
	 */
	public int getTotalPages() {
		return total_pages;
	}

	/**
	 *
	 * @param total_pages
	 *     The total_pages
	 */
	public void setTotalPages(int total_pages) {
		this.total_pages = total_pages;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeInt(page);
		dest.writeTypedList(results);
		dest.writeInt(total_results);
		dest.writeInt(total_pages);
	}
}
