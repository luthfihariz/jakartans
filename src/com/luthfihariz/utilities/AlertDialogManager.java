package com.luthfihariz.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.view.View;

public class AlertDialogManager {

	public void showDialog(Context context, String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(Html.fromHtml(message));
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		alertDialog.show();
	}

	public void showDialog(Context context, String title, String message,
			DialogInterface.OnClickListener positiveButtonListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(Html.fromHtml(message));
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
				positiveButtonListener);
		alertDialog.show();
	}

	public void showDialog(Context context, String title, String message,
			DialogInterface.OnClickListener positiveButtonListener,
			DialogInterface.OnClickListener negativeButtonListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(Html.fromHtml(message));
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
				positiveButtonListener);
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Nope",
				negativeButtonListener);
		alertDialog.show();
	}
	
	public void showDialog(Context context, String title, String message, View customView,
			DialogInterface.OnClickListener positiveButtonListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(Html.fromHtml(message));
		alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "UPDATE",
				positiveButtonListener);		
		alertDialog.show();
	}
}
