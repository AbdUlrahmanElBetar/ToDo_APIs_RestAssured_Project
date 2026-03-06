package com.qacart.todo.testCases;


import com.qacart.todo.Steps.UserSteps;
import com.qacart.todo.apis.UserApis;
import com.qacart.todo.data.ErrorMessages;
import com.qacart.todo.models.User;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@Feature("User Management")
public class UserTest {

    @Story("User Registration")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that a new user can successfully register with valid email and password")
    @Test(description = "User should be able to register")
    public void shouldBeAbleToRegister() {

        User user = UserSteps.generateUserData();

        Response response = UserApis.register(user);
        User returnedResponse = response.as(User.class);

        assertThat(response.statusCode(), equalTo(201));
        assertThat(returnedResponse.getFirstName(), equalTo(user.getFirstName()));

    }

    @Story("User Registration Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the system prevents registering a user with an email that already exists")
    @Test(description = "User should not be able to register with existing email")
    public void shouldNotBeAbleToRegisterByTheSameEmail() {

        User user = UserSteps.getRegUser();

        Response response = UserApis.register(user);
        assertThat(response.statusCode(), equalTo(400));

        Error returnedMsg = response.as(Error.class);
        assertThat(returnedMsg.getMessage(), equalTo(ErrorMessages.EMAIL_IS_ALREADY_EXIST));
    }

    @Story("User Login")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that a registered user can successfully log in using valid credentials")
    @Test(description = "User should be able to login with valid credentials")
    public void userShouldBeAbleToLogin() {

        User user = UserSteps.getRegUser();
        User loginData = new User(user.getEmail(), user.getPassword());

        Response response = UserApis.login(loginData);
        User returnedResponse = response.as(User.class);

        assertThat(response.path("firstName"), equalTo(user.getFirstName()));
        assertThat(returnedResponse.getAccess_token(), not(equalTo(null)));
        assertThat(response.statusCode(), equalTo(200));

    }

    @Story("Invalid Login Attempt")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that login fails when a user provides invalid email or password")
    @Test(description = "User should not be able to login with invalid credentials")
    public void userShouldNotBeAbleToLoginWithInvalidCredentials() {

        User user = UserSteps.getRegUser();
        User loginData = new User(user.getEmail(), "InvalidPassword");

        Response response = UserApis.login(loginData);

        Error returnedMsg = response.as(Error.class);
        assertThat(returnedMsg.getMessage(), equalTo(ErrorMessages.EMAIL_AND_PASSWORD_COMBINATION_IS_NOT_CORRECT));
    }


}
