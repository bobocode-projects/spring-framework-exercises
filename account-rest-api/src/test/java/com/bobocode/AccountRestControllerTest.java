package com.bobocode;

import com.bobocode.config.RootConfig;
import com.bobocode.config.WebConfig;
import com.bobocode.dao.impl.InMemoryAccountDao;
import com.bobocode.web.controller.AccountRestController;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(classes = {RootConfig.class, WebConfig.class})
public class AccountRestControllerTest {
    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private InMemoryAccountDao accountDao;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
        accountDao.clear();
    }

    @Test
    public void testAccountRestControllerAnnotation() {
        RestController restController = AccountRestController.class.getAnnotation(RestController.class);

        assertThat(restController, notNullValue());
    }

    @Test
    public void testAccountRestControllerRequestMapping() {
        RequestMapping requestMapping = AccountRestController.class.getAnnotation(RequestMapping.class);

        assertThat(requestMapping, notNullValue());
        assertThat(requestMapping.value(), arrayWithSize(1));
        assertThat(requestMapping.value(), arrayContaining("/accounts"));
    }

    @Test
    public void testHttpStatusCodeOnCreate() throws Exception {
        mockMvc.perform(
                post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Johnny\", \"lastName\":\"Boy\", \"email\":\"jboy@gmail.com\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCreateAccountReturnsAssignedId() throws Exception {
        mockMvc.perform(
                post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Johnny\", \"lastName\":\"Boy\", \"email\":\"jboy@gmail.com\"}"))
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void testGetAccountsResponseStatusCode() throws Exception {
        mockMvc.perform(get("/accounts").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllAccounts() throws Exception {
        mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Johnny\", \"lastName\":\"Boy\", \"email\":\"jboy@gmail.com\"}"));
        mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Okko\", \"lastName\":\"Bay\", \"email\":\"obay@gmail.com\"}"));

        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].email").value(hasItems("jboy@gmail.com", "obay@gmail.com")));
    }

    @Test
    public void testGetById() throws Exception {
        String responseString = mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Johnny\", \"lastName\":\"Boy\", \"email\":\"jboy@gmail.com\"}"))
                .andReturn().getResponse().getContentAsString();

        int id = JsonPath.parse(responseString).read("$.id");


        mockMvc.perform(get(String.format("/accounts/%d", id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.email").value("jboy@gmail.com"))
                .andExpect(jsonPath("$.firstName").value("Johnny"))
                .andExpect(jsonPath("$.lastName").value("Boy"));
    }

    @Test
    public void testRemoveAccount() throws Exception {
        String responseString = mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Johnny\", \"lastName\":\"Boy\", \"email\":\"jboy@gmail.com\"}"))
                .andReturn().getResponse().getContentAsString();
        int id = JsonPath.parse(responseString).read("$.id");

        mockMvc.perform(delete(String.format("/accounts/%d", id)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testUpdateAccount() throws Exception {
        String responseString = mockMvc.perform(post("/accounts").contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"Johnny\", \"lastName\":\"Boy\", \"email\":\"jboy@gmail.com\"}"))
                .andReturn().getResponse().getContentAsString();
        int id = JsonPath.parse(responseString).read("$.id");

        mockMvc.perform(put(String.format("/accounts/%d", id)).contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{\"id\":\"%d\", \"firstName\":\"Johnny\", \"lastName\":\"Boy\", \"email\":\"johnny.boy@gmail.com\"}", id)))
                .andExpect(status().isNoContent());
    }


}
