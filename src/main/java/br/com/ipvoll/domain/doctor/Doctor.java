package br.com.ipvoll.domain.doctor;

import br.com.ipvoll.domain.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "doctors")
@Entity(name="Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phonenumber;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    private boolean active;

    public Doctor(DoctorDTO doctorDTO) {
        this.active = true;
        this.name = doctorDTO.name();
        this.email = doctorDTO.email();
        this.phonenumber = doctorDTO.phonenumber();
        this.crm = doctorDTO.crm();
        this.specialty = doctorDTO.specialty();
        this.address = new Address(doctorDTO.address());
    }

    public void updateInfo(DoctorDTOUpdate doctorDTOUpdate) {
        if(doctorDTOUpdate.name() != null) {
            this.name = doctorDTOUpdate.name();
        }
        if(doctorDTOUpdate.phonenumber() != null) {
            this.phonenumber = doctorDTOUpdate.phonenumber();
        }
        if(doctorDTOUpdate.address() != null) {
            this.address.updateInfo(doctorDTOUpdate.address());
        }
    }

    public void delete() {
        this.active = false;
    }
}
