package com.qacart.todo.Steps;

import com.github.javafaker.Faker;
import com.qacart.todo.apis.ToDoApis;
import com.qacart.todo.models.ToDo;
import io.restassured.response.Response;

public class ToDoSteps {

    public static ToDo getToDoData() {
        Faker faker = new Faker();
        boolean isCompleted = false;
        String item = faker.book().title();
        return new ToDo(isCompleted, item);
    }

    public static String getToDoId(ToDo toDo, String token) {
        Response response = ToDoApis.addToDo(toDo, token);
        return response.jsonPath().getString("_id");
    }
}
