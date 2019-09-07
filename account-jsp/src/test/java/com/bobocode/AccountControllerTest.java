package com.bobocode;

import com.bobocode.config.RootConfig;
import com.bobocode.config.WebConfig;
import com.bobocode.web.controller.AccountController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringJUnitWebConfig(classes = {RootConfig.class, WebConfig.class})
class AccountControllerTest {
    @Autowired
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    void testAccountControllerAnnotation() {
        Controller controller = AccountController.class.getAnnotation(Controller.class);

        assertThat(controller, notNullValue());
    }

    @Test
    void testAccountControllerRequestMapping() {
        RequestMapping requestMapping = AccountController.class.getAnnotation(RequestMapping.class);

        assertThat(requestMapping, notNullValue());
        assertThat(requestMapping.value(), arrayWithSize(1));
        assertThat(requestMapping.value(), arrayContaining("/accounts"));
    }

    @Test
    void testGetAccountsResponseStatusCode() throws Exception {
        mockMvc.perform(get("/accounts")).andExpect(status().isOk());
    }

    @Test
    void testGetAccountsViewName() throws Exception {
        mockMvc.perform(get("/accounts"))
                .andExpect(view().name("accounts"));
    }

    @Test
    void testGetAccountsModelContainsAccountList() throws Exception {
        mockMvc.perform(get("/accounts"))
                .andExpect(model().attributeExists("accountList"));
    }

    @Test
    void testAccountsResponseDefaultListSize() throws Exception {
        mockMvc.perform(get("/accounts"))
                .andExpect(model().attribute("accountList", hasSize(10)));
    }

    @Test
    void testAccountsResponseListSize() throws Exception {
        mockMvc.perform(get("/accounts").param("size", "20"))
                .andExpect(model().attribute("accountList", hasSize(20)));
    }


}
