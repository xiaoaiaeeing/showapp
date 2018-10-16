package com.ident.validator.core.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import com.ident.ValidateListener;
import com.ident.ValidateResult;
import com.ident.Validator;
import com.ident.validator.core.R;
import com.ident.validator.core.fragment.ResultFragment;
import com.ident.validator.core.model.TagInfo;
import com.ident.validator.core.model.TagMessage;
import com.ident.validator.core.utils.NAFVerifyHelper;
import com.ident.validator.core.views.ProgressDialog;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * @author cheny
 * @version 1.0
 * @descr
 * @date 2017/7/10 11:05
 */

public class ValidatorPresenter implements ValidatorContract.Presenter, ValidateListener {
    static {
        System.loadLibrary("IdentValidator");
    }

    private final ValidatorContract.View mView;
    //    private NAFNfc mNafNfc;
    private Activity mAct;
    private boolean isFirst;
    private ProgressDialog mProgressDialog;
    private TagInfo tagInfo;

    private String num = " ";
    private String[] zouyun_num = new String[29];//存储状态位的数组
    private int zouyun_i = 0;


    public ValidatorPresenter(ValidatorContract.View view) {
        this.mView = view;
        mAct = (Activity) mView;
//        mNafNfc.init(mAct);
        Validator.getInstance().init(mAct.getApplicationContext());
        isFirst = true;
    }

    @Override
    public void onStart() {
        if (isFirst){
            analysisTag();
            isFirst = false;
        }

    }

    @Override
    public void onResume() {
//        mNafNfc.enableForegroundDispatch();
    }

    @Override
    public void onPause() {
//        mNafNfc.disableForegroundDispatch();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onNewIntent(Intent intent){
        analysisTag();
    }


    @Override
    public void onNewIntent(Intent intent, String num1) {
        num = num1;
        analysisTag();

    }

