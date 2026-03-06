package com.qacart.todo.testCases;

import com.qacart.todo.Steps.ToDoSteps;
import com.qacart.todo.Steps.UserSteps;
import com.qacart.todo.apis.ToDoApis;
import com.qacart.todo.data.ErrorMessages;
import com.qacart.todo.models.ToDo;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Feature("ToDo Management")
public class ToDoTest {


    @Story("Add ToDo")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that an authenticated user can successfully create a new ToDo item")
    @Test(description = "User should be able to add a new ToDo")
    public void shouldBeAbleToAddToDo() {
        String token = UserSteps.getToken();
        ToDo toDo = ToDoSteps.getToDoData();

        Response response = ToDoApis.addToDo(toDo, token);

        assertThat(response.statusCode(), equalTo(201));

        ToDo returnedObject = response.as(ToDo.class);
        assertThat(returnedObject.isCompleted(), equalTo(false));
        assertThat(returnedObject.getItem(), equalTo(toDo.getItem()));

    }

    @Story("Add ToDo Validation")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that the API prevents creating a ToDo when the request body is invalid or missing required fields")
    @Test(description = "User should not be able to add ToDo with invalid data")
    public void shouldNotBeAbleToAddToDo() {
        String token = UserSteps.getToken();
        ToDo toDo = new ToDo("Learn Playwright");

        Response response = ToDoApis.addToDo(toDo, token);

        assertThat(response.statusCode(), equalTo(400));
        Error returnedMsg = response.as(Error.class);
        assertThat(returnedMsg.getMessage(), equalTo(ErrorMessages.IS_COMPLETED_IS_REQUIRED));
    }

    @Story("Get ToDo")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a user can retrieve a specific ToDo item using its ID")
    @Test(description = "User should be able to retrieve an existing ToDo")
    public void shouldBeAbleToGetToDo() {
        String token = UserSteps.getToken();
        // ToDo toDo = new ToDo(false, "Learn Playwright");
        ToDo toDo = ToDoSteps.getToDoData();
        String toDoId = ToDoSteps.getToDoId(toDo, token);

        Response response = ToDoApis.getToDo(toDoId, token);

        assertThat(response.statusCode(), equalTo(200));

        ToDo returnedObject = response.as(ToDo.class);
        assertThat(returnedObject.getItem(), equalTo(toDo.getItem()));
        assertThat(returnedObject.isCompleted(), equalTo(toDo.isCompleted()));
        assertThat(returnedObject.getId(), equalTo(toDoId));

    }

    @Story("Update ToDo")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a user can update an existing ToDo item successfully")
    @Test(description = "User should be able to update a ToDo")
    public void shouldBeAbleToUpdateToDo() {
        String token = UserSteps.getToken();
        ToDo toDo = ToDoSteps.getToDoData();
        String toDoId = ToDoSteps.getToDoId(toDo, token);
        Response response = ToDoApis.updateToDo(toDo, token, toDoId);
        assertThat(response.statusCode(), equalTo(200));

        ToDo returnedObject = response.as(ToDo.class);
        assertThat(returnedObject.getId(), equalTo(toDoId));
        assertThat(returnedObject.getItem(), equalTo(toDo.getItem()));

    }

    @Story("Delete ToDo")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that a user can delete an existing ToDo item successfully")
    @Test(description = "User should be able to delete a ToDo")
    public void shouldBeAbleToDeleteToDo() {
        String token = UserSteps.getToken();
        ToDo toDo = ToDoSteps.getToDoData();
        String toDoId = ToDoSteps.getToDoId(toDo, token);
        Response response = ToDoApis.deleteToDo(token, toDoId);
        assertThat(response.statusCode(), equalTo(200));

        ToDo returendObject = response.as(ToDo.class);
        assertThat(returendObject.getId(), equalTo(toDoId));
        assertThat(returendObject.getItem(), equalTo(toDo.getItem()));

    }


}
