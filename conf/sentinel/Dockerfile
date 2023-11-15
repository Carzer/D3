FROM openjdk:8-jdk-slim-bullseye
EXPOSE 8858
EXPOSE 8719
COPY ./sentinel-dashboard-1.8.2.jar /carzer/sentinel/
WORKDIR /carzer/sentinel
CMD ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=8858", "-Dcsp.sentinel.api.port=8719", "-Dcsp.sentinel.dashboard.server=localhost:8858", "-Dproject.name=sentinel-dashboard","-Dauth.enabled=true", "-Dsentinel.dashboard.auth.username=admin", "-Dsentinel.dashboard.auth.password=admin", "-Dserver.servlet.session.timeout=60m", "sentinel-dashboard-1.8.2.jar"]
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo "Asia/Shanghai" > /etc/timezone