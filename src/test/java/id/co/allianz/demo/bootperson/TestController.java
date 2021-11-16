package id.co.allianz.demo.bootperson;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.co.allianz.demo.bootperson.model.Person;
import id.co.allianz.demo.bootperson.service.PersonService;

@WebMvcTest
public class TestController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService mockService;

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testPerson() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        Person person = new Person();
        person.setId(5);
        person.setFullname("Test Orang");
        person.setDob(formatter.parse("1990-01-01"));

        Mockito.when(mockService.create(ArgumentMatchers.any())).thenReturn(person);

        String jsonContent = mapper.writeValueAsString(person);
        mockMvc.perform(
                post("/person").content(jsonContent).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.fullname").value("Test Orang")).andExpect(jsonPath("$.dob").value("1990-01-01"))
                .andDo(print());
        verify(mockService, times(0)).create(person);
    }
}
