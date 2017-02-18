package net.angrycode.wehub.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.webkit.MimeTypeMap;

import net.angrycode.wehub.R;

import java.io.File;

/**
 * Created by lancelot on 2016/12/17.
 */

public class AppUtils {
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }
    public static void downLoad(Context context,String url) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //设置状态栏中显示Notification
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle(context.getResources().getString(R.string.app_name));

        request.setDescription("正在下载中...");
        //设置可用的网络类型
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        //不显示下载界面
        request.setVisibleInDownloadsUi(true);

        //创建文件的下载路径
        File folder = Environment.getDownloadCacheDirectory();

        //指定下载的路径为和上面创建的路径相同
        request.setDestinationInExternalPublicDir(folder.getAbsolutePath(), "WeCode_xyz.apk");

        //设置文件类型
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url));
        request.setMimeType(mimeString);
        //将请求加入请求队列会 downLoadManager会自动调用对应的服务执行者个请求
        downloadManager.enqueue(request);
    }
}
