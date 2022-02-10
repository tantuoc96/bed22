package com.test.bed22.ra;

import com.test.bed22.RestAssuredClient;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HtmlUtilControllerRA extends RestAssuredClient {

    Response textToJsonValue(String body) {
        RequestSpecification request = setupCommunicationBase();
        return request.when()
                .body(body)
                .post("/api/v1/converter/textToJsonValue");
    }

    Response textAnalysis(Object body, String textType) {
        RequestSpecification request = setupCommunicationBase();

        return request.when()
                .body(body)
                .post("/api/v1/analysis/" + textType);
    }

    Response markdownToHtml(Object body, String textType) {
        RequestSpecification request = setupCommunicationBase();

        return request.when()
                .body(body)
                .post("/api/v1/converter/markdownTo/" + textType);
    }
}
