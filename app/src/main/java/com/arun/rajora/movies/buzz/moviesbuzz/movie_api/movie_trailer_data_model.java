package com.arun.rajora.movies.buzz.moviesbuzz.movie_api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajor on 04-May-16.
 */
public class movie_trailer_data_model implements Parcelable{

	private int id;
	private List<Result> results = new ArrayList<Result>();

	protected movie_trailer_data_model(Parcel in) {

		id = in.readInt();
		in.readTypedList(results,Result.CREATOR);
	}

	public static final Parcelable.Creator<movie_trailer_data_model> CREATOR = new Parcelable.Creator<movie_trailer_data_model>() {
		@Override
		public movie_trailer_data_model createFromParcel(Parcel in) {
			return new movie_trailer_data_model(in);
		}

		@Override
		public movie_trailer_data_model[] newArray(int size) {
			return new movie_trailer_data_model[size];
		}
	};

	/**
	 *
	 * @return
	 * The id
	 */
	public int getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 * The id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 *
	 * @return
	 * The results
	 */
	public List<Result> getResults() {
		return results;
	}

	/**
	 *
	 * @param results
	 * The results
	 */
	public void setResults(List<Result> results) {
		this.results = results;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeInt(id);
		dest.writeTypedList(results);
	}

	public static class Result implements Parcelable{

		private String id;
		private String iso6391;
		private String iso31661;
		private String key;
		private String name;
		private String site;
		private int size;
		private String type;

		protected Result(Parcel in) {
			id = in.readString();
			iso6391 = in.readString();
			iso31661 = in.readString();
			key = in.readString();
			name = in.readString();
			site = in.readString();
			size = in.readInt();
			type = in.readString();
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
		 * The id
		 */
		public String getId() {
			return id;
		}

		/**
		 *
		 * @param id
		 * The id
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 *
		 * @return
		 * The iso6391
		 */
		public String getIso6391() {
			return iso6391;
		}

		/**
		 *
		 * @param iso6391
		 * The iso_639_1
		 */
		public void setIso6391(String iso6391) {
			this.iso6391 = iso6391;
		}

		/**
		 *
		 * @return
		 * The iso31661
		 */
		public String getIso31661() {
			return iso31661;
		}

		/**
		 *
		 * @param iso31661
		 * The iso_3166_1
		 */
		public void setIso31661(String iso31661) {
			this.iso31661 = iso31661;
		}

		/**
		 *
		 * @return
		 * The key
		 */
		public String getKey() {
			return key;
		}

		/**
		 *
		 * @param key
		 * The key
		 */
		public void setKey(String key) {
			this.key = key;
		}

		/**
		 *
		 * @return
		 * The name
		 */
		public String getName() {
			return name;
		}

		/**
		 *
		 * @param name
		 * The name
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 *
		 * @return
		 * The site
		 */
		public String getSite() {
			return site;
		}

		/**
		 *
		 * @param site
		 * The site
		 */
		public void setSite(String site) {
			this.site = site;
		}

		/**
		 *
		 * @return
		 * The size
		 */
		public int getSize() {
			return size;
		}

		/**
		 *
		 * @param size
		 * The size
		 */
		public void setSize(int size) {
			this.size = size;
		}

		/**
		 *
		 * @return
		 * The type
		 */
		public String getType() {
			return type;
		}

		/**
		 *
		 * @param type
		 * The type
		 */
		public void setType(String type) {
			this.type = type;
		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {

			dest.writeString(id);
			dest.writeString(iso6391);
			dest.writeString(iso31661);
			dest.writeString(key);
			dest.writeString(name);
			dest.writeString(site);
			dest.writeInt(size);
			dest.writeString(type);
		}
	}

}
