package com.ident.validator.core.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ident.validator.core.R;
import com.ident.validator.core.base.BaseFragment;

/**
 * Created by pengllrn on 2018/7/30.
 */

public class ResultFragment extends BaseFragment{





    public static ResultFragment newInstance(){
        Bundle args = new Bundle();
        ResultFragment fragment = new ResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getRootViewLayoutId() {
        return R.layout.fragment_result;
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {

    }

    @Override
    protected void setupData() {

    }

    @Override
    public void onClick(View v) {

    }

    public void setTvInfo(String s){
        TextView tv_info = (TextView) getHostActivity().findViewById(R.id.tv_info);
        tv_info.setText(s);
    }

    public void setImageColor(char c,int index){
        int id=0;
        switch (index){
            case 0:
                id = R.id.iv_0;
                break;
            case 1:
                id = R.id.iv_1;
                break;
            case 2:
                id = R.id.iv_2;
                break;
            case 3:
                id = R.id.iv_3;
                break;
            case 4:
                id = R.id.iv_4;
                break;
        }
        Log.d("ContentValues",""+getHostActivity());
        ImageView viewById = (ImageView) getHostActivity().findViewById(id);
        Log.d("ContentValues",""+viewById);
        if(c == '1'){
            viewById.setBackgroundColor(Color.parseColor("#FF0000"));
        }else if(c == '0'){
            viewById.setBackgroundColor(Color.parseColor("#44BB44"));
        } else if (c == 'a') {
            viewById.setBackgroundColor(Color.parseColor("#AAAAAA"));
        }

    }

    public void setResultImg(int id){
        ImageView imgView = (ImageView) getHostActivity().findViewById(R.id.result_img);
        imgView.setImageResource(id);
    }
    public void setResultProduct(boolean b){
        ImageView imgView = (ImageView) getHostActivity().findViewById(R.id.result_product_iv);
        if (b){
            imgView.setVisibility(View.VISIBLE);
        }else {
            imgView.setVisibility(View.GONE);
        }
    }






}
