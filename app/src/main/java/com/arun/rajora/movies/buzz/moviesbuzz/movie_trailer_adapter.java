package com.arun.rajora.movies.buzz.moviesbuzz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
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
public class movie_trailer_adapter extends RecyclerView.Adapter<movie_trailer_adapter.viewHolder> {
	public List<movie_trailer_data_model.Result> mDataSet;
	private Context mContext;
	movie_trailer_adapter(Context context)
	{
		mContext=context;
		mDataSet=new ArrayList<movie_trailer_data_model.Result>();
	}
	@Override
	public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.template_movie_trailer,parent,false);
		viewHolder viewholder=new viewHolder(view);
		return viewholder;
	}

	@Override
	public void onBindViewHolder(viewHolder holder, int position) {
		((TextView)holder.itemView.findViewById(R.id.details_trailer_link)).setText(mDataSet.get(position).getName());
	}

	@Override
	public int getItemCount() {
		return mDataSet==null?0:mDataSet.size();
	}

	public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

		public viewHolder(View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			movie_trailer_data_model.Result result=mDataSet.get(getLayoutPosition());
			Intent intent;
			intent=new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:"+result.getKey()));
			if(intent.resolveActivity(mContext.getPackageManager())!=null)
			{
				mContext.startActivity(intent);
				return;
			}

			Intent nIntent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v="+result.getKey()));
			if(nIntent.resolveActivity(mContext.getPackageManager())!=null)
			{
				mContext.startActivity(nIntent);
			}
		}
	}

	void add(movie_trailer_data_model.Result result)
	{
		mDataSet.add(result);
		notifyDataSetChanged();
	}
	void clear()
	{
		if(mDataSet==null) return;
		mDataSet.clear();
		notifyDataSetChanged();
	}
	void add(List<movie_trailer_data_model.Result> results)
	{
		mDataSet.addAll(results);
		notifyDataSetChanged();
	}
}
