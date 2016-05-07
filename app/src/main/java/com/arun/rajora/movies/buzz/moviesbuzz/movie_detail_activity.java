package com.arun.rajora.movies.buzz.moviesbuzz;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.arun.rajora.movies.buzz.moviesbuzz.movie_api.Result;
import com.squareup.picasso.Picasso;

public class movie_detail_activity extends AppCompatActivity{
	private static final String  poster_base_uri = "https://image.tmdb.org/t/p/w185";
	public static final String ftag="detailFragment";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_detail);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapse_toolbar);
			Result result=getIntent().getExtras().getParcelable("movie-item");
			Bundle bundle;
			if(savedInstanceState!=null)
				bundle=savedInstanceState;
			else
				bundle=new Bundle();
			collapsingToolbarLayout.setTitle(result.getTitle());
			bundle.putParcelable("movie-data",result);
			movie_detail_fragment fragment=new movie_detail_fragment();
			fragment.setArguments(bundle);
			Picasso.with(this.getApplicationContext()).load(poster_base_uri + result.getBackdropPath()).fit().into((ImageView) findViewById(R.id.backdrop_poster));
		if(savedInstanceState==null)
		{
			getSupportFragmentManager().beginTransaction().replace(R.id.fragment_movie_detail,fragment,ftag).commit();

		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
}
