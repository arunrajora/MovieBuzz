package com.arun.buzz.movie.app.moviebuzz;

import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements sort_by_dialog.NoticeDialogListener {
	public String query_movie_list;
	public RecyclerView recyclerView;
	public Bundle selected;
	public RecyclerView.LayoutManager layoutManager;
	public movie_adapter adapter;
	public QueryBuilder_list query_movie;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		selected=new Bundle();
		query_movie=new QueryBuilder_list();
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		recyclerView=(RecyclerView)findViewById(R.id.movies_grid_view);
		Display display=getWindowManager().getDefaultDisplay();
		Point size=new Point();
		display.getSize(size);
		layoutManager=new GridLayoutManager(this,(int)((size.x - 1) / getResources().getDimension(R.dimen.movie_poster_width))+1);
		recyclerView.setLayoutManager(layoutManager);
		adapter=new movie_adapter();
		recyclerView.setAdapter(adapter);
		new movie_data_fetcher(adapter).execute(query_movie.query());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}
		else if(id==R.id.action_sort_by)
		{
			sort_by_dialog dialog=new sort_by_dialog();
			dialog.setArguments(selected);
			dialog.show(getFragmentManager(),"sort_by");
		}
		else if(id==R.id.refresh)
		{
			query_movie.set_page(1);
			adapter.clear();
			new movie_data_fetcher(adapter).execute(query_movie.query());
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick_sort_by_item(DialogInterface dialog, int which) {
		int choice_map[]={0,4,1,3,2,5};
		selected.putInt("selected",which);
		query_movie.sort_choice(choice_map[which]);
		query_movie.set_page(1);
		adapter.clear();
		new movie_data_fetcher(adapter).execute(query_movie.query());
		dialog.dismiss();
	}
}
