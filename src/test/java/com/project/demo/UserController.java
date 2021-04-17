package com.project.demo;

import com.project.demo.DTO.UserDTO;
import com.project.demo.entities.Address;
import com.project.demo.services.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        UserDTO userDTO = getUserDTO();

        Mockito.when(userService.register(userDTO))
                .thenReturn(userDTO);

        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.toJson(userDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void should_return_all_users() throws Exception{
        UserDTO userDTO = getUserDTO();

        Set<UserDTO> userDTOS = new HashSet<>();
        userDTOS.add(userDTO);

        Mockito.when(userService.findAll())
                .thenReturn(userDTOS);

        mockMvc.perform(get("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()", Matchers.is(1)))
                .andDo(print());

    }

    @Test
    void should_return_user() throws Exception {
        UserDTO userDTO = getUserDTO();

        Mockito.when(userService.findById(Mockito.anyString()))
                .thenReturn(userDTO);

        mockMvc.perform(get("/api/user/12AB")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is("12AB")))
                .andDo(print());
    }

    private UserDTO getUserDTO() {
        return  UserDTO.builder()
                .id("12AB")
                .age(21)
                .firstName("Hocine")
                .lastName("Kheddadji")
                .email("hocine@gmail.com")
                .address(Address.builder().street("7 run pierre curie").country("FRANCE").build())
                .build();
    }

}
