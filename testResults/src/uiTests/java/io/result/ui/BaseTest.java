package io.result.ui;

import io.result.ui.POM.AuthenticationPage;
import io.result.ui.POM.Banner;
import io.result.ui.config.UITestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(classes = {UITestConfig.class})
public class BaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    public AuthenticationPage authenticationPage;

    @Autowired
    public Banner banner;

}
