package com.icloud.test.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The class is a utility to collect response times for a given URL
 * by sending concurrent connection requests
 */
public class Executor {

    // Thread safe array list to collect response times
    private List<Long> latencyCollector = new CopyOnWriteArrayList<>();

    /**
     * This method will send {@code numOfConcurrentRequests * numOfConcurrentRequests}
     * to the {@code targetUrl} and return list of repsonse time measured for each successful
     * request. Unsuccessful requests (timeouts, IO errors etc) will be ignored
     *
     * @param targetUrl URL to measure response time
     * @param numOfConcurrentRequests number of threads making requests in parallel
     * @return List of response times
     */
    public List<Long> runConcurrentRequests(String targetUrl, int numOfConcurrentRequests) {

        // service to send asynchronous concurrent requests
        ExecutorService executor = Executors.newFixedThreadPool(numOfConcurrentRequests);

        for (int i = 0; i < numOfConcurrentRequests * numOfConcurrentRequests; i++) {
            Runnable newThread = new ThreadRunner(targetUrl);
            executor.execute(newThread);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // wait till all the threads are terminated
        }
        return latencyCollector;
    }


    /**
     * The class implements {@code Runnable} which collects the latency
     * for every request
     */
    private class ThreadRunner implements Runnable {

        private String targetUrl;

        public ThreadRunner(String targetUrl) {
            this.targetUrl = targetUrl;
        }

        public void run() {
            try {
                URL target = new URL(targetUrl);
                HttpURLConnection connection = (HttpURLConnection) target.openConnection();
                connection.setRequestMethod("GET");
                long startTime = System.currentTimeMillis();
                connection.connect();
                long endTime = System.currentTimeMillis();
                latencyCollector.add(endTime - startTime);
            } catch (IOException ignored) {
            }
        }
    }

}
