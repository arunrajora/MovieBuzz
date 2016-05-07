package com.arun.rajora.movies.buzz.moviesbuzz;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arun.rajora.movies.buzz.moviesbuzz.movie_api.movie_review_data_model;
import com.arun.rajora.movies.buzz.moviesbuzz.movie_api.movie_trailer_data_model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajor on 06-May-16.
 */
public class reviews_adapter extends RecyclerView.Adapter<reviews_adapter.ViewHolder> {
	List<movie_review_data_model.Result> mDataSet;
	Context mContext;
	reviews_adapter(Context context)
	{
		mContext=context;
		mDataSet=new ArrayList<movie_review_data_model.Result>();
	}
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		Context context=parent.getContext();
		View view = LayoutInflater.from(context).inflate(R.layout.template_movie_comment,parent,false);
		ViewHolder viewHolder=new ViewHolder(view);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		int a=1;
		TextView textView=(TextView)holder.itemView.findViewById(R.id.review_text_view);
		//textView=(TextView)holder.cardView.findViewById(R.id.review_text_view);
		((TextView)holder.itemView.findViewById(R.id.review_text_view)).setText(mDataSet.get(position).getContent());
	}

	@Override
	public int getItemCount() {
		return mDataSet==null?0:mDataSet.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder{
		View cardView;
		public ViewHolder(View itemView) {
			super(itemView);
		}
	}
	public void add(movie_review_data_model.Result result)
	{
		mDataSet.add(result);
		notifyDataSetChanged();
	}
	public void clear()
	{
		if(mDataSet==null) return;
		mDataSet.clear();
		notifyDataSetChanged();
	}
	public void add(List<movie_review_data_model.Result> results)
	{
		mDataSet.addAll(results);
		notifyDataSetChanged();
	}
}
