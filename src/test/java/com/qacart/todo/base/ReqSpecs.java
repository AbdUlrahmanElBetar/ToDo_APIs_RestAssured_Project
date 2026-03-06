package com.qacart.todo.base;

import com.qacart.todo.config.Config;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ReqSpecs {

    public static RequestSpecification reqSpec() {
        return given()
                .baseUri(Config.getBaseUrl())
                .contentType(ContentType.JSON)
                .log().all();
    }
}
