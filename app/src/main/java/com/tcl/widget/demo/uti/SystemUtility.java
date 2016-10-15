package com.tcl.widget.demo.uti;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.tcl.mig.commonframework.base.BaseApplication;

import java.io.File;
import java.util.List;

public class SystemUtility {

	private static int screenWidth;

	private static int screenHeight;

	private static float density;

	public enum NetWorkType {
		none, mobile, wifi
	}

	private static void setScreenInfo() {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) BaseApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
		density = dm.density;
	}

	public static int getScreenWidth() {
		if (screenWidth == 0)
			setScreenInfo();
		return screenWidth;
	}

	public static int getScreenHeight() {
		if (screenHeight == 0)
			setScreenInfo();
		return screenHeight;
	}
	
	public static int getTitleBarHeight(Activity activity) {
		int contentTop = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();  
		//statusBarHeight是上面所求的状态栏的高度  
		int titleBarHeight = contentTop - getStatusBarHeight(activity);
		
		return titleBarHeight;
	}

	public static float getDensity() {
		if (density == 0.0f)
			setScreenInfo();
		return density;
	}

	public static boolean hasSDCard() {
		boolean mHasSDcard = false;
		if (Environment.MEDIA_MOUNTED.endsWith(Environment.getExternalStorageState())) {
			mHasSDcard = true;
		} else {
			mHasSDcard = false;
		}

		return mHasSDcard;
	}

	public static String getSdcardPath() {

		if (hasSDCard())
			return Environment.getExternalStorageDirectory().getAbsolutePath();

		return "/sdcard/";
	}

	private static boolean sdcardCanWrite() {
		return Environment.getExternalStorageDirectory().canWrite();
	}

	public static boolean hasSdcardAndCanWrite() {
		return hasSDCard() && sdcardCanWrite();
	}

	/**
	 * 获取SDCARD的可用大小,单位字节
	 * 
	 * @return
	 */
	public long getSdcardtAvailableStore() {

		if (hasSdcardAndCanWrite()) {
			String path = getSdcardPath();
			if (path != null) {
				StatFs statFs = new StatFs(path);

				long blocSize = statFs.getBlockSize();

				long availaBlock = statFs.getAvailableBlocks();

				return availaBlock * blocSize;
			}
		}

		return 0;
	}

	public static NetWorkType getNetworkType() {

		ConnectivityManager connMgr = (ConnectivityManager) BaseApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		if (networkInfo != null) {
			switch (networkInfo.getType()) {
			case ConnectivityManager.TYPE_MOBILE:
				return NetWorkType.mobile;
			case ConnectivityManager.TYPE_WIFI:
				return NetWorkType.wifi;
			}
		}

		return NetWorkType.none;
	}


	private static String transformIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
                + "." + (i >> 24 & 0xFF);
    }


	public static String getVersionName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionName;
		} catch (Exception e) {
		}
		return "";
	}

	public static int getVersionCode(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;
		} catch (Exception e) {
		}
		return 0;
	}
	
	public static String getPackage(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.packageName;
		} catch (Exception e) {
		}
		return "";
	}
	
	public static void scanPhoto(File file) {
		 Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		 Uri uri = Uri.fromFile(file);
		 intent.setData(uri);
		 BaseApplication.getContext().sendBroadcast(intent);
	}


	public static int getScreenHeight(Activity paramActivity) {
		Display display = paramActivity.getWindowManager().getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		return metrics.heightPixels;
	}

	public static int getStatusBarHeight(Activity paramActivity) {
		Rect localRect = new Rect();
		paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
		return localRect.top;

	}



	// below status bar,include actionbar, above softkeyboard
	public static int getAppHeight(Activity paramActivity) {
		Rect localRect = new Rect();
		paramActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
		return localRect.height();
	}

	public static boolean isKeyBoardShow(Activity paramActivity) {
		int height = SystemUtility.getScreenHeight(paramActivity) - SystemUtility.getStatusBarHeight(paramActivity)
				- SystemUtility.getAppHeight(paramActivity);
		return height != 0;
	}

	 /**
     * 模拟发送点击事件
     * @param view
     * @param x
     * @param y
     */
    public static void setSimulateClick(View view, float x, float y) {
        long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime,
            MotionEvent.ACTION_DOWN, x, y, 0);
        downTime += 100;
        final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime,
            MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(downEvent);
        view.onTouchEvent(upEvent);
        downEvent.recycle();
        upEvent.recycle();
      }
    
    /**
     * 获取本地资源字符串
     * 
     * @param  resId
     */
    public static final String getString(int resId) {
        Context context = BaseApplication.getContext();
        if (context == null || context.getResources() == null){
            return  "";
        }
        String string = "";
        try {
            string =  context.getResources().getString(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }
    
    /**
     * 获取本地Color
     * 
     * @param  resId
     */
    public static final int getColor(int resId) {
        int color = 0x00000000;
        try {
            color = BaseApplication.getContext().getResources().getColor(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return color;
    }

	/**
	 * 获取本地Color
	 *
	 * @param  resId
	 */
	public static final Drawable getDrawable(int resId) {
		Drawable drawable = null;
		try {
			drawable = BaseApplication.getContext().getResources().getDrawable(resId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return drawable;
	}

	/**
	 * 判断某个服务是否正在运行的方法
	 *
	 * @param context
	 * @param serviceName
	 *            是包名+服务的类名（例如：com.clean.spaceplus.junk.service.SystemCacheService）
	 * @return true代表正在运行，false代表服务没有正在运行
	 */
	public static boolean isServiceRunning(Context context, String serviceName) {
		boolean isRunning = false;
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> list = am.getRunningServices(Integer.MAX_VALUE);
		if (list == null || list.size() <= 0) {
			return false;
		}
		for(ActivityManager.RunningServiceInfo serviceInfo : list){
			String name = serviceInfo.service.getClassName().toString();
			if (serviceName.equals(name)){
				isRunning = true;
				break;
			}
		}
		return isRunning;
	}



	/**
	 * xgs 判断当前是不是锁屏状态。
	 * **/
	public final static boolean isScreenLocked(Context c) {
		try {
			KeyguardManager mKeyguardManager = (KeyguardManager) c
					.getSystemService(Context.KEYGUARD_SERVICE);
			return mKeyguardManager.inKeyguardRestrictedInputMode();
		} catch (Exception e) {

		}
		return false;
	}
}
