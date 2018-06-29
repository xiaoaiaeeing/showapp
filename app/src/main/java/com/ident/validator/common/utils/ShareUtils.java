package com.ident.validator.common.utils;

import java.io.File;
import java.util.ArrayList;

import com.ident.validator.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

public class ShareUtils {
	public static void shareText(Activity act, String content) {
		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_TEXT, content);
		shareIntent.setType("text/plain");
		act.startActivity(Intent.createChooser(shareIntent, act.getString(R.string.str_result_true_share_to)));
	}

	public static void shareImage(View view, Activity act) {
		String imagePath = Environment.getExternalStorageDirectory() + File.separator + "validator.jpg";
		Uri imageUri = Uri.fromFile(new File(imagePath));

		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
		shareIntent.setType("image/*");
		act.startActivity(Intent.createChooser(shareIntent, act.getString(R.string.str_result_true_share_to)));
	}

	public static void shareMutiImage(View view, Activity act) {
		ArrayList<Uri> uriList = new ArrayList<Uri>();

		String path = Environment.getExternalStorageDirectory() + File.separator;
		uriList.add(Uri.fromFile(new File(path + "validator.jpg")));
		uriList.add(Uri.fromFile(new File(path + "validator.jpg")));
		uriList.add(Uri.fromFile(new File(path + "validator.jpg")));

		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
		shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
		shareIntent.setType("image/*");
		act.startActivity(Intent.createChooser(shareIntent, act.getString(R.string.str_result_true_share_to)));
	}

	public static void shareImageWithText(Activity act, String subject, String content, Uri uri) {
		if (uri == null) {
			return;
		}
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("image/*");
		intent.putExtra(Intent.EXTRA_STREAM, uri);
		if (subject != null && !"".equals(subject)) {
			intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		}
		if (content != null && !"".equals(content)) {
			intent.putExtra(Intent.EXTRA_TEXT, content);
		}
		act.startActivity(Intent.createChooser(intent, act.getString(R.string.str_result_true_share_to)));
	}
}
