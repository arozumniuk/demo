package io.result.ui;

import io.qameta.allure.Feature;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Feature("Logout")
public class LogoutTests extends BaseTest {

    @Value("${url}")
    String baseUrl;

    @Value("${user.email}")
    String email;

    @Value("${user.password}")
    String password;


    @BeforeMethod
    public void setUp() {
        authenticationPage.open().signIn(email, password);
    }

    @Test
    public void checkLogOutByUI() {
        banner.clickSignOut();

        String expectedPage = baseUrl + "?controller=authentication&back=my-account";
        String actualPage = banner.getCurrentUrl();

        assertThat(actualPage).isEqualToIgnoringCase(expectedPage);
    }

    @Test
    public void checkLogOutByAnotherWay() {
        banner.logout();
    }

}
