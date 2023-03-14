package br.com.ipvoll.controller;


import br.com.ipvoll.domain.doctor.*;
import br.com.ipvoll.domain.doctor.Doctor;
import br.com.ipvoll.domain.doctor.DoctorListDTO;
import br.com.ipvoll.domain.doctor.DoctorRepository;
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
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorDTODetaild> registerDoctor(@RequestBody @Valid DoctorDTO doctorDTO, UriComponentsBuilder uriBuilder) {
        var doctor = new Doctor(doctorDTO);
        doctorRepository.save(doctor);

        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDTODetaild(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListDTO>> listAllDoctors(@PageableDefault(size=10, sort={"name"}) Pageable pagination) {
        var page = doctorRepository.findAllByActiveTrue(pagination).map(DoctorListDTO::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDTODetaild> updateDoctor(@RequestBody @Valid DoctorDTOUpdate doctorDTOUpdate) {
       var doctor = doctorRepository.getReferenceById(doctorDTOUpdate.id());
       doctor.updateInfo(doctorDTOUpdate);

        return ResponseEntity.ok(new DoctorDTODetaild(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Doctor> deleteDoctor(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTODetaild> getOneDoctor(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);

        return ResponseEntity.ok(new DoctorDTODetaild(doctor));
    }
}
