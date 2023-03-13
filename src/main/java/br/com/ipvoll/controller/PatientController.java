package br.com.ipvoll.controller;

import br.com.ipvoll.patient.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public void registerPatient(@RequestBody @Valid PatientDTO patientDTO) {
        patientRepository.save(new Patient(patientDTO));
    }

    @GetMapping
    public Page<PatientListDTO> listAllPatients(@PageableDefault(size=10, sort={"name"}) Pageable pagination) {
        return patientRepository.findAllByActiveTrue(pagination).map(PatientListDTO::new);
    }

    @PutMapping
    @Transactional
    public void updatePatient(@RequestBody @Valid PatientDTOUpdate patientDTOUpdate) {
        var patient = patientRepository.getReferenceById(patientDTOUpdate.id());
        patient.updateInfo(patientDTOUpdate);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletePatient(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.delete();
    }

}
