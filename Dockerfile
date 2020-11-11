FROM        java:8

ENV M2_HOME /opt/mvn
ENV M2 /opt/mvn/bin
ENV PATH $PATH:/opt/mvn/bin
ENV MAVEN_OPTS -Djava.net.preferIPv4Stack=true

ADD . /app

RUN mkdir -p /opt; \
    wget -O /opt/apache-maven-3.5.4-bin.tar.gz http://artfiles.org/apache.org/maven/maven-3/3.5.4/binaries/apache-maven-3.5.4-bin.tar.gz; \
    cd /opt/; tar -xzf apache-maven-3.5.4-bin.tar.gz; \
    ln -f -s /opt/apache-maven-3.5.4 /opt/mvn; \
    mkdir -p /app; \
    cd /app; /opt/mvn/bin/mvn clean package -Dmaven.test.skip=true -Dgpg.skip;

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "/app/target/logs-0.0.1.jar"]
