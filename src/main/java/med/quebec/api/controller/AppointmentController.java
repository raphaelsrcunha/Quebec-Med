package med.quebec.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.quebec.api.domain.appointment.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointment")
public class AppointmentController {

    @Autowired
    private AppointmentsSchedule schedule;

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity postAppointment(@RequestBody @Valid ScheduleAppointmentData data){
        var dto = schedule.schedule(data);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity deleteAppointment(@RequestBody @Valid CancelingAppointmentData data) {
        schedule.cancel(data);
        return ResponseEntity.noContent().build();
    }

}
