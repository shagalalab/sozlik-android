package com.shagalalab.sozlik.helper;

import android.content.Context;
import android.content.pm.PackageManager;

import com.shagalalab.sozlik.R;

/**
 * Created by manas on 20.03.18.
 */

public class PackageHelper {
    private PackageManager packageManager;
    private Context context;

    public PackageHelper(Context context) {
        this.context = context;
        packageManager = context.getPackageManager();
    }

    public boolean isAppInstalled() {
        try {
            packageManager.getPackageInfo(context.getString(R.string.qqkeyboard_package), PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return false;
    }
}
