package com.test.bed22;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

/**
 * Base class for FIT tests
 */
@ActiveProfiles("test")
public class BaseFIT {
    /**
     * Port in which the server is running
     */
    @LocalServerPort
    public int serverPort;

    protected void setUp(int restAssuredPort) {
        RestAssured.port = restAssuredPort;
        JsonPath.config = new JsonPathConfig("UTF-8")
                .numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL);
    }

}