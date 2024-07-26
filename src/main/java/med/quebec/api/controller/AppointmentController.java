package med.quebec.api.controller;

import jakarta.validation.Valid;
import med.quebec.api.domain.appointment.AppointmentsSchedule;
import med.quebec.api.domain.appointment.ScheduleAppointmentData;
import med.quebec.api.domain.appointment.DetailingAppointmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appointment")
public class AppointmentController {

    @Autowired
    private AppointmentsSchedule schedule;

    @PostMapping
    @Transactional
    public ResponseEntity postAppointment(@RequestBody @Valid ScheduleAppointmentData data){

        schedule.schedule(data);

        return ResponseEntity.ok(new DetailingAppointmentData(null, null, null, null));
    }

}
