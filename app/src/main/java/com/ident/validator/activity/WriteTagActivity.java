package com.ident.validator.activity;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ident.validator.R;

import java.nio.charset.Charset;

public class WriteTagActivity extends BaseNfcActivity {
    String TAG = "WriteTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_tag);

    }
    @Override
    public void onNewIntent(Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        String[] techList = tag.getTechList();
        boolean haveMifareUltralight = false;
        for (String tech : techList) {
            if (tech.indexOf("MifareUltralight") >= 0) {
                haveMifareUltralight = true;
                break;
            }
        }
        if (!haveMifareUltralight) {
            Toast.makeText(this, "不支持MifareUltralight数据格式", Toast.LENGTH_SHORT).show();
            return;
        }
        writeTag(tag);
    }
    public void writeTag(Tag tag) {
//        NfcA nfcA = NfcA.get(tag);
//        nfcA.transceive(new byte[]{-1,-1,-1,-1});
        MifareUltralight ultralight = MifareUltralight.get(tag);
        try {
//            Boolean ok = Validator.getInstance().authenticate(new byte[]{-1,-1,-1,-1});
            ultralight.connect();
//            byte[] transceive = ultralight.transceive(new byte[]{-86, -71, 9, 0, 0, 0, 4, 32, -1, -1, -1, -1, 36});
//            String re="";
//            for(int i=0;i<transceive.length;i++){
//                String temp = Integer.toHexString(transceive[i] & 0xFF);
//                if (temp.length() == 1) {
//                    temp = "0" + temp;
//                }
//                re+=temp;
//            }
//            Log.d(TAG, "writeTag: "+re);
//            ok = Validator.getInstance().authenticate(new byte[]{-1,-1,-1,-1});
            //写入八个汉字，从第五页开始写，中文需要转换成GB2312格式

            //密码认证
            byte[] transceive = ultralight.transceive(new byte[]{0x1b, -1, -1, -1, -1});
            String re="";
            for(int i=0;i<transceive.length;i++){
                String temp = Integer.toHexString(transceive[i] & 0xFF);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                re+=temp;
            }
            Log.d(TAG, "writeTag: "+re);

            ultralight.writePage(4, "北京".getBytes(Charset.forName("GB2312")));
            Log.d(TAG, "writeTag: 4 success.");
            ultralight.writePage(5, "上海".getBytes(Charset.forName("GB2312")));
            Log.d(TAG, "writeTag: 5 success.");
            ultralight.writePage(6, "广州".getBytes(Charset.forName("GB2312")));
            Log.d(TAG, "writeTag:6 success.");
            //ultralight.writePage(35, "天津".getBytes(Charset.forName("GB2312")));
            ultralight.writePage(33,new byte[]{4,0,0,32});
            Log.d(TAG, "writeTag: 35 success.");
            Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "写入失败", Toast.LENGTH_SHORT).show();
        } finally {
            try {
                ultralight.close();
            } catch (Exception e) {
            }
        }
    }
}
