package io.result.api;

import io.qameta.allure.Feature;
import io.restassured.response.Response;
import io.result.api.dto.CreatedUser;
import io.result.api.dto.User;
import org.testng.annotations.Test;

import static io.result.randomizer.RandomBeanGenerator.random;
import static org.assertj.core.api.Assertions.assertThat;

public class UserTests extends ReqresClient {

    @Test
    @Feature("Create user")
    public void checkIfUserCreated() {

        User expectedUser = random(User.class);
        Response response = createUser(expectedUser);

        assertThat(response.statusCode()).isEqualTo(201);

        User createdUser = response.getBody().as(User.class);
        assertThat(createdUser).isEqualTo(expectedUser);
    }


    @Test
    @Feature("Update user")
    public void checkIfUserUpdated() {
        CreatedUser createdUser = createUser().setId(2);
        User updateForUser = random(User.class);
        Response response = updateUser(updateForUser, createdUser.getId());

        assertThat(response.statusCode()).isEqualTo(200);

        User updatedUser = response.getBody().as(User.class);
        assertThat(updatedUser).isEqualTo(updateForUser);
    }

    @Test
    @Feature("Get user by id")
    public void checkGetById() {
        Response response = getUser(12);
        assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    @Feature("Delete user")
    public void checkIfUserDeleted() {
        Response response = deleteUser(12);
        assertThat(response.statusCode()).isEqualTo(204);
    }
}
