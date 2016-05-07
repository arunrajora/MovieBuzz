package com.arun.rajora.movies.buzz.moviesbuzz;

import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arun.rajora.movies.buzz.moviesbuzz.R;
import com.arun.rajora.movies.buzz.moviesbuzz.movie_api.movie_api_endpoint;
import com.arun.rajora.movies.buzz.moviesbuzz.movie_api.movie_list_data_model;
import com.arun.rajora.movies.buzz.moviesbuzz.movie_api.movie_trailer_data_model;
import com.arun.rajora.movies.buzz.moviesbuzz.movie_api.Result;
import com.arun.rajora.movies.buzz.moviesbuzz.movie_data.db_favourite_movies;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class movie_list_fragment extends Fragment{
	private boolean m2pane;
	private int pg_no=0;
	private int rem=0;
	private OnFragmentInteractionListener mListener;
	private TextView msortByTextView;
	private RecyclerView mRecyclerView;
	private RecyclerView.LayoutManager mLayoutManager;
	private movie_list_adapter mAdapter;
	private int mSortOptionsId;
	private List<Result> mSavedData;
	private db_favourite_movies mDatabaseFav;
	private static final String mSortByArray[]={"Favourite","Most Popular","Highest Rated"};
	private boolean mLoaded=false;
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.movie_list_menu,menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int opId=item.getItemId();
		if(opId!=mSortOptionsId)
		{
			mAdapter.clear();
			switch(opId)
			{
				case R.id.favourites:
					pg_no=1;
					mSortOptionsId=R.id.favourites;
					msortByTextView.setText(mSortByArray[0]);
					break;
				case R.id.mostPopular:
					pg_no=1;
					mSortOptionsId=R.id.mostPopular;
					msortByTextView.setText(mSortByArray[1]);
					break;
				case R.id.highestRated:
					pg_no=1;
					mSortOptionsId=R.id.highestRated;
					msortByTextView.setText(mSortByArray[2]);
					break;
				case R.id.refresh:
					pg_no=1;
					break;
			}

			mLoadMovieList();
		}
		return super.onOptionsItemSelected(item);
	}
	private void mLoadMovieList()
	{
		if(pg_no==1)
		{
			mLoaded=false;
		}
		((OnFragmentInteractionListener)getActivity()).clearDetailsView();
		final int tempMsortOpId=mSortOptionsId;
		if(mSortOptionsId==R.id.favourites)
		{
			List<String> favs=mDatabaseFav.getFavouriteMovies(mDatabaseFav.getReadableDatabase());
			rem=favs.size();
			for(int i = 0;i<favs.size();i++)
			{
				int pp=mAdapter.dataset.size();
				Retrofit retrofit = new Retrofit.Builder()
						.baseUrl("https://api.themoviedb.org/3/")
						.addConverterFactory(GsonConverterFactory.create())
						.build();
				movie_api_endpoint api_endpoint = retrofit.create(movie_api_endpoint.class);
				retrofit2.Call call=api_endpoint.getMovieDetails(favs.get(i));
				call.enqueue(new Callback() {
					@Override
					public void onResponse(Call call, Response response) {
						if(mSortOptionsId==tempMsortOpId && !mAdapter.dataset.contains((Result) response.body()))
						mAdapter.add_item((Result) response.body(),true);
						rem--;
						if(rem==0)
							mLoaded=true;
					}
					@Override
					public void onFailure(Call call, Throwable t) {
						Log.d("error","failed fetch");
					}
				});
			}
			pg_no++;

		}
		else
		{
			Retrofit retrofit = new Retrofit.Builder()
					.baseUrl("https://api.themoviedb.org/3/")
					.addConverterFactory(GsonConverterFactory.create())
					.build();
			movie_api_endpoint api=retrofit.create(movie_api_endpoint.class);
			retrofit2.Call call;
			if(mSortOptionsId==R.id.mostPopular)
			{
				call=api.getMoviesList("popular",pg_no);
			}
			else
			{
				call=api.getMoviesList("top_rated",pg_no);
			}
			pg_no++;
			call.enqueue(new Callback<movie_list_data_model>() {
				@Override
				public void onResponse(retrofit2.Call call, Response response) {
					movie_list_data_model x=(movie_list_data_model) response.body();
					if(mSortOptionsId==tempMsortOpId)
					mAdapter.add_collection(x.getResults(),true);
					mLoaded=true;
				}

				@Override
				public void onFailure(retrofit2.Call call, Throwable t) {
					Log.d("error","failed to fetch");
				}
			});
		}

	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		mLoaded=false;
		pg_no=1;
		int scrollPosition=0;
		if(savedInstanceState!=null)
		{
			m2pane=savedInstanceState.getBoolean("m2pane");
			pg_no=savedInstanceState.getInt("pg_no");
			mSortOptionsId=savedInstanceState.getInt("mSortOptionsId");
			mSavedData=savedInstanceState.getParcelableArrayList("list_adapter_data");
			scrollPosition=savedInstanceState.getInt("scrollPosition");
		}
		else if(getArguments()!=null && getArguments().containsKey("mSortOptionsId"))
		{
			m2pane=getArguments().getBoolean("m2pane");
			pg_no=getArguments().getInt("pg_no");
			mSortOptionsId=getArguments().getInt("mSortOptionsId");
			mSavedData=getArguments().getParcelableArrayList("list_adapter_data");
			scrollPosition=getArguments().getInt("scrollPosition");
		}
		else
		{
			m2pane=getArguments().getBoolean("m2pane");
			mSortOptionsId=R.id.mostPopular;
		}
		mDatabaseFav=new db_favourite_movies(getActivity());
		View rootView= inflater.inflate(R.layout.movie_list_fragment, container, false);
		msortByTextView=(TextView) rootView.findViewById(R.id.sort_by_text_view);
		switch (mSortOptionsId)
		{
			case R.id.favourites:msortByTextView.setText(mSortByArray[0]);
				break;
			case R.id.mostPopular:msortByTextView.setText(mSortByArray[1]);
				break;
			case R.id.highestRated:msortByTextView.setText(mSortByArray[2]);
				break;
		}
		setAdapter(rootView);
		Log.d("findme",String.valueOf(scrollPosition));
		if(savedInstanceState!=null || (getArguments()!=null && getArguments().containsKey("mSortOptionsId") && mSavedData!=null) )
		{
			mAdapter.dataset=mSavedData;
			mLoaded=true;
			mAdapter.notifyDataSetChanged();
			mRecyclerView.scrollToPosition(scrollPosition);
		}
		if(pg_no<2)
		{
			pg_no=1;
			mLoadMovieList();

		}
		return rootView;
	}
	void setAdapter(View view)
	{
		if(mRecyclerView==null)
		mRecyclerView=(RecyclerView) view.findViewById(R.id.movie_list_recycler_view);
		Display display=getActivity().getWindowManager().getDefaultDisplay();
		Point size=new Point();
		display.getSize(size);
		float totalSizeAvali=0;
		float oneItemSize=(int)getResources().getDimension(R.dimen.movie_poster_width);
		if(m2pane)
		{
			totalSizeAvali=size.x-1-getResources().getDimension(R.dimen.details_master_view_left);
		}
		else
		{
			totalSizeAvali=(size.x - 1);
		}
		int rowsCount =(int)( totalSizeAvali/oneItemSize);
		if(totalSizeAvali%oneItemSize>=(oneItemSize/2))
			rowsCount++;
			mLayoutManager=new GridLayoutManager(getActivity(),(int)(rowsCount));
		mRecyclerView.setLayoutManager(mLayoutManager);
		mAdapter=new movie_list_adapter(getContext(),new movie_list_adapter.OnItemClickListener(){
			@Override
			public void onItemClick(Result item, int position) {
				((OnFragmentInteractionListener)getActivity()).onItemSelected(item,position);
			}
		});
		mRecyclerView.setAdapter(mAdapter);
	}
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof OnFragmentInteractionListener) {
			mListener = (OnFragmentInteractionListener) context;
		} else {
			throw new RuntimeException(context.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	public interface OnFragmentInteractionListener {
			public void onItemSelected(Result result,int pos);
			public void clearDetailsView();
	}


	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if(mLoaded==false)
		{
			pg_no=0;
			mAdapter.dataset.clear();
		}
		outState.putInt("scrollPosition",((GridLayoutManager)mLayoutManager).findFirstVisibleItemPosition());
		outState.putBoolean("m2pane",m2pane);
		outState.putInt("pg_no",pg_no);
		outState.putParcelableArrayList("list_adapter_data",(ArrayList<Result>)mAdapter.dataset);
		outState.putInt("mSortOptionsId",mSortOptionsId);
	}
}
