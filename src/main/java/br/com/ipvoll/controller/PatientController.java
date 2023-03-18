package br.com.ipvoll.controller;

import br.com.ipvoll.domain.patient.Patient;
import br.com.ipvoll.domain.patient.PatientListDTO;
import br.com.ipvoll.domain.patient.PatientRepository;
import br.com.ipvoll.domain.patient.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
@SecurityRequirement(name="bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<PatientDTODetaild> registerPatient(@RequestBody @Valid PatientDTO patientDTO, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(patientDTO);
        patientRepository.save(patient);

        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientDTODetaild(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListDTO>> listAllPatients(@PageableDefault(size=10, sort={"name"}) Pageable pagination) {
        var page = patientRepository.findAllByActiveTrue(pagination).map(PatientListDTO::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PatientDTODetaild> updatePatient(@RequestBody @Valid PatientDTOUpdate patientDTOUpdate) {
        var patient = patientRepository.getReferenceById(patientDTOUpdate.id());
        patient.updateInfo(patientDTOUpdate);

        return ResponseEntity.ok(new PatientDTODetaild(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Patient> deletePatient(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTODetaild> getOnePatient(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);

        return ResponseEntity.ok().body(new PatientDTODetaild(patient));
    }
}
