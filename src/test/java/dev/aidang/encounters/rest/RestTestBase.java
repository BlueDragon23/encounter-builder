package dev.aidang.encounters.rest;

import java.net.http.HttpClient;
import org.springframework.boot.test.web.server.LocalServerPort;

public class RestTestBase {

    protected final HttpClient httpClient = HttpClient.newBuilder().build();

    @LocalServerPort
    protected int port;
}
