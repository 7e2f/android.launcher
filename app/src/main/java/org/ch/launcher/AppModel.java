package org.ch.launcher;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;

import java.io.File;
import java.util.Arrays;

/**
 * @credit http://developer.android.com/reference/android/content/AsyncTaskLoader.html
 */
public class AppModel {

    private final Context mContext;
    private final ApplicationInfo mInfo;

    private String mAppLabel;
    private Drawable mIcon;

    private boolean mMounted;
    private final File mApkFile;

    public boolean visible;

    public AppModel(Context context, ApplicationInfo info) {
        mContext = context;
        mInfo = info;

        mApkFile = new File(info.sourceDir);
    }

    String[] appsWhitelist = {
            "org.gateshipone.odyssey",
            "com.nexon.maplem.global",
            "net.sourceforge.opencamera",
            "com.google.android.deskclock",
            "com.simplemobiletools.gallery",
            "com.maklesoft.padlock",
            "com.fsck.k9",
            "com.duckduckgo.mobile.android",
            "org.amoradi.syncopoli",
            "de.blinkt.openvpn",
            "org.fdroid.fdroid",
            "com.android.dialer",
            "org.thunderdog.challegram",
            "com.moez.QKSMS"
    };



    public boolean visible() {
        if (Arrays.asList(appsWhitelist).contains(getApplicationPackageName())) {
            return true;
        } else {
            return false;
        }
    }

    public ApplicationInfo getAppInfo() {
        return mInfo;
    }

    public String getApplicationPackageName() {
        return getAppInfo().packageName;
    }

    public String getLabel() {
        return mAppLabel;
    }

    public Drawable getIcon() {
        if (mIcon == null) {
            if (mApkFile.exists()) {
                mIcon = mInfo.loadIcon(mContext.getPackageManager());
                return mIcon;
            } else {
                mMounted = false;
            }
        } else if (!mMounted) {
            // If the app wasn't mounted but is now mounted, reload
            // its icon.
            if (mApkFile.exists()) {
                mMounted = true;
                mIcon = mInfo.loadIcon(mContext.getPackageManager());
                return mIcon;
            }
        } else {
            return mIcon;
        }

        return mContext.getResources().getDrawable(android.R.drawable.sym_def_app_icon);
    }


    void loadLabel(Context context) {
        if (mAppLabel == null || !mMounted) {
            if (!mApkFile.exists()) {
                mMounted = false;
                mAppLabel = mInfo.packageName;
            } else {
                mMounted = true;
                CharSequence label = mInfo.loadLabel(context.getPackageManager());
                mAppLabel = label != null ? label.toString() : mInfo.packageName;
            }
        }
    }
}
