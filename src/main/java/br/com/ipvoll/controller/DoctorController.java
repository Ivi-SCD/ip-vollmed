package br.com.ipvoll.controller;


import br.com.ipvoll.doctor.Doctor;
import br.com.ipvoll.doctor.DoctorDTO;
import br.com.ipvoll.doctor.DoctorListDTO;
import br.com.ipvoll.doctor.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void registerDoctor(@RequestBody @Valid DoctorDTO doctorDTO) {
        doctorRepository.save(new Doctor(doctorDTO));
    }

    @GetMapping
    public Page<DoctorListDTO> listAllDoctors(@PageableDefault(size=10, sort={"name"}) Pageable pagination) {
        return doctorRepository.findAll(pagination).map(DoctorListDTO::new);
    }
}
