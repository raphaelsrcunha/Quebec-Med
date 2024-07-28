package med.quebec.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.quebec.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void postPatient(@RequestBody @Valid PatientRegistrationData data) {
        System.out.println(data);;
        repository.save(new Patient(data));
    }

    @GetMapping
    public Page<PatientListData> getPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return repository.findAllByActiveTrue(pagination).map(PatientListData::new);
    }

    @PutMapping
    @Transactional
    public void updatePatient(@RequestBody @Valid PatientUpdateData data) {
        var patient = repository.getReferenceById(data.id());
        patient.updateData(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletePatient(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.inactivate();
    }

}
