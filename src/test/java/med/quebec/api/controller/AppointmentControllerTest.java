package med.quebec.api.controller;

import med.quebec.api.domain.appointment.AppointmentsSchedule;
import med.quebec.api.domain.appointment.DetailingAppointmentData;
import med.quebec.api.domain.appointment.ScheduleAppointmentData;
import med.quebec.api.domain.doctor.MedicalSpecialty;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ScheduleAppointmentData> scheduleAppointmentDataJson;

    @Autowired
    private JacksonTester<DetailingAppointmentData> detailingAppointmentDataJson;

    @MockBean
    private AppointmentsSchedule appointmentsSchedule;

    @Test
    @DisplayName("Should return HTTP code 400 when information is invalid")
    @WithMockUser
    void postAppointment_scenario1() throws Exception {
        var response = mvc.perform(post("/appointment"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return HTTP code 200 when information is valid")
    @WithMockUser
    void postAppointment_scenario2() throws Exception {

        var date = LocalDateTime.now().plusHours(1);
        var specialty = MedicalSpecialty.CARDIOLOGY;

        var detailingData = new DetailingAppointmentData(null, 1l, 2l, date);

        when(appointmentsSchedule.schedule(any())).thenReturn(detailingData);

        var response = mvc
                .perform(
                        post("/appointment")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(scheduleAppointmentDataJson.write(
                                        new ScheduleAppointmentData(1l, specialty, 2l, date)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonExpected = detailingAppointmentDataJson.write(
                new DetailingAppointmentData(null, 1l, 2l, date)
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }

    @Test
    void deleteAppointment() {
    }
}