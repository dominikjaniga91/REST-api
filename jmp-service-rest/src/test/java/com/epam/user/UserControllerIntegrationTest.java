package com.epam.user;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Dominik_Janiga
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository repository;


    @Test
    @Order(1)
    void shouldCreateUserInDatabase_whenSendPostRequest() throws Exception {
        //given
        String requestContent = readResourceAsString("create_user.json");

        //when
        this.mockMvc.perform(post("/user")
                .content(requestContent)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("John")))
                .andExpect(jsonPath("$.surname", Matchers.is("Doe")))
                .andExpect(jsonPath("$.birthday", Matchers.is("1990-12-22")));

        //then
        User user = this.repository.findById(1L).get();

        Assertions.assertEquals(1L, user.getId());
        Assertions.assertEquals("John", user.getName());
        Assertions.assertEquals("Doe", user.getSurname());
        Assertions.assertEquals(LocalDate.parse("1990-12-22"), user.getBirthday());
    }

    @Test
    @Order(2)
    void shouldReturnUser_whenSendGetRequest() throws Exception {
        //given
        int userId = 1;

        //when
        this.mockMvc.perform(get("/user/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("John")))
                .andExpect(jsonPath("$.surname", Matchers.is("Doe")))
                .andExpect(jsonPath("$.birthday", Matchers.is("1990-12-22")));
    }


    @Test
    @Order(3)
    void shouldChangeUserName_whenSendPutRequest() throws Exception {
        //given
        String requestContent = readResourceAsString("update_user.json");

        //when
        this.mockMvc.perform(put("/user")
                .content(requestContent)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Johnathan")))
                .andExpect(jsonPath("$.surname", Matchers.is("Doe")))
                .andExpect(jsonPath("$.birthday", Matchers.is("1990-12-22")));
    }

    @Test
    @Order(4)
    void shouldDeleteUser_whenSendDeleteRequest() throws Exception {
        //given
        long userId = 1;

        //when
        this.mockMvc.perform(delete("/user/" + userId))
                .andExpect(status().isOk());

        //then
        List<User> users = this.repository.findAll();
        Assertions.assertEquals(0, users.size());
    }

    private String readResourceAsString(String fileName) throws IOException {
        try (InputStream resourceAsStream = readResourceAsStream(fileName)) {
            byte[] response = IOUtils.toByteArray(resourceAsStream);
            return new String(response);
        }
    }

    private InputStream readResourceAsStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
    }
}
