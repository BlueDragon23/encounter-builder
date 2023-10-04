package dev.aidang.encounters.rest;

import org.springframework.boot.test.web.server.LocalServerPort;

import java.net.http.HttpClient;

public class RestTestBase {

    protected final HttpClient httpClient = HttpClient.newBuilder().build();

    @LocalServerPort
    protected int port;
}
