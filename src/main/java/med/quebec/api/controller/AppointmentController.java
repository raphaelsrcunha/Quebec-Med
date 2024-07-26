package med.quebec.api.controller;

import jakarta.validation.Valid;
import med.quebec.api.domain.appointment.ScheduleAppointmentData;
import med.quebec.api.domain.appointment.detailingAppointmentData;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appointment")
public class AppointmentController {

    @PostMapping
    @Transactional
    public ResponseEntity postAppointment(@RequestBody @Valid ScheduleAppointmentData data){
        System.out.println(data);
        return ResponseEntity.ok(new detailingAppointmentData(null, null, null, null));
    }

}
