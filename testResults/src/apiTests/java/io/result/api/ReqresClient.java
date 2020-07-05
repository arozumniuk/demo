package io.result.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.result.api.dto.CreatedUser;
import io.result.api.dto.User;
import io.result.api.utilites.ApiRoutes;

import static io.restassured.RestAssured.given;
import static io.result.randomizer.RandomBeanGenerator.random;

public class ReqresClient extends BaseTestsRunner implements ApiRoutes {

    public CreatedUser createUser() {
        return createUser(random(User.class)).getBody().as(CreatedUser.class);
    }

    @Step("Send request to create user")
    public Response createUser(User user) {
        return given().body(user).when().post(properties.getApi() + USER);
    }

    @Step("Send request to get user with id = {id}")
    public Response getUser(int id) {
        return given()
                .pathParams("id", id)
                .when()
                .get(properties.getApi() + USER + "{id}");
    }

    @Step("Send request to update user with id = {id}")
    public Response updateUser(User user, int id) {
        return given()
                .body(user)
                .pathParams("id", id)
                .when()
                .put(properties.getApi() + USER + "{id}");
    }

    @Step("Send request to delete user with id = {id}")
    public Response deleteUser(int id) {
        return given()
                .pathParams("id", id)
                .when()
                .delete(properties.getApi() + USER + "{id}");
    }

}
