package com.icloud.test.utils;

import java.util.Collections;
import java.util.List;

/**
 * The class is used to generate report by analyzing the latency
 */
public class ReportGenerator {

    /**
     * Prints the 10th, 15th, 50th, 90th, 95th and 99th percentile of response time
     * along with mean and standarad deviation
     * @param url url for test
     * @param latencies list of measured latencies
     */
    public static void printReport(String url, List<Long> latencies) {

        // sorting the input to calculate percentiles
        Collections.sort(latencies);

        System.out.println("==========================================");
        System.out.println("Report for " + url);
        System.out.println("------------------------------------------");

        // we send fix number of requests but in case there are timeouts or IO exceptions,
        // we should not hardcode the list size below
        for (float i : new float[]{0.10f, 0.15f, 0.50f, 0.90f, 0.95f, 0.99f}) {
            System.out.println((int) (i * 100) + "th percentile: " + latencies.get((int) Math.round(i * latencies.size())) + " ms");
        }

        System.out.println("------------------------------------------");
        long sum = 0;
        for (Long ni : latencies) {
            sum += ni;
        }
        long mean = sum/latencies.size();
        System.out.println("Mean: " + mean + " ms");
        System.out.println("------------------------------------------");

        long dev = 0;
        for (Long ni : latencies) {
            dev += Math.pow(ni - mean, 2);
        }
        System.out.println("Standard Deviation: " + Math.sqrt(dev/latencies.size()) + " ms");
        System.out.println("------------------------------------------");
    }
}
