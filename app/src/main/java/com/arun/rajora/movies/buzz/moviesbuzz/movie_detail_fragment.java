package com.arun.rajora.movies.buzz.moviesbuzz;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.arun.rajora.movies.buzz.moviesbuzz.movie_api.Result;
import com.arun.rajora.movies.buzz.moviesbuzz.movie_api.movie_api_endpoint;
import com.arun.rajora.movies.buzz.moviesbuzz.movie_api.movie_list_data_model;
import com.squareup.picasso.Picasso;
import com.arun.rajora.movies.buzz.moviesbuzz.movie_api.movie_review_data_model;
import com.arun.rajora.movies.buzz.moviesbuzz.movie_api.movie_trailer_data_model;
import com.arun.rajora.movies.buzz.moviesbuzz.movie_data.db_favourite_movies;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class movie_detail_fragment extends Fragment {
	private boolean mLoadedTrailer=false;
	private boolean mLoadedReview=false;
	private static final String  poster_base_uri = "https://image.tmdb.org/t/p/w185";
	private Result data;
	private reviews_adapter mReviewAdapter;
	private movie_trailer_adapter mTrailerAdapter;
	private static final String favourite_status[]={"Add to Favourites","Added to Favourites"};
	private db_favourite_movies database_fav;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView=inflater.inflate(R.layout.fragment_movie_detail, container, false);
		Bundle args=getArguments();
		mLoadedTrailer=mLoadedReview=false;
		if(args!=null) {
			setAdapter(rootView);
			if(args.containsKey("movie-data"));
			data = args.getParcelable("movie-data");
			if(args.containsKey("movie-review"))
			{
				mReviewAdapter.mDataSet = (ArrayList)args.getParcelableArrayList("movie-review");
				mLoadedReview=true;
			}
			if(args.containsKey("movie-trailer")) {
				mTrailerAdapter.mDataSet = (ArrayList) args.getParcelableArrayList("movie-trailer");
				mLoadedTrailer=true;
			}
		}
			if (savedInstanceState != null) {
				setAdapter(rootView);
				if (savedInstanceState.containsKey("movie-data"))
					data = savedInstanceState.getParcelable("movie-data");
				if (savedInstanceState.containsKey("movie-review")) {
					mReviewAdapter.mDataSet = (ArrayList) savedInstanceState.getParcelableArrayList("movie-review");
					mLoadedReview=true;
				}
				if (savedInstanceState.containsKey("movie-trailer")) {
					mTrailerAdapter.mDataSet = (ArrayList) savedInstanceState.getParcelableArrayList("movie-trailer");
					mLoadedTrailer=true;
				}
			}
		if(data!=null)
			{
				setDataToView(rootView);
				setDatabase_fav(rootView);
				if(!mLoadedReview)
					fetchReviews();
				else
				mReviewAdapter.notifyDataSetChanged();
				if(!mLoadedTrailer)
					fetchTrailer();
				else
					mTrailerAdapter.notifyDataSetChanged();
			}
		return rootView;
	}
	void setDataToView(View rootView){
		Picasso.with(getContext()).load(poster_base_uri+data.getPosterPath()).fit().into((ImageView)rootView.findViewById(R.id.details_poster_image));
		((TextView)rootView.findViewById(R.id.details_title)).setText(data.getTitle());
		((TextView)rootView.findViewById(R.id.details_release_date)).setText(data.getReleaseDate()+" \n(Release Date)");
		((TextView)rootView.findViewById(R.id.detail_vote_average)).setText(String.valueOf(data.getVoteAverage())+" \n(Ratings)");
		((TextView)rootView.findViewById(R.id.details_synopsis)).setText("Plot:\n\n"+data.getOverview());
	}
	void setAdapter(View rootView){
		mReviewAdapter=new reviews_adapter(getContext());
		((RecyclerView)rootView.findViewById(R.id.movie_review_recycler_view)).setLayoutManager(new LinearLayoutManager(getActivity()));
		((RecyclerView)rootView.findViewById(R.id.movie_review_recycler_view)).setAdapter(mReviewAdapter);

		mTrailerAdapter=new movie_trailer_adapter(getContext());
		((RecyclerView)rootView.findViewById(R.id.movie_trailer_recycler_view)).setLayoutManager(new LinearLayoutManager(getActivity()));
		((RecyclerView)rootView.findViewById(R.id.movie_trailer_recycler_view)).setAdapter(mTrailerAdapter);

	}
	void setDatabase_fav(View rootView){
		database_fav = new db_favourite_movies(getActivity());
		if(database_fav.isMovieFavourite(database_fav.getReadableDatabase(),String.valueOf(data.getId())))
		{
			((ImageView)rootView.findViewById(R.id.detail_favourite_image)).setImageResource(R.drawable.favourite);
			((TextView)rootView.findViewById(R.id.detail_favourite_text)).setText(favourite_status[1]);
		}
		else
		{
			((ImageView)rootView.findViewById(R.id.detail_favourite_image)).setImageResource(R.drawable.not_favourite);
			((TextView)rootView.findViewById(R.id.detail_favourite_text)).setText(favourite_status[0]);
		}
		((LinearLayout)rootView.findViewById(R.id.detail_favourite)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(database_fav.isMovieFavourite(database_fav.getReadableDatabase(),String.valueOf(data.getId())))
				{
					database_fav.removeMovieAsFavourite(database_fav.getWritableDatabase(),String.valueOf(data.getId()));
					((ImageView)getActivity().findViewById(R.id.detail_favourite_image)).setImageResource(R.drawable.not_favourite);
					((TextView)getActivity().findViewById(R.id.detail_favourite_text)).setText(favourite_status[0]);
				}
				else
				{
					((ImageView)getActivity().findViewById(R.id.detail_favourite_image)).setImageResource(R.drawable.favourite);
					((TextView)getActivity().findViewById(R.id.detail_favourite_text)).setText(favourite_status[1]);
					database_fav.addMovieAsFavourite(database_fav.getWritableDatabase(),String.valueOf(data.getId()));
				}
			}
		});
	}
	void fetchTrailer()
	{
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://api.themoviedb.org/3/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		movie_api_endpoint api=retrofit.create(movie_api_endpoint.class);
		retrofit2.Call call;
		call=api.getMovieTrailers(data.getId(),1);
		call.enqueue(new Callback<movie_list_data_model>() {
			@Override
			public void onResponse(retrofit2.Call call, Response response) {
				mTrailerAdapter.add(((movie_trailer_data_model) response.body()).getResults());
				mLoadedTrailer=true;
			}

			@Override
			public void onFailure(retrofit2.Call call, Throwable t) {
				Log.d("error","failed to fetch");
			}
		});
	}
	void fetchReviews()
	{
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://api.themoviedb.org/3/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		movie_api_endpoint api=retrofit.create(movie_api_endpoint.class);
		retrofit2.Call call;
			call=api.getMovieReview(data.getId(),1);
		call.enqueue(new Callback<movie_list_data_model>() {
			@Override
			public void onResponse(retrofit2.Call call, Response response) {
				mReviewAdapter.add(((movie_review_data_model) response.body()).getResults());
				mLoadedReview=true;
			}

			@Override
			public void onFailure(retrofit2.Call call, Throwable t) {
				Log.d("error","failed to fetch");
			}
		});
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if(data!=null)
		outState.putParcelable("movie-data",data);
		if(mLoadedReview)
			outState.putParcelableArrayList("movie-review",(ArrayList) mReviewAdapter.mDataSet);
		if(mLoadedTrailer)
			outState.putParcelableArrayList("movie-trailer",(ArrayList)mTrailerAdapter.mDataSet);

	}
}
