package com.project.demo;

import com.project.demo.DTO.UserDTO;
import com.project.demo.entities.Address;
import com.project.demo.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public class UserController {

    @MockBean
    private UserService userService;

        @Autowired
        private MockMvc mockMvc;

    @Test
    void should_return_create_user() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .age(25)
                .firstName("Hocine")
                .lastName("Kheddadji")
                .email("hocine@gmail.com")
                .address(Address.builder().street("7 run pierre curie").country("FRANCE").build())
                .build();

        Mockito.when(userService.register(userDTO))
                .thenReturn(userDTO);

        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.toJson(userDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

}
