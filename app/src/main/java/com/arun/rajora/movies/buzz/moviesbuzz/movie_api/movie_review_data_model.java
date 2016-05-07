package com.arun.rajora.movies.buzz.moviesbuzz.movie_api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajor on 04-May-16.
 */
public class movie_review_data_model implements Parcelable{

	private int id;
	private int page;
	private List<Result> results = new ArrayList<Result>();
	private int total_pages;
	private int total_results;

	protected movie_review_data_model(Parcel in) {
		id = in.readInt();
		page = in.readInt();
		in.readTypedList(results,Result.CREATOR);
		total_pages = in.readInt();
		total_results = in.readInt();
	}

	public static final Creator<movie_review_data_model> CREATOR = new Creator<movie_review_data_model>() {
		@Override
		public movie_review_data_model createFromParcel(Parcel in) {
			return new movie_review_data_model(in);
		}

		@Override
		public movie_review_data_model[] newArray(int size) {
			return new movie_review_data_model[size];
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
	 * The page
	 */
	public int getPage() {
		return page;
	}

	/**
	 *
	 * @param page
	 * The page
	 */
	public void setPage(int page) {
		this.page = page;
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

	/**
	 *
	 * @return
	 * The total_pages
	 */
	public int getTotalPages() {
		return total_pages;
	}

	/**
	 *
	 * @param total_pages
	 * The total_pages
	 */
	public void setTotalPages(int total_pages) {
		this.total_pages = total_pages;
	}

	/**
	 *
	 * @return
	 * The total_results
	 */
	public int getTotalResults() {
		return total_results;
	}

	/**
	 *
	 * @param total_results
	 * The total_results
	 */
	public void setTotalResults(int total_results) {
		this.total_results = total_results;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeInt(id);
		dest.writeInt(page);
		dest.writeTypedList(results);
		dest.writeInt(total_pages);
		dest.writeInt(total_results);
	}

	public static class Result implements Parcelable{

		private String id;
		private String author;
		private String content;
		private String url;

		protected Result(Parcel in) {
			id = in.readString();
			author = in.readString();
			content = in.readString();
			url = in.readString();
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
		 * The author
		 */
		public String getAuthor() {
			return author;
		}

		/**
		 *
		 * @param author
		 * The author
		 */
		public void setAuthor(String author) {
			this.author = author;
		}

		/**
		 *
		 * @return
		 * The content
		 */
		public String getContent() {
			return content;
		}

		/**
		 *
		 * @param content
		 * The content
		 */
		public void setContent(String content) {
			this.content = content;
		}

		/**
		 *
		 * @return
		 * The url
		 */
		public String getUrl() {
			return url;
		}

		/**
		 *
		 * @param url
		 * The url
		 */
		public void setUrl(String url) {
			this.url = url;
		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {

			dest.writeString(id);
			dest.writeString(author);
			dest.writeString(content);
			dest.writeString(url);
		}
	}
}
