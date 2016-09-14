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

        System.out.println("10th percentile: " + latencies.get((int) Math.round(0.10 * latencies.size())) + " ms");
        System.out.println("15th percentile: " + latencies.get((int) Math.round(0.15 * latencies.size())) + " ms");
        System.out.println("50th percentile: " + latencies.get((int) Math.round(0.50 * latencies.size())) + " ms");
        System.out.println("90th percentile: " + latencies.get((int) Math.round(0.90 * latencies.size())) + " ms");
        System.out.println("95th percentile: " + latencies.get((int) Math.round(0.95 * latencies.size())) + " ms");
        System.out.println("99th percentile: " + latencies.get((int) Math.round(0.99 * latencies.size())) + " ms");

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
