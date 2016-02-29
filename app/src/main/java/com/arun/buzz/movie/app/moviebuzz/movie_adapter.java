package com.arun.buzz.movie.app.moviebuzz;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Movie;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class movie_adapter extends RecyclerView.Adapter<movie_adapter.ViewHolder> {
	private Context context;
	private static final String  poster_base_uri = "https://image.tmdb.org/t/p/w185";
	private ArrayList<movie_data> dataset;
	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
		public ImageView imgview;

		public ViewHolder(ImageView img)
		{
			super(img);
			imgview=img;
			imgview.setOnClickListener(this);
		}
		@Override
		public void onClick(View view)
		{
			Intent intent=new Intent(context.getApplicationContext(),movie_details_activity.class);
			intent.putExtra("movie-selected",dataset.get(this.getLayoutPosition()));
			context.startActivity(intent);
		}
	}
	movie_adapter()
	{
		dataset=new ArrayList<movie_data>();
	}
	movie_adapter(ArrayList<movie_data> gdataset)
	{
		dataset=gdataset;


	}
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Picasso.with(context).load(poster_base_uri+dataset.get(position).poster_path).fit().into(holder.imgview);
	}

	@Override
	public movie_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		context=parent.getContext();
		View view=LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);
		ViewHolder viewHolder = new ViewHolder((ImageView)view);

		return viewHolder;
	}

	@Override
	public int getItemCount() {
		return dataset.size();
	}
	public void add_item(movie_data x,boolean bool)
	{
		dataset.add(x);
		if(bool)
		{
			notifyDataSetChanged();
		}
	}
	public void add_collection(ArrayList<movie_data> coll,boolean bool)
	{
		dataset.addAll(coll);
		if(bool)
		{
			notifyDataSetChanged();
		}
	}
	public void add_item(movie_data x)
	{
		dataset.add(x);
	}
	public void add_collection(ArrayList<movie_data> coll)
	{
		dataset.addAll(coll);
	}
	public void clear(Boolean bool)
	{
		dataset.clear();
		if(bool)
		notifyDataSetChanged();
	}
	public void clear()
	{
		dataset.clear();
			notifyDataSetChanged();
	}

}
