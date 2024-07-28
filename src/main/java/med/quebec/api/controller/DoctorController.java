package med.quebec.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.quebec.api.domain.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity postDoctor(@RequestBody @Valid MedicalRegistrationData data, UriComponentsBuilder uriBuilder){
        var doctor = new Doctor(data);
        repository.save(doctor);

        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicalDetailData(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<MedicalListData>> getDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination){
        var page = repository.findAllByActiveTrue(pagination).map(MedicalListData::new);
        return ResponseEntity.ok(page) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalDetailData> getDoctor(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new MedicalDetailData(doctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid MedicalUpdateData data){
        var doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);
        return ResponseEntity.ok(new MedicalDetailData(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        //repository.deleteById(id); we changed to logical delete using column active
        var doctor = repository.getReferenceById(id);
        doctor.inactivate();

        return ResponseEntity.noContent().build();
    }

}
