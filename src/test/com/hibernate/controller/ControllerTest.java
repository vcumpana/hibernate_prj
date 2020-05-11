//package com.hibernate.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.hibernate.entities.Employee;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//@SpringBootTest
//class ControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context)
//                        .build();
//    }
//
//    @Test
//    void addEmployee() {
//
//        Employee employee = new Employee();
//
//        new ObjectMapper().writer().writeValueAsString();
//
//        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/employee");
//        post.contentType(MediaType.APPLICATION_JSON)
//                .content(JsonUtil.toString(fundRequest));
//
//    }
//}