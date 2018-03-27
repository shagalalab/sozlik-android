package com.shagalalab.sozlik.helper.thread;

import java.util.concurrent.Executor;

/**
 * Created by atabek on 03/27/2018.
 */

public class AppExecutors {
    private final Executor diskIo;
    private final Executor mainThread;

    private AppExecutors(Executor diskIo, Executor mainThread) {
        this.diskIo = diskIo;
        this.mainThread = mainThread;
    }

    public AppExecutors() {
        this(new DiskIoThreadExecutor(), new MainThreadExecutor());
    }

    public Executor getDiskIo() {
        return diskIo;
    }

    public Executor getMainThread() {
        return mainThread;
    }
}
