package com.shagalalab.sozlik.helper.thread;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * Created by atabek on 03/27/2018.
 */

public class MainThreadExecutor implements Executor {
    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(@NonNull Runnable command) {
        mainThreadHandler.post(command);
    }
}
