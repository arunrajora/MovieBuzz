package com.arun.rajora.movies.buzz.moviesbuzz;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.arun.rajora.movies.buzz.moviesbuzz.movie_api.Result;

public class MainActivity extends AppCompatActivity implements  movie_list_fragment.OnFragmentInteractionListener{

	boolean mTwoPane;
	private static final String fTag="mDetails";
	private static final String flistTag="mList";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		FrameLayout frameLayout =(FrameLayout) findViewById(R.id.movie_detail_fragment);
		if(frameLayout==null)
		{
			mTwoPane=false;
		}
		else
		{
			mTwoPane=true;
			if(savedInstanceState==null)
			{
				getSupportFragmentManager().beginTransaction().replace(R.id.movie_detail_fragment,new movie_detail_fragment(),fTag).commit();
			}
		}
		if(savedInstanceState==null)
		{
			Bundle bundle=new Bundle();
			bundle.putBoolean("m2pane",mTwoPane);
			movie_list_fragment fragment=new movie_list_fragment();
			fragment.setArguments(bundle);
			getSupportFragmentManager().beginTransaction().replace(R.id.movies_list_fragment,fragment,flistTag).commit();
		}


	}

	@Override
	public void onItemSelected(Result result, int pos) {
		if(mTwoPane)
		{
			Bundle bundle=new Bundle();
			bundle.putParcelable("movie-data",result);
			movie_detail_fragment fragment=new movie_detail_fragment();
			fragment.setArguments(bundle);
			getSupportFragmentManager().beginTransaction().replace(R.id.movie_detail_fragment,fragment,fTag).commit();
		}
		else
		{
			Intent intent=new Intent(this,movie_detail_activity.class);
			intent.putExtra("movie-item",result);
			startActivity(intent);
		}
	}

	@Override
	public void clearDetailsView() {
		if(mTwoPane)
		{
			getSupportFragmentManager().beginTransaction().replace(R.id.movie_detail_fragment,new movie_detail_fragment(),fTag).commit();
		}
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
}
