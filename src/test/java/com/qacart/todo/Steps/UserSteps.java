package com.qacart.todo.Steps;

import com.github.javafaker.Faker;
import com.qacart.todo.apis.UserApis;
import com.qacart.todo.models.User;
import io.restassured.response.Response;

public class UserSteps {

    public static User generateUserData() {
        Faker faker = new Faker();
        String fName = faker.name().firstName();
        String lName = faker.name().lastName();
        String mail = faker.internet().emailAddress();
        String password = "12341234";

        return new User(fName, lName, mail, password);
    }

    public static User getRegUser() {
        User user = UserSteps.generateUserData();
        UserApis.register(user);
        return user;
    }

    public static String getToken() {
        User user = generateUserData();
        Response response = UserApis.register(user);
        return response.jsonPath().getString("access_token");
    }
}
