package br.com.ipvoll.controller;

import br.com.ipvoll.domain.appointment.AppointmentDTO;
import br.com.ipvoll.domain.appointment.AppointmentDTODetaild;
import br.com.ipvoll.domain.appointment.AppointmentSchedule;
import br.com.ipvoll.domain.doctor.Specialty;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AppointmentDTO> appointmentDTOJacksonTester;

    @Autowired
    private JacksonTester<AppointmentDTODetaild> appointmentDTODetaildJacksonTester;

    @MockBean
    private AppointmentSchedule appointmentSchedule;

    @Test
    @DisplayName("Must return HTTP Code 400 when the fields are not valid")
    @WithMockUser
    void toScheduleScenaryOne() throws Exception {
        var response = mockMvc.perform(post("/appointment")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Must return HTTP Code 200 when the fields are valid")
    @WithMockUser
    void toScheduleScenaryTwo() throws Exception {

        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm");
        var date = LocalDateTime.now().plusHours(1).format(formatter);

        var specialty = Specialty.CARDIOLOGY;
        var appointmentDetaild = new AppointmentDTODetaild(null, 2L, 5L,LocalDateTime.parse(date, formatter));

        when(appointmentSchedule.toSchedule(any())).thenReturn(appointmentDetaild);

        var response = mockMvc
                .perform(post("/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentDTOJacksonTester.write(new AppointmentDTO(2L, 5L, LocalDateTime.parse(date, formatter), specialty)).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJson = appointmentDTODetaildJacksonTester.write(
                appointmentDetaild
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

}