package com.arun.buzz.movie.app.moviebuzz;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by rajor on 28-Feb-16.
 */
public class movie_data_fetcher extends AsyncTask<String,Void,String>{
	movie_adapter mo_adapter;
	public movie_data_fetcher(movie_adapter ad)
	 {
		mo_adapter=ad;
	 }
	public movie_adapter adapter;
	@Override
	protected String doInBackground(String... params)
	{
		HttpURLConnection connection=null;
		String json_string ="";
		BufferedReader bufferedReader=null;
		if(params.length!=1)
		{
			return null;
		}
		try {
			URL url=new URL(params[0]);
			connection=(HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			InputStream stream = connection.getInputStream();
			if(stream==null)
				return null;
			bufferedReader=new BufferedReader(new InputStreamReader(stream));
			StringBuffer buff=new StringBuffer();
			String line="";
			while((line=bufferedReader.readLine())!=null)
			{
				buff.append(line+"\n");
			}
			if(buff!=null || buff.toString()!="")
			{
				json_string=buff.toString();
			}

		}catch(Exception e)
		{
			Log.e("network-error",e.toString());
		}finally {
			connection.disconnect();
			try{
				bufferedReader.close();
			}catch (Exception e)
			{
				Log.e("string buffer error",e.toString());
			}
		}

		return json_string;
	}
	@Override
	protected void onPostExecute(String movie_json_string) {
		if(movie_json_string==null || movie_json_string=="")
		{
			return;
		}
		ArrayList<movie_data> raw_data=new ArrayList<movie_data>();
		Log.d("got-data",movie_json_string);
		try {
				JSONObject obj_json=new JSONObject(movie_json_string);
				JSONArray array=obj_json.getJSONArray("results");

				for(int i=0;i<array.length();i++)
				{
					JSONObject item=array.getJSONObject(i);
					raw_data.add(new movie_data());
					raw_data.get(i).poster_path=item.getString("poster_path");
					raw_data.get(i).adult=item.getString("adult");
					raw_data.get(i).overview=item.getString("overview");
					raw_data.get(i).release_date=item.getString("release_date");
					raw_data.get(i).id=item.getString("id");
					raw_data.get(i).original_title=item.getString("original_title");
					raw_data.get(i).title=item.getString("title");
					raw_data.get(i).backdrop_path=item.getString("backdrop_path");
					raw_data.get(i).popularity=item.getString("popularity");
					raw_data.get(i).vote_count=item.getString("vote_count");
					raw_data.get(i).video=item.getString("video");
					raw_data.get(i).vote_average=item.getString("vote_average");
					mo_adapter.add_item(raw_data.get(i), true);
				}

		}catch (Exception e)
		{

			Log.e("error ",e.toString());
		}
	}
}
