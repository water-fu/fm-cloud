package com.fm.zipkin;

import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;
import zipkin.server.EnableZipkinServer;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinApplication {

  private static final org.slf4j.Logger log = LoggerFactory.getLogger(ZipkinApplication.class);

  public static void main(String[] args) throws UnknownHostException {
    Environment env =
        new SpringApplicationBuilder(ZipkinApplication.class).web(true).run(args).getEnvironment();
    log.info(
        "\n----------------------------------------------------------\n\t"
            + "Application '{}' is running! Access URLs:\n\t"
            + "Local: \t\thttp://127.0.0.1:{}\n\t"
            + "External: \thttp://{}:{}\n----------------------------------------------------------",
        env.getProperty("spring.application.name"),
        env.getProperty("server.port"),
        InetAddress.getLocalHost().getHostAddress(),
        env.getProperty("server.port"));

    String configServerStatus = env.getProperty("configserver.status");
    log.info(
        "\n----------------------------------------------------------\n\t"
            + "Config Server: \t{}\n----------------------------------------------------------",
        configServerStatus == null
            ? "Not found or not setup for this application"
            : configServerStatus);
  }
}
