package com.ident.validator.common.imageloader;

import java.io.File;

import com.ident.validator.App;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

/**
 * @author sky
 * @version 1.0
 * @descr
 * @date 2016/9/22 11:01
 */

public final class ZImageLoader {
	private ZImageLoader() {
	}

	static {
		Picasso picasso = new Picasso.Builder(App.getInst()).downloader(new OkHttp3Downloader(App.getInst()))
				.loggingEnabled(true).build();
		Picasso.setSingletonInstance(picasso);
	}

	public static void loadFromUrl(Context context, String url, ImageView imageView) {
		load(context, url, imageView);
	}

	public static void loadFromUrl(Context context, String url, @DrawableRes int placeholderResId,
			ImageView imageView) {
		load(context, url, false, placeholderResId, imageView);
	}

	public static void loadFromFile(Context context, File file, ImageView imageView) {
		load(context, file, imageView);
	}

	public static void loadFromUri(Context context, Uri uri, ImageView imageView) {
		load(context, uri, imageView);
	}

	public static void loadFromRes(Context context, @DrawableRes int resId, ImageView imageView) {
		load(context, resId, imageView);
	}

	public static void loadCircle(Context context, String url, ImageView imageView) {
		load(context, url, true, 0, imageView);
	}

	public static void loadFromUrl(Context context, String url, int targetWidth, int targetHeight,
			ImageView imageView) {
		load(context, url, targetWidth, targetHeight, imageView);
	}

	public static void loadFromRes(Context context, @DrawableRes int resId, int targetWidth, int targetHeight,
			ImageView imageView) {
		load(context, resId, targetWidth, targetHeight, imageView);
	}

	public static void loadFromRes(Context context, @DrawableRes int resId, int placeholderResId, int targetWidth,
			int targetHeight, ImageView imageView) {
		readyLoad(context, resId, false, placeholderResId, targetWidth, targetHeight, imageView);
	}

	private static void load(Context context, Object target, ImageView imageView) {
		load(context, target, false, 0, imageView);
	}

	private static void load(Context context, Object target, boolean isCircle, int placeholderResId,
			ImageView imageView) {
		readyLoad(context, target, isCircle, placeholderResId, 0, 0, imageView);
	}

	private static void load(Context context, Object target, int targetWidth, int targetHeight, ImageView imageView) {
		readyLoad(context, target, false, 0, targetWidth, targetHeight, imageView);
	}

	private static void readyLoad(Context context, Object source, boolean isCircle, int placeholderResId,
			int targetWidth, int targetHeight, ImageView imageView) {
		RequestCreator request = null;
		if (source instanceof String) {
			request = Picasso.with(context).load((String) source);
		} else if (source instanceof Integer) {
			request = Picasso.with(context).load((Integer) source);
		} else if (source instanceof File) {
			request = Picasso.with(context).load((File) source);
		} else if (source instanceof Uri) {
			request = Picasso.with(context).load((Uri) source);
		} else {
			throw new IllegalArgumentException("Unknown source type,please check");
		}
		if (isCircle) {
			request.transform(new CropCircleTransformation());
		}
		if (targetWidth > 0 && targetHeight > 0) {
			request.resize(targetWidth, targetHeight);
		}
		if (placeholderResId > 0) {
			request.placeholder(placeholderResId);
			request.error(placeholderResId);
		}
		request.into(imageView);
	}
}
