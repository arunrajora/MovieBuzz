package com.arun.buzz.movie.app.moviebuzz;

import android.util.Log;

/**
 * Created by rajor on 29-Feb-16.
 */
public class QueryBuilder_list {
	private static final String base_uri="http://api.themoviedb.org/3/discover/movie";
	private static final String  secret_key= "{your_secret_key_here}";
	private static final String sort_by_values[]={"popularity.desc","release_date.desc","revenue.desc","original_title.asc","vote_average.desc","vote_count.desc"};
	private int page;
	private int sort_by_index;
	private int max_pages;
	QueryBuilder_list()
	{
		page=max_pages=1;
		sort_by_index=0;
	}
	public void sort_choice(int x)
	{
		if(x>=0 && x<=sort_by_values.length)
			sort_by_index=x;
	}
	public void set_max_pages(int x)
	{
		max_pages=x;
	}
	public void set_page(int x)
	{
		if(x>0 && x<max_pages)
			page=x;
	}
	public String query()
	{
		return base_uri+"?api_key="+secret_key+"&sort_by="+sort_by_values[sort_by_index]+"&page="+String.valueOf(page);
	}
}
