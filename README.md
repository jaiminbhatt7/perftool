# perftool
Utility to generate reports for response time of a URL

## Getting Started
#### Prerequisites:
 * [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 * [Apache Maven] (https://maven.apache.org/download.cgi)
 
To verify open terminal and verify following:
```
$ mvn -version
Apache Maven 3.2.3 (33f8c3e1027c3ddde99d3cdebad2656a31e8fdf4; 2014-08-11T13:58:10-07:00)
Maven home: /Applications/apache-maven-3.2.3
Java version: 1.8.0_72, vendor: Oracle Corporation
Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_72.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.11.2", arch: "x86_64", family: "mac"
```

#### Create executable jar:
```
$ mvn clean install
```
The jar will be in `target` directory.
```
$ ls target 
classes                   generated-sources         maven-archiver            maven-status              perftool-1.0-SNAPSHOT.jar
```

### How to run:
```
$ java -jar target/perftool-1.0-SNAPSHOT.jar "http://en.wikipedia.org/wiki/Main_Page" 10
==========================================
Report for http://en.wikipedia.org/wiki/Main_Page
------------------------------------------
10th percentile: 21 ms
15th percentile: 21 ms
50th percentile: 22 ms
90th percentile: 74 ms
95th percentile: 80 ms
99th percentile: 81 ms
------------------------------------------
Mean: 29 ms
------------------------------------------
Standard Deviation: 17.320508075688775 ms
------------------------------------------
```
In the above command, first argument is the target URL and the second argument is number of concurrent requests
