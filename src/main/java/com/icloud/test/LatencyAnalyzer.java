package com.icloud.test;

import com.icloud.test.utils.Executor;
import com.icloud.test.utils.ReportGenerator;

/**
 * The class generates reports for response time for given URL
 */
public class LatencyAnalyzer {

    public static void main(String[] argv) {
        if (argv.length != 2) {
            throw new IllegalArgumentException("Invalid input");
        }
        Executor executor = new Executor();
        ReportGenerator.printReport(argv[0], executor.runConcurrentRequests(argv[0], Integer.valueOf(argv[1])));
    }
}
