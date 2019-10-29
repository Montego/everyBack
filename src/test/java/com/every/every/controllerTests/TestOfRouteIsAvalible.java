package com.every.every.controllerTests;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
public class TestOfRouteIsAvalible {
    private MockMvc mockMvc;

//    @Test
//    public void isTreeStoreGetAllByLevelAvalible() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/wrong/route")
//                .accept(MediaType.ALL))
//                .andExpect(status().isForbidden());
//    }



}
