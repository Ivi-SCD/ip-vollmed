package br.com.ipvoll.doctor;

import br.com.ipvoll.address.Address;
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

    public Doctor(DoctorDTO doctorDTO) {
        this.name = doctorDTO.name();
        this.email = doctorDTO.email();
        this.phonenumber = doctorDTO.phonenumber();
        this.crm = doctorDTO.crm();
        this.specialty = doctorDTO.specialty();
        this.address = new Address(doctorDTO.address());
    }
}
