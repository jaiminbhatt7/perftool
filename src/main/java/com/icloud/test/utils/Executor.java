package com.icloud.test.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The class is a utility to collect response times for a given URL
 * by sending concurrent connection requests
 */
public class Executor {

    private String url;
    // Thread safe array list to collect response times
    private List<Long> latencyCollector = new CopyOnWriteArrayList<>();
    private ExecutorService executor;

    public Executor(String url, int numOfConcurrentRequests) {
        this.url = url;
        this.executor = Executors.newFixedThreadPool(numOfConcurrentRequests);
    }

    /**
     * This method will send 100 requests with {@code numOfConcurrentRequests} concurrency
     * to the {@code targetUrl} and return list of response time measured for each successful
     * request. Unsuccessful requests (timeouts, IO errors etc) will be ignored
     *
     * @return List of response times
     * @throws InterruptedException if the threads are interrupted
     */
    public List<Long> run() throws InterruptedException {
        List<Callable<Void>> callables = new ArrayList<>();

        // limit of 100 as defined by the problem statement
        for (int i = 0; i < 100; i++) {
            callables.add(getCallable());
        }
        executor.invokeAll(callables);
        executor.shutdown();
        while (!executor.isTerminated()) {
            // wait till all the threads are terminated
        }
        return latencyCollector;
    }

    private Callable<Void> getCallable() {
        return () -> {
            try {
                URL target = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) target.openConnection();
                connection.setRequestMethod("GET");
                long startTime = System.currentTimeMillis();
                connection.connect();
                long endTime = System.currentTimeMillis();
                latencyCollector.add(endTime - startTime);
            } catch (IOException ignored) {
            }
            return null;
        };
    }

}
