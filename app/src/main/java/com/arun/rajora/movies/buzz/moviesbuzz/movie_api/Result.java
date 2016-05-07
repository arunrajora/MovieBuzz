
package com.arun.rajora.movies.buzz.moviesbuzz.movie_api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
public class Result implements Parcelable{

    private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    private List<Integer> genre_ids;
    private int id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    private double vote_average;
	Result()
	{
		genre_ids=new ArrayList<Integer>();
	}

	protected Result(Parcel in) {
		poster_path = in.readString();
		adult = in.readByte() != 0;
		overview = in.readString();
		release_date = in.readString();
		id = in.readInt();
		original_title = in.readString();
		original_language = in.readString();
		title = in.readString();
		backdrop_path = in.readString();
		popularity = in.readDouble();
		vote_count = in.readInt();
		video = in.readByte() != 0;
		vote_average = in.readDouble();
	}

	public static final Creator<Result> CREATOR = new Creator<Result>() {
		@Override
		public Result createFromParcel(Parcel in) {
			return new Result(in);
		}

		@Override
		public Result[] newArray(int size) {
			return new Result[size];
		}
	};

	/**
     * 
     * @return
     *     The poster_path
     */
    public String getPosterPath() {
        return poster_path;
    }

    /**
     * 
     * @param poster_path
     *     The poster_path
     */
    public void setPosterPath(String poster_path) {
        this.poster_path = poster_path;
    }

    /**
     * 
     * @return
     *     The adult
     */
    public boolean isAdult() {
        return adult;
    }

    /**
     * 
     * @param adult
     *     The adult
     */
    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    /**
     * 
     * @return
     *     The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * 
     * @param overview
     *     The overview
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * 
     * @return
     *     The release_date
     */
    public String getReleaseDate() {
        return release_date;
    }

    /**
     * 
     * @param release_date
     *     The release_date
     */
    public void setReleaseDate(String release_date) {
        this.release_date = release_date;
    }

    /**
     * 
     * @return
     *     The genre_ids
     */
    public List<Integer> getGenreIds() {
        return genre_ids;
    }

    /**
     * 
     * @param genre_ids
     *     The genre_ids
     */
    public void setGenreIds(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    /**
     * 
     * @return
     *     The id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The original_title
     */
    public String getOriginalTitle() {
        return original_title;
    }

    /**
     * 
     * @param original_title
     *     The original_title
     */
    public void setOriginalTitle(String original_title) {
        this.original_title = original_title;
    }

    /**
     * 
     * @return
     *     The original_language
     */
    public String getOriginalLanguage() {
        return original_language;
    }

    /**
     * 
     * @param original_language
     *     The original_language
     */
    public void setOriginalLanguage(String original_language) {
        this.original_language = original_language;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The backdrop_path
     */
    public String getBackdropPath() {
        return backdrop_path;
    }

    /**
     * 
     * @param backdrop_path
     *     The backdrop_path
     */
    public void setBackdropPath(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    /**
     * 
     * @return
     *     The popularity
     */
    public double getPopularity() {
        return popularity;
    }

    /**
     * 
     * @param popularity
     *     The popularity
     */
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    /**
     * 
     * @return
     *     The vote_count
     */
    public int getVoteCount() {
        return vote_count;
    }

    /**
     * 
     * @param vote_count
     *     The vote_count
     */
    public void setVoteCount(int vote_count) {
        this.vote_count = vote_count;
    }

    /**
     * 
     * @return
     *     The video
     */
    public boolean isVideo() {
        return video;
    }

    /**
     * 
     * @param video
     *     The video
     */
    public void setVideo(boolean video) {
        this.video = video;
    }

    /**
     * 
     * @return
     *     The vote_average
     */
    public double getVoteAverage() {
        return vote_average;
    }

    /**
     * 
     * @param vote_average
     *     The vote_average
     */
    public void setVoteAverage(double vote_average) {
        this.vote_average = vote_average;
    }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(poster_path);
		dest.writeByte((byte) (adult ? 1 : 0));
		dest.writeString(overview);
		dest.writeString(release_date);
		dest.writeInt(id);
		dest.writeString(original_title);
		dest.writeString(original_language);
		dest.writeString(title);
		dest.writeString(backdrop_path);
		dest.writeDouble(popularity);
		dest.writeInt(vote_count);
		dest.writeByte((byte) (video ? 1 : 0));
		dest.writeDouble(vote_average);
	}
}
