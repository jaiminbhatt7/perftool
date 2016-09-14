package com.icloud.test;

import com.icloud.test.utils.Executor;
import com.icloud.test.utils.ReportGenerator;

/**
 * The class generates reports for response time for given URL
 */
public class LatencyAnalyzer {

    public static void main(String[] argv) throws InterruptedException {
        if (argv.length != 2) {
            throw new IllegalArgumentException("Invalid input");
        }
        Executor executor = new Executor(argv[0], Integer.valueOf(argv[1]));
        ReportGenerator.printReport(argv[0], executor.run());
    }
}
