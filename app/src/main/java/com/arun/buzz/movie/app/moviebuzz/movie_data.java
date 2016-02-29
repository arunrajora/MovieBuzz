package com.arun.buzz.movie.app.moviebuzz;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rajor on 28-Feb-16.
 */
public class movie_data implements Parcelable{
	public String poster_path;
	public String adult;
	public String overview;
	public String release_date;
	public String id;
	public String original_title;
	public String title;
	public String original_language;
	public String backdrop_path;
	public String popularity;
	public String vote_count;
	public String video;
	public String vote_average;
	public static final Creator<movie_data> CREATOR=new Creator<movie_data>() {
		@Override
		public movie_data createFromParcel(Parcel source) {
			return new movie_data(source);
		}

		@Override
		public movie_data[] newArray(int size) {
			return new movie_data[size];
		}
	};
	movie_data()
	{
		poster_path="";
	}
	movie_data(Parcel in)
	{
		poster_path=in.readString();
		adult=in.readString();
		overview=in.readString();
		release_date=in.readString();
		id=in.readString();
		original_title=in.readString();
		title=in.readString();
		original_language=in.readString();
		backdrop_path=in.readString();
		popularity=in.readString();
		vote_count=in.readString();
		video=in.readString();
		vote_average=in.readString();
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(poster_path);
		dest.writeString(adult);
		dest.writeString(overview);
		dest.writeString(release_date);
		dest.writeString(id);
		dest.writeString(original_title);
		dest.writeString(title);
		dest.writeString(original_language);
		dest.writeString(backdrop_path);
		dest.writeString(popularity);
		dest.writeString(vote_count);
		dest.writeString(video);
		dest.writeString(vote_average);
	}

	@Override
	public int describeContents() {
		return 0;
	}
}
