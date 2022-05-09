package com.quasarfire.quasar.controllers;

import com.quasarfire.quasar.exceptions.MessageException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@WebAppConfiguration
class AllianceTopSecretControllerTest {

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Test
    public void allianceTopSecret() throws Exception, MessageException{

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        String uri = "/topsecret";

        String inputJson = "{ \"satellites\": [ { \"name\": \"kenobi\", \"distance\": 100.0, \"message\": " +
                "[\"este\", \"\", \"\", \"mensaje\", \"\"] }, { \"name\": \"skywalker\", \"distance\": 115.5, \"message\": " +
                "[\"\", \"es\", \"\", \"\", \"secreto\"] }, { \"name\": \"sato\", \"distance\": 142.7, \"message\": " +
                "[\"este\", \"\", \"un\", \"\", \"\"] } ] }";

        String expectedJson = "{\"position\":{\"x\":-58.315252587138595,\"y\":-69.55141837312165}," +
                "\"message\":\"este es un mensaje secreto\"}";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expectedJson,content);
    }

    @Test
    public void allianceTopSecretSplit() throws Exception, MessageException{

        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        String uri = "/topsecret_split/Kenobi";

        String inputJson = "{\"distance\": 100.0,\"message\": [\"este\", \"\", \"\", \"mensaje\", \"\"]}";

        String expected = "{\"position\":null,\"message\":\"Message saved successfully.\"}";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(expected,content);
    }
}