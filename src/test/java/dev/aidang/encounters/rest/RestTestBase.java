package dev.aidang.encounters.rest;

import dev.aidang.encounters.IntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.net.http.HttpClient;

@IntegrationTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTestBase {

    protected final HttpClient httpClient = HttpClient
            .newBuilder()
            .build();

    @LocalServerPort
    protected int port;

}
