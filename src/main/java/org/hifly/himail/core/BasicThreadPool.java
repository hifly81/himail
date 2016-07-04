package org.hifly.himail.core;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BasicThreadPool {

    private final ExecutorService pool;

    public BasicThreadPool(int maxThreads) {
        pool = Executors.newFixedThreadPool(maxThreads);
    }

    public Future submit(Callable callable) {
        return pool.submit(callable);
    }

}
