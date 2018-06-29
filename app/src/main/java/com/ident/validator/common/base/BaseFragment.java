package com.ident.validator.common.base;

import java.lang.reflect.Field;

import com.ident.validator.common.utils.T;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

	private Context mHostContext;
	protected LayoutInflater mInflater;
	protected View mRootView;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mHostContext = context;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		mInflater = inflater;
		mRootView = inflater.inflate(getRootViewLayoutId(), container, false);
		return mRootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initializeViews();
		initializeData();
	}

	protected abstract int getRootViewLayoutId();

	protected abstract void initializeViews();

	protected abstract void initializeData();

	public abstract void onClick(View v);

	public Activity getHostActivity() {
		return (Activity) mHostContext;
	}

	protected int getResColor(int color) {
		return this.getResources().getColor(color);
	}

	public View findViewById(int id) {
		if (mRootView != null) {
			return mRootView.findViewById(id);
		}
		return null;
	}

	public void showToast(String msg) {
		T.showShort(getContext().getApplicationContext(), msg);
	}

	public void showLoadingDialog() {

	}

	public void closeLoadingDialog() {

	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		// getActivity().overridePendingTransition(R.anim.push_right_in,
		// R.anim.push_left_out);
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		super.startActivityForResult(intent, requestCode);
		// getActivity().overridePendingTransition(R.anim.push_right_in,
		// R.anim.push_left_out);
	}

	protected void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(getActivity(), cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	protected void startActivity(String action) {
		startActivity(action, null);
	}

	protected void startActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDetach() {
		super.onDetach();
		try {
			Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);

		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