    @Override
    public void onDestroy() {
//        mNafNfc = null;
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    @Override
    public void init() {

    }


    private void analysisTag() {
        Intent intent = mAct.getIntent();
        Tag tag = NAFVerifyHelper.getNfcData(intent);
        if (tag != null) {
            //清楚界面
            mView.restUI();

//            tagInfo = parseProductTag(intent);
//            if (tagInfo!=null && !TextUtils.isEmpty(tagInfo.pid)) {
//                //显示真假结果，加载图片
//                boolean result = showProduct(tagInfo.pid);
//                if (result) {
//                    startVerify(intent);
//                } else {
//                    mView.showAlert("未找到对应资源,请更新后再使用");
//                }
//            } else {
//                mView.showAlert("空白标签！");
//            }

            MifareUltralight mifare = MifareUltralight.get(tag);
            String result="";
            String temp="";
            try {
                mifare.connect();
                byte[] payload = mifare.readPages(0);
                for(int j =0;j<8;j++) {
                    temp = Integer.toHexString(payload[j] & 0xFF);
                    if (temp.length() == 1) {
                        temp = "0" + temp;
                    }
                    result += temp;
                }
               System.out.println(result);

//                temp = "";
//                int move = 0x80;
//                byte[] transceive = mifare.transceive(new byte[]{0x30, -128});
//                for(int i=0;i<5;i++){
//                    if((transceive[0] & move) == 0) temp+="0";
//                    else temp+="1";
//                    move = move >> 1;
//                }
//                System.out.println(temp);



                ResultFragment instance = ResultFragment.newInstance();
                mView.switchFragment(instance);

                //有状态位标签的uid
                String[] num_array = {"04867f75dae84c81", "046de00172df4c80", "046a04e23a794c84",
                "043fd2611ae74c80", "046cbc5cea734c80", "04e7c5aedae84c80", "0445579ecae54c80", "0430902c6adf4c81",
                "042a5bfd7ae04980", "041a56c07ae04980", "041c58c87ae04980", "04355ce57ae04980", "042d5bfa7ae04980",
                "04235bf47ae04980", "041b5bcc7ae04980", "041c5ccc7ae04980", "04245cf47ae04980", "042e5cfe7ae04980",
                "04385ce87ae04980", "04435c937ae04980", "044f5c9f7ae04980", "04595c897ae04980", "04645cb47ae04980",
                "04715ca17ae04980", "047b5cab7ae04980", "04835c537ae04980", "048b5c5b7ae04980", "04945c447ae04980",
                        "04a05c707ae04980"};

                //分配状态位
                String[] state_array = {"00000", "00001", "00010", "00011", "00100", "00101", "00110",
                "11111", "11101", "11110", "11011", "11100", "11010", "10011", "10100", "10110", "10101", "10111", "11000",
                 "11001", "00111", "01000", "01001", "01010", "01011", "01100", "01101", "01110", "01111", "10000", "10001",
                "10010"}
                        ;
                boolean result_num = Arrays.asList(num_array).contains(result);
                System.out.println("a:" + result_num);
                if(result_num){
//                    int b = 0;
//                    b = (int) ((Math.random()+0.1) * 29);
//                    num = binary2decimal(b, 5);

                    switch(result){
                        case "04867f75dae84c81":
                            setMove("00000", instance);
                            break;
                        case "046de00172df4c80":
                            setMove("00001", instance);
                            break;
                        case  "046a04e23a794c84":
                            setMove("00010", instance);
                            break;
                        case  "043fd2611ae74c80":
                            setMove("00011", instance);
                            break;
                        case  "046cbc5cea734c80":
                            setMove("00100", instance);
                            break;
                        case  "04e7c5aedae84c80":
                            setMove("00101", instance);
                            break;
                        case  "0445579ecae54c80":
                            setMove("00110", instance);
                            break;
                        case  "0430902c6adf4c81":
                            setMove("00111", instance);
                            break;
                        case  "042a5bfd7ae04980":
                            setMove("01000", instance);
                            break;
                        case  "041a56c07ae04980":
                            setMove("01001", instance);
                            break;
                        case  "041c58c87ae04980":
                            setMove("01010", instance);
                            break;
                        case  "04355ce57ae04980":
                            setMove("01011", instance);
                            break;
                        case  "042d5bfa7ae04980":
                            setMove("01100", instance);
                            break;
                        case  "04235bf47ae04980":
                            setMove("01101", instance);
                            break;
                        case  "041b5bcc7ae04980":
                            setMove("01110", instance);
                            break;
                        case  "041c5ccc7ae04980":
                            setMove("01111", instance);
                            break;
                        case  "04245cf47ae04980":
                            setMove("10000", instance);
                            break;
                        case  "042e5cfe7ae04980":
                            setMove("10001", instance);
                            break;
                        case  "04385ce87ae04980":
                            setMove("10010", instance);
                            break;
                        case  "04435c937ae04980":
                            setMove("10011", instance);
                            break;
                        case  "044f5c9f7ae04980":
                            setMove("10100", instance);
                            break;
                        case  "04595c897ae04980":
                            setMove("10101", instance);
                            break;
                        case  "04645cb47ae04980":
                            setMove("10110", instance);
                            break;
                        case  "04715ca17ae04980":
                            setMove("10111", instance);
                            break;
                        case  "047b5cab7ae04980":
                            setMove("11000", instance);
                            break;
                        case  "04835c537ae04980":
                            setMove("11001", instance);
                            break;
                        case  "048b5c5b7ae04980":
                            setMove("11010", instance);
                            break;
                        case  "04945c447ae04980":
                            setMove("11011", instance);
                            break;
                        case  "04a05c707ae04980":
                            setMove("11100", instance);
                            break;
                        default:
                            setMove("00000", instance);

                    }


                    //setMove(state_array[zouyun_i], instance);
                    List<TagMessage> tagMessage = DataSupport.where("uid = ?",result).find(TagMessage.class);
                    if(tagMessage.size()>0){
                            instance.setResultImg(R.mipmap.p_010001000100000002_success);
                            instance.setResultProduct(true);
                            instance.setTvInfo("");
                            System.out.print(num);
                    }else{
                        TagMessage tagMes = new TagMessage(result);
                        tagMes.save();
                        instance.setResultImg(R.mipmap.p_010001000100000002_success);
                        instance.setResultProduct(true);
                        instance.setTvInfo("");
                        System.out.print(num);
                    }
                }else{
                    setMove("aaaaa", instance);
                    List<TagMessage> tagMessage = DataSupport.where("uid = ?",result).find(TagMessage.class);
                        instance.setResultImg(R.mipmap.p_010001000100000002_failure);
                        instance.setResultProduct(false);
                        instance.setTvInfo("这不是RAS标签");
                        System.out.print(num);
                }
                if(zouyun_i<32){
                    zouyun_i++;
                }else {

                }








//                setMove(num,instance);
//                List<TagMessage> tagMessage = DataSupport.where("uid = ?",result).find(TagMessage.class);
//
//                if(num == "aaaaa"){
//                    if(tagMessage.size()>0){
//                        instance.setResultImg(R.mipmap.p_010001000100000002_failure);
//                        instance.setResultProduct(false);
//                        instance.setTvInfo("商品已开启");
//                        System.out.print(num);
 //                   }else{
 //                       TagMessage tagMes = new TagMessage(result);
 //                       tagMes.save();
//                        instance.setResultImg(R.mipmap.p_010001000100000002_success);
//                        instance.setResultProduct(true);
//                        instance.setTvInfo("商品验证为真");
//                        System.out.print(num);
//                    }
//                }else {
//                    if(tagMessage.size()>0){
                        //
                        //if(tagMessage.get(0).getStatenum().equals(temp)){
                        //instance.setResultImg(R.mipmap.p_010001000100000002_success);
                        //instance.setResultProduct(true);
                        //instance.setTvInfo("商品已被开启");
                        //}else {
                        //instance.setResultImg(R.mipmap.p_010001000100000002_failure);
                        //instance.setResultProduct(false);
                        //instance.setTvInfo("商品状态位发生变化");
                        //}
//                        if(num == "00000"){
//                            instance.setResultImg(R.mipmap.p_010001000100000002_success);
//                            instance.setResultProduct(true);
//                            instance.setTvInfo("商品已被开启");
//                            System.out.print(num);
//                        }else{
//                            instance.setResultImg(R.mipmap.p_010001000100000002_failure);
//                            instance.setResultProduct(false);
//                            instance.setTvInfo("商品状态位发生变化");
//                            System.out.print(num);
//                        }
//                    }else {
                        //                                        TagMessage tagMes = new TagMessage(result,temp);
                        //                                        tagMes.save();
                        //                                        instance.setResultImg(R.mipmap.p_010001000100000002_success);
                        //                                        instance.setResultProduct(true);
                        //                                       instance.setTvInfo("商品验证为真");
//                        TagMessage tagMes = new TagMessage(result);
//                        tagMes.save();
//                        instance.setResultImg(R.mipmap.p_010001000100000002_success);
//                        instance.setResultProduct(true);
//                        instance.setTvInfo("商品验证为真");
//                        System.out.print(num);

//                    }
//                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setMove(String move,ResultFragment instance){
        for(int i=0;i<5;i++){
            Log.d(TAG,move);
            instance.setImageColor(move.charAt(i),i);
        }
    }

    //开始验证
    private void startVerify(Intent intent) {
        mView.setTipViews();
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        try {
            Validator.getInstance().validate(tag, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //显示对应产品信息
    private void showProductFromDisk(String path) {
        String pid = path.substring(path.lastIndexOf("/") + 1, path.length());
        Resources res = mAct.getResources();
        String product_bg = res.getString(R.string.disk_product_bg, path, pid);
        String product_logo = res.getString(R.string.disk_product_logo, path, pid);
        String product_seal = res.getString(R.string.disk_product_seal, path, pid);
        String product_failure = res.getString(R.string.disk_product_failure, path, pid);
        String product_success = res.getString(R.string.disk_product_success, path, pid);
        String product_img = res.getString(R.string.disk_product_img, path, pid);
        Log.e("TAG", "product_bg:" + product_bg);
    }

    //显示对应产品信息
    private boolean showProduct(String pid) {
        if (TextUtils.equals(pid, "010001000200040001")) {
            pid = "010001000100000031";
        }
        if (TextUtils.equals(pid, "0700ff00ff00ff0001")) {
            pid = "070001000100ff0001";
        }
        Resources res = mAct.getResources();
        int product_bg = getDrawableId(R.string.product_bg, pid, res);
        int product_logo = getDrawableId(R.string.product_logo, pid, res);
        int product_seal = getDrawableId(R.string.product_seal, pid, res);
        int product_failure = getDrawableId(R.string.product_failure, pid, res);
        int product_success = getDrawableId(R.string.product_success, pid, res);
        int product_img = getDrawableId(R.string.product_img, pid, res);
//        if (product_bg == 0 || product_logo == 0 || product_seal == 0 ||
//                product_failure == 0 || product_success == 0 || product_img == 0) {
//            return false;
//        }
        if (product_logo == 0 || product_failure == 0 || product_success == 0) {
            return false;
        }
        // TODO: 2017/7/20  待替换为070001000100ff0001//云艺术pid d1560001391b0700ff00ff00ff000100
        if (TextUtils.equals(pid, "070001000100ff0001") || TextUtils.equals(pid, "0700ff00ff00ff0001")) {
            mView.switchFragment(CloudArtFragment.newInstance());
        } else {
            mView.switchFragment(ValidatorFragment.newInstance());
        }
        mView.showProduct(product_bg, product_logo, product_seal, product_failure, product_success, product_img);
        return true;
    }

    private int getDrawableId(int sId, String pid, Resources resources) {
        return resources.getIdentifier(mAct.getString(sId, pid), "mipmap", mAct.getPackageName());
    }

    //解析标签信息
    private TagInfo parseProductTag(Intent intent) {
        TagInfo tagInfo = new TagInfo();
        if (intent != null) {
            Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (parcelables != null) {
                String[] schemes = new String[]{"d156000139", "d156000189"};
                for (String scheme : schemes) {
                    tagInfo = parseTaInfo(parcelables, scheme.getBytes());//tagInfo可以为null
                    if (tagInfo != null)
                        break;
                }
            }
            //tagInfo.tid = NAFVerifyHelper.getTagTid(NAFVerifyHelper.getNfcData(intent));
        }
        //Log.e("ident", "tagInfo:" + tagInfo.toString());
        return tagInfo;
    }

    private TagInfo parseTaInfo(Parcelable[] parcelables, byte[] scheme) {
        TagInfo tagInfo = null;
//        inner:
        for (int i = 0; i < parcelables.length; i++) {
            NdefMessage msg = (NdefMessage) parcelables[i];
            NdefRecord[] records = msg.getRecords();
            for (int j = 0; j < records.length; j++) {
                byte[] payload = records[j].getPayload();
                Log.d(TAG, "parseTaInfo: "+bytesToHexString(scheme));
                Log.d(TAG, "parseTaInfo: "+bytesToHexString(payload));
                if (payload != null && payload.length > scheme.length) {
                    boolean found = true;
                    for (int k = 0; k < scheme.length; ++k) {
                        if (scheme[k] != payload[k + 1]) {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        int offset = scheme.length + 1 + 3 + 1;
//                        int type = payload[offset];
//                        int sub1 = (payload[offset + 1] << 8) | payload[offset + 2];
//                        int sub2 = (payload[offset + 3] << 8) | payload[offset + 4];
                        int vendor = ((payload[offset + 5] << 8) & 0XFF) | (payload[offset + 6] & 0XFF);
//                        int brand = (payload[offset + 7] << 8) | payload[offset + 8];
                        tagInfo = new TagInfo();
                        tagInfo.vendor = vendor;

                        byte[] desAry = new byte[9];
                        System.arraycopy(payload, offset, desAry, 0, 9);
                        tagInfo.pid = NAFVerifyHelper.bytesToHexString(desAry);

                        byte[] aid = new byte[24];
                        System.arraycopy(payload, j + 1, aid, 0, 24);
                        tagInfo.aid = NAFVerifyHelper.bytesToHexString(NAFVerifyHelper.parse(aid));
                    }
                }
            }
        }
        return tagInfo;
    }

    @Override
    public void onError(Validator.ErrorCode errorCode) {
        mView.showAlert(errorCode.toString());
        mView.restUI();
        Log.e("ident", "onError:" + errorCode);
    }

    @Override
    public void onResult(ValidateResult validateResult) {
        Log.e("ident", "onResult url:" + validateResult.url() + "--code:" + validateResult.code() + "--isValid:" + validateResult.isValid() + "--aid:" + NAFVerifyHelper.bytesToHexString(validateResult.aid()));
        if (validateResult != null) {
            if (validateResult.isValid()) {
                //d1560001391b0700ff00ff00ff000100
                //d1560001391b070001000100ff000100
                if (TextUtils.equals("d1560001391b01000100020004000100", NAFVerifyHelper.bytesToHexString(validateResult.aid())) ||
                        TextUtils.equals("d1560001391b070001000100ff000100", NAFVerifyHelper.bytesToHexString(validateResult.aid())) ||
                        TextUtils.equals("d1560001391b0700ff00ff00ff000100", NAFVerifyHelper.bytesToHexString(validateResult.aid()))) {
                    mView.jump2Result(validateResult.url());
                } else {
                    mView.showSuccess(tagInfo.isTest());
                }
            } else {
                mView.showFailure(tagInfo.isTest());
            }
        }
    }


    @Override
    public void onProgress(int i) {
        Log.e("ident", "onProgress:" + i);
    }

    private TagRequest mTagRequest;

    @Override
    public void onMoreTagRequest(TagRequest tagRequest) {
        mTagRequest = tagRequest;
        Log.e("ident", "onMoreTagRequest:" + tagRequest.toString());
    }

    @Override
    public void onLogMessage(String s) {
        Log.e("ident", "onLogMessage:" + s);
    }

    @Override
    public void showToolbarRightMenu() {
        mView.showToolbarRightMenu(getToolbarRightJumpUrl(), TextUtils.isEmpty(getToolbarRightJumpUrl()));
    }

    @Override
    public String getToolbarRightJumpUrl() {
        if (tagInfo != null) {
            return "http://pdp.ident.cn/ProductPortal/Product/WebSite?aid=" + tagInfo.aid.toUpperCase() + "?tid=" + tagInfo.tid.toUpperCase();
        }
        return null;
    }

    public String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if(src != null && src.length > 0) {
            for(int i = 0; i < src.length; ++i) {
                int v = src[i] & 255;
                String hv = Integer.toHexString(v);
                if(hv.length() < 2) {
                    builder.append(0);
                }

                builder.append(hv);
            }

            return builder.toString();
        } else {
            return null;
        }
    }





    /*
    zouyun 产生随机状态位
     */
    public static String binary2decimal(int decNum , int digit) {
        String binStr = "";
        for(int i= digit-1;i>=0;i--) {
            binStr +=(decNum>>i)&1;
        }
        return binStr;
    }

}
