  
  # Overview
  
  Trying show simple memory problem when java application running in docker container.
  
  ## App
   * I've simple spring boot app (spring boot 2.0.4)
   * two endpoints
     * /api/initialMemory - invoke intially, - displays memory stats
     * /api/allocate80  - dumps data into jvm upto 80% of jvm memory
  ## Build app
  * ```gradle clean build```
  
  ## Problem
  * build docker image
    * ```docker build -t gangakrishh/springboot2:openjre8 -f Dockerfile.openjre8 . ```
  * run docker container
    * ```docker run -d --name mycontainerjre -p 8080:8080 -m600m gangakrishh/springboot2:openjre8```
  * Acces endpoints
    * ``` http://localhost:8080/api/initialMemory```
      * observ some data
    * access ``` http://localhost:8080/api/allocate80 ```
      * observ that container is killed because application tried to use more memory than container memory
        * ```docker inspect mycontainerjre | jq .[0].State```
  ## Solution 1
  * passing jvm memory argument as part JAVA_OPTS
  * build docker image
    * ```  docker build -t gangakrishh/springboot2:openjre8env -f Dockerfile.openjre8env . ```
  * run docker container
    * ```docker run -d --name mycontainerjop -p 8080:8080 -m600m -e JAVA_OPTIONS='-Xmx600m' gangakrishh/springboot2:openjre8env```
      * observe we are passing JAVA_OPTS environment variable, see [Dockfile.openjre8env](spring-boot2-docker/Dockerfile.openjre8env
) content
    * Acces endpoints
      * ```http://localhost:8080/api/initialMemory```
        * output like below
          * ``` ```
      * ```http://localhost:8080/api/allocate80 ```
        * consume 80% jvm memory, but container won't be killed
  ## Solution 2
   * java people fixed this issue on experiment mode in jdk8u131+ and in java 9, now no need to pass 'XmX' as JAVA_OPTS, instead
     pass two arguments see content [Dockerfile.openjre8cgroup](spring-boot2-docker/Dockerfile.openjre8cgroup) content
   * buld docker image
     * ```docker build -t gangakrishh/springboot2:openjre8cgroup -f Dockerfile.openjre8cgroup . ```
   * run docker image
     * ```  docker run -d --name mycontainercgr -p 8080:8080 -m600m gangakrishh/springboot2:openjre8cgroup```
   * Acces endpoints
      * ```http://localhost:8080/api/initialMemory```
        * output like below
          * ``` ```
      * ```http://localhost:8080/api/allocate80 ```
        * consume 80% jvm memory, but container won't be killed
  
  ## Solution 3
  * in java 10 issue fixed for long term, no need to pass JAVA_OPTS or -XX:+UseCGroupMemoryLimitForHeap, java 10 by default recognizes if application running in container.
  * buld docker image
    * ```docker build -t gangakrishh/springboot2:openjre10 -f Dockerfile.openjre10 .```
  * run docker image
    * ```docker run -d --name mycontainer10j -p 8080:8080 -m600m gangakrishh/springboot2:openjre10```
  * Acces endpoints
      * ```http://localhost:8080/api/initialMemory```
        * output like below
          * ``` ```
      * ```http://localhost:8080/api/allocate80 ```
        * consume 80% jvm memory, but container won't be killed

