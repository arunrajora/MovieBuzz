package com.arun.buzz.movie.app.moviebuzz;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class movie_details_activity extends AppCompatActivity {

	private movie_data movie_selected;
	private static final String  poster_base_uri = "https://image.tmdb.org/t/p/w185";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_details_activity);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapse_toolbar);
		Bundle got_bundle=getIntent().getExtras();
		if(got_bundle!=null && got_bundle.getParcelable("movie-selected")!=null)
		{
			movie_selected=got_bundle.getParcelable("movie-selected");
			collapsingToolbarLayout.setTitle(movie_selected.title);
			((TextView)findViewById(R.id.description_movie)).setText(movie_selected.overview);
			((TextView)findViewById(R.id.release_date)).setText(movie_selected.release_date);
			((TextView)findViewById(R.id.vote_average)).setText(movie_selected.vote_average+"/10\n("+movie_selected.vote_count+")");
			Picasso.with(this.getApplicationContext()).load(poster_base_uri + movie_selected.backdrop_path).fit().into((ImageView) findViewById(R.id.backdrop_poster));
			Picasso.with(this.getApplicationContext()).load(poster_base_uri + movie_selected.poster_path).fit().into((ImageView) findViewById(R.id.details_poster));

		}
	}

}
