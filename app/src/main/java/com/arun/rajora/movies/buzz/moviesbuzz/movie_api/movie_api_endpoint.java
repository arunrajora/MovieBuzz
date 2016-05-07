package com.arun.rajora.movies.buzz.moviesbuzz.movie_api;

import java.security.PrivateKey;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rajor on 04-May-16.
 */
public interface movie_api_endpoint {
	static String API_KEY="YOUR_KEY_HERE";
	@GET("movie/{category}?api_key="+API_KEY)
	Call<movie_list_data_model> getMoviesList(
			@Path("category") String category,
			@Query("page") int page
	);


	@GET("movie/{id}/reviews?api_key="+API_KEY)
	Call<movie_review_data_model> getMovieReview(
			@Path("id") int id,
			@Query("page") int page
	);

	@GET("movie/{id}/videos?api_key="+API_KEY)
	Call<movie_trailer_data_model> getMovieTrailers(
			@Path("id") int id,
			@Query("page") int page
	);
	@GET("movie/{id}?api_key="+API_KEY)
	Call<Result> getMovieDetails(
			@Path("id") String id
	);
}
