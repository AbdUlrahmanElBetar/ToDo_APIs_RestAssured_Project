package com.qacart.todo.apis;

import com.qacart.todo.base.ReqSpecs;
import com.qacart.todo.data.Route;
import com.qacart.todo.models.ToDo;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ToDoApis {

    public static Response addToDo(ToDo toDo, String token) {

        return given()
                .spec(ReqSpecs.reqSpec())
                .body(toDo)
                .auth().oauth2(token).log().body()
                .when().post(Route.TODOS_PATH)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response getToDo(String toDoId, String token) {
        return given()
                .spec(ReqSpecs.reqSpec())
                .auth().oauth2(token)
                .when()
                .get(Route.TODOS_PATH + "/" + toDoId)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response updateToDo(ToDo toDo, String token, String toDoId) {
        return given()
                .spec(ReqSpecs.reqSpec())
                .auth().oauth2(token)
                .body(toDo)
                .when()
                .put(Route.TODOS_PATH + "/" + toDoId)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response deleteToDo(String token, String todoId) {
        return given()
                .spec(ReqSpecs.reqSpec())
                .auth().oauth2(token)
                .when()
                .delete(Route.TODOS_PATH + "/" + todoId)
                .then()
                .log().all()
                .extract().response();
    }
}
