package com.ident.validator.ui.launcher;

import com.ident.validator.R;
import com.ident.validator.common.base.BaseActivity;
import com.ident.validator.common.base.mvp.BasePresenter;
import com.ident.validator.common.imageloader.ZImageLoader;
import com.ident.validator.ui.home.HomeActivity;
import com.squareup.picasso.Picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class LauncherActivity extends BaseActivity {

	private ImageView mSplashImg;
	private RelativeLayout mRootView;

	@Override
	protected int getLayoutResId() {
		return R.layout.activity_launcher;
	}

	@Override
	protected BasePresenter createPresenter() {
		return null;
	}

	@Override
	protected void setStatusBar() {
		// super.setStatusBar();
		// StatusBarUtil.setTranslucent(this);
	}

	@Override
	protected void initializeViews(Bundle savedInstanceState) {
		mSplashImg = (ImageView) findViewById(R.id.splash_img);
		mRootView = (RelativeLayout) findViewById(R.id.mRootView);
		mRootView.post(new Runnable() {
			
			@Override
			public void run() {
				ZImageLoader.loadFromRes(getApplicationContext(),
						R.mipmap.img_splash,
						mRootView.getMeasuredWidth(), 
						mRootView.getMeasuredHeight(), 
						mSplashImg);
			}
		});
		
		ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 1.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(3000);
		scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// if (UserManager.getIns().isLogin()) {
				// startActivity(HomeActivity.class);
				// } else {
				// startActivity(LoginActivity.class);
				// }
				startActivity(HomeActivity.class);
				finish();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
		mSplashImg.startAnimation(scaleAnimation);

	}

	@Override
	protected void initializeData() {

	}

	public static Bitmap drawable2Bitmap(Drawable drawable) {
		if (drawable instanceof BitmapDrawable) {
			return ((BitmapDrawable) drawable).getBitmap();
		} else if (drawable instanceof NinePatchDrawable) {
			Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
					drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
			Canvas canvas = new Canvas(bitmap);
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
			drawable.draw(canvas);
			return bitmap;
		} else {
			return null;
		}
	}

	public static Drawable resizeImage(Bitmap bitmap, int w, int h) {
		Bitmap BitmapOrg = bitmap;
		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();
		int newWidth = w;
		int newHeight = h;

		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;

		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// if you want to rotate the Bitmap
		// matrix.postRotate(45);
		Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width, height, matrix, true);
		return new BitmapDrawable(resizedBitmap);
	}

	@Override
	public void onClick(View v) {

	}

}
