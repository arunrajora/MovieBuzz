package com.arun.buzz.movie.app.moviebuzz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class sort_by_dialog extends DialogFragment {
	int selected=0;
	public sort_by_dialog()
	{
		selected=0;
	}

	@Override
	public void setArguments(Bundle args) {
		super.setArguments(args);
		if(args.get("selected")!=null)
		selected=args.getInt("selected");
	}

	public interface NoticeDialogListener {
		public void onClick_sort_by_item(DialogInterface dialog, int which);
	}
	NoticeDialogListener listener;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.dialog_sort_by);
		builder.setSingleChoiceItems(R.array.sort_by_options,selected, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				listener.onClick_sort_by_item(dialog,which);
			}
		});
		return builder.create();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
				listener=(NoticeDialogListener)activity;
		}catch (ClassCastException e)
		{
			throw new ClassCastException(activity.toString()+" must implement NoticeDialogListener");
		}
	}
}
