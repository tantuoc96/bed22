package com.test.bed22;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class RestAssuredClient {

    protected RequestSpecification setupCommunicationBase() {
        RequestSpecification requestSpecification = RestAssured.given()
                .log().ifValidationFails()
                .contentType(ContentType.JSON);
        requestSpecification.then().log().ifValidationFails();
        return requestSpecification;
    }
}