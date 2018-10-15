package com.ident.validator.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * @author sky
 * @version 1.0
 * @descr
 * @date 2016/9/22 10:00
 */

public class IOUtils {
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    public static byte[] compress(byte[] data) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(data.length / 4);
        DeflaterOutputStream zipOut = new DeflaterOutputStream(byteStream);
        try {
            zipOut.write(data);
            zipOut.finish();
            zipOut.close();
        } catch (IOException e) {
            return new byte[0];
        } finally {
            close(zipOut);
        }
        return byteStream.toByteArray();
    }

    public static byte[] uncompress(byte[] data) {
        InflaterInputStream in = new InflaterInputStream(new ByteArrayInputStream(data));
        ByteArrayOutputStream out = new ByteArrayOutputStream(data.length * 4);
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        } catch (IOException e) {
            return new byte[0];
        } finally {
            close(in);
        }
        return out.toByteArray();
    }
}
