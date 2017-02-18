package net.angrycode.wehub.utils;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lancelot on 2016/12/3.
 */

public class FileUtils {

    /**
     * 读取
     *
     * @param fileName
     * @return
     */
    public static String readFile(String fileName) {
        String txt = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        FileInputStream in = null;
        try {
            in = new FileInputStream(fileName);
            byte[] data = new byte[1024];
            int len = -1;
            while ((len = in.read(data)) != -1) {
                out.write(data, 0, len);
                out.flush();
            }
            txt = out.toString();
        } catch (IOException e) {
            LogUtils.log(e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                out.close();
            } catch (IOException e) {
                LogUtils.log(e.getMessage());
            }

        }
        return txt;
    }

    public static void write2File(String json, String fileName) {

        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileName);
            byte[] data = json.getBytes();
            out.write(data, 0, data.length);
            out.flush();
        } catch (IOException e) {
            LogUtils.log(e.getMessage());
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                LogUtils.log(e.getMessage());
            }
        }
    }

    public static String getFileName(Context context, String lang) {
        File cacheDir = context.getCacheDir();
        String fileName = MD5Utils.MD5(lang);
        return new File(cacheDir, fileName).getAbsolutePath();
    }

    /**
     * 读取
     * @param in
     * @return
     */
    public static String read(InputStream in) {
        String retVal = null;
        if (in == null) {
            return retVal;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len = -1;
        byte[] data = new byte[1024];
        try {
            while ((len = in.read(data)) != -1) {
                out.write(data, 0, len);
            }
            out.flush();

        } catch (IOException e) {
            LogUtils.log(e.getMessage());
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                LogUtils.log(e.getMessage());
            }
        }
        return out.toString();
    }
}
