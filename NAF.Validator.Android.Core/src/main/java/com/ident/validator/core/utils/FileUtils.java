package com.ident.validator.core.utils;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;

/**
 * @author cheny
 * @version 1.0
 * @descr
 * @date 2017/7/12 10:39
 */

public class FileUtils {

    public static final String CACHE_DIR_NAME = "ident";

    /**
     * 获取缓存根目录
     *
     * @return
     */
    public static File getCacheRootDirPath() {
        String rootDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        File dir = new File(rootDir + File.separator + CACHE_DIR_NAME);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * 获取缓存目录
     *
     * @return
     */
    public static File getCacheDirPath() {
        String rootDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        File dir = new File(rootDir + File.separator + CACHE_DIR_NAME + File.separator + "cache");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * 根据下载文件路径创建文件
     *
     * @param path
     * @return
     */
    public static File createDownloadFileDir(String path) throws IOException {
        File dir = getCacheDirPath();
        String fileName = getFileName(path);
        File file = new File(dir, fileName);
        file.createNewFile();
        return file;
    }

    /**
     * 创建zip文件释放目录
     *
     * @param dirName 目录名称
     * @return
     */
    public static File createUnZipDir(String dirName) {
        File dir = getCacheDirPath();
        File file = new File(dir, dirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static void downloadFile(String path, File desFile, IDownloadListener listener) {
        //创建连接
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setReadTimeout(30 * 1000);
            conn.setReadTimeout(30 * 1000);
            conn.setConnectTimeout(10 * 1000);
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                //获取到输入流
                InputStream is = conn.getInputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
                FileOutputStream fileOutputStream = new FileOutputStream(desFile);
                //开始读取流保存到目标文件
                long totalSize = conn.getContentLength();
                int len;
                byte[] buffer = new byte[1024];
                int downloadSize = 0;
                int lastProgress = 0;
                while ((len = bufferedInputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                    //计算下载进度
                    downloadSize += len;
                    int progress = (int) ((downloadSize * 100) / totalSize);
                    if (progress > lastProgress) {
                        lastProgress = progress;
                        Log.e("TAG", "progress:" + lastProgress);
                        if (listener != null) {
                            listener.onProgress(lastProgress);
                        }
                    }
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                bufferedInputStream.close();
                is.close();
                if (listener != null) {
                    listener.onComplete(desFile);
                }
            } else {
                // TODO: 2017/7/13  失败
                if (listener != null) {
                    listener.onFailure(new DownloadException(responseCode, "下载失败"));
                }
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onFailure(new DownloadException(e.getMessage()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onFailure(new DownloadException(e.getMessage()));
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onFailure(new DownloadException(e.getMessage()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onFailure(new DownloadException(e.getMessage()));
            }
        }
    }

    public static String getFileName(String path) {
        if (!TextUtils.isEmpty(path)) {
            return path.substring(path.lastIndexOf("/") + 1, path.length());
        }
        return path;
    }

    public static String getFileNameNotType(String path) {
        String fileName = getFileName(path);
        if (!TextUtils.isEmpty(fileName) && fileName.lastIndexOf(".") != -1) {
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        }
        return fileName;
    }

    public static void getCacheDirFiles(Map<String, String> desMap) {
        File dir = getCacheDirPath();
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory() && file.listFiles().length > 0) {
                    String fileName = file.getName();
                    desMap.put(fileName, file.getAbsolutePath());
                }
            }
        }
    }

    public static boolean isEmptyDir(File file) {
        if (file != null && file.exists() && file.listFiles().length > 0) {
            return false;
        }
        return true;
    }

    public static void deleteFile(File file) {
        if (file != null && file.exists() && file.isFile()) {
            file.delete();
        }
    }

    public static interface IDownloadListener {
        void onProgress(int progress);

        void onComplete(File file);

        void onFailure(DownloadException e);
    }

    public static class DownloadException extends Exception {
        public DownloadException(String message) {
            super(message);
        }

        int type;

        public DownloadException(int type, String msg) {
            super(msg);
            this.type = type;
        }
    }
}
