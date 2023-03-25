package com.epam.subscription;

import com.epam.user.User;
import com.epam.user.UserRepository;
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
class ServiceControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SubscriptionRepository repository;

    @Autowired
    private UserRepository userRepository;


    @Test
    @Order(1)
    void shouldCreateUserInDatabase_whenSendPostRequest() throws Exception {
        //given
        String requestContent = readResourceAsString("create_subscription.json");
        User user = new User(1L, "John", "Doe", LocalDate.now());
        this.userRepository.save(user);
        System.out.println("subscription  " + requestContent);

        //when
        this.mockMvc.perform(post("/subscription")
                .content(requestContent)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.userId", Matchers.is(1)))
                .andExpect(jsonPath("$.startDate", Matchers.is(LocalDate.now().toString())));

        //then
        Optional<Subscription> savedSubscription = this.repository.findById(1L);
        Assertions.assertTrue(savedSubscription.isPresent());

        Subscription subscription = savedSubscription.get();
        User savedUser = subscription.getUser();
        Assertions.assertEquals(1L, subscription.getId());
        Assertions.assertEquals(LocalDate.now(), subscription.getStartDate());
        Assertions.assertEquals(1L, savedUser.getId());
        Assertions.assertEquals("John", savedUser.getName());
        Assertions.assertEquals("Doe", savedUser.getSurname());
        Assertions.assertEquals(LocalDate.now(), savedUser.getBirthday());
    }

    @Test
    @Order(2)
    void shouldReturnSubscription_whenSendGetRequest() throws Exception {
        //given
        int subscriptionId = 1;

        //when
        this.mockMvc.perform(get("/subscription/" + subscriptionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.userId", Matchers.is(1)))
                .andExpect(jsonPath("$.startDate", Matchers.is(LocalDate.now().toString())));


    }


    @Test
    @Order(3)
    void shouldChangeUserId_whenSendPutRequest() throws Exception {
        //given
        String requestContent = readResourceAsString("update_subscription.json");
        User user = new User(2L, "Michael", "Jordan", LocalDate.now());
        this.userRepository.save(user);

        //when
        this.mockMvc.perform(put("/subscription")
                .content(requestContent)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.userId", Matchers.is(2)));

        //then
        Subscription subscription = this.repository.findById(1L).get();
        User savedUser = subscription.getUser();
        Assertions.assertEquals("Michael", savedUser.getName());
        Assertions.assertEquals("Jordan", savedUser.getSurname());
    }

    @Test
    @Order(4)
    void shouldDeleteSubscription_whenSendDeleteRequest() throws Exception {
        //given
        long subscriptionId = 1;

        //when
        this.mockMvc.perform(delete("/subscription/" + subscriptionId))
                .andExpect(status().isOk());

        //then
        List<Subscription> subscriptions = this.repository.findAll();
        Assertions.assertEquals(0, subscriptions.size());
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
