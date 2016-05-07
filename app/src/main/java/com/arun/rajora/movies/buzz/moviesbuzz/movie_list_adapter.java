package com.arun.rajora.movies.buzz.moviesbuzz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.arun.rajora.movies.buzz.moviesbuzz.movie_api.Result;
import com.arun.rajora.movies.buzz.moviesbuzz.movie_api.movie_list_data_model;
import com.facebook.stetho.inspector.elements.android.ActivityTracker;
import com.facebook.stetho.inspector.protocol.module.DOMStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajor on 05-May-16.
 */

public class movie_list_adapter extends RecyclerView.Adapter<movie_list_adapter.ViewHolder> {
	private static final String  poster_base_uri = "https://image.tmdb.org/t/p/w185";
	public List<Result> dataset;
	private Context mContext;
	public OnItemClickListener mItemClickListener;
	movie_list_adapter(Context context,OnItemClickListener listener)
	{
		mItemClickListener=listener;
		mContext=context;
		dataset=new ArrayList<Result>();
	}
	public class ViewHolder extends RecyclerView.ViewHolder{
		public ImageView imgview;

		public ViewHolder(ImageView img)
		{
			super(img);
			imgview=img;
		}

		public void bind(final Result result,final OnItemClickListener mItemClickListener) {
			itemView.setOnClickListener(
					new View.OnClickListener(){
						@Override
						public void onClick(View v) {
							mItemClickListener.onItemClick(result,getAdapterPosition());
						}
					}
			);
		}
	}
	public interface OnItemClickListener{
		public void onItemClick(Result view,int position);
	}
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Picasso.with(mContext).load(poster_base_uri+dataset.get(position).getPosterPath()).fit().into(holder.imgview);
		holder.bind(dataset.get(position),mItemClickListener);
	}

	@Override
	public movie_list_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		Context context=parent.getContext();
		View view=LayoutInflater.from(context).inflate(R.layout.template_data_list_item,parent,false);
		ViewHolder viewHolder = new ViewHolder((ImageView)view);

		return viewHolder;
	}

	@Override
	public int getItemCount() {
		if(dataset==null)
			return 0;
		else
			return dataset.size();
	}
	public void add_item(Result x,boolean bool)
	{
		for(int i=0;i<dataset.size();i++)
		{
			if(dataset.get(i).getId()==x.getId())
				return;;
		}
		dataset.add(x);
		if(bool)
		{
			notifyDataSetChanged();
		}
	}
	public void add_collection(List<Result> coll,boolean bool)
	{
		dataset.addAll(coll);
		if(bool)
		{
			notifyDataSetChanged();
		}
	}
	public void add_item(Result x)
	{
		if(!dataset.contains(x))
		dataset.add(x);
	}
	public void add_collection(List<Result> coll)
	{
		dataset.addAll(coll);
	}
	public void clear(Boolean bool)
	{
		if(dataset==null)
			return;
		dataset.clear();
		if(bool)
			notifyDataSetChanged();
	}
	public void clear()
	{
		if(dataset==null)
			return;
		dataset.clear();
		notifyDataSetChanged();
	}

}