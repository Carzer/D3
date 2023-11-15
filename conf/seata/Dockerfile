FROM openjdk:8-jdk-slim-bullseye
COPY ./seata/seata-server-1.4.2/ /carzer/seata/
WORKDIR /carzer/seata
CMD ["./bin/seata-server.sh", "-p 8091 -h 127.0.0.1 -m file"]
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo "Asia/Shanghai" > /etc/timezone