package br.com.ipvoll.domain.patient;

import br.com.ipvoll.domain.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "patients")
@Entity(name="Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;
    private String phonenumber;
    private String ssn;
    @Embedded
    private Address address;

    private boolean active;

    public Patient(PatientDTO patientDTO) {
        this.active = true;
        this.name = patientDTO.name();
        this.email = patientDTO.email();
        this.phonenumber = patientDTO.phonenumber();
        this.ssn = patientDTO.ssn();
        this.address = new Address(patientDTO.address());
    }

    public void updateInfo(PatientDTOUpdate patientDTOUpdate) {
        if(patientDTOUpdate.name() != null) {
            this.name = patientDTOUpdate.name();
        }
        if(patientDTOUpdate.phonenumber() != null) {
            this.phonenumber = patientDTOUpdate.phonenumber();
        }
        if(patientDTOUpdate.address() != null) {
            this.address.updateInfo(patientDTOUpdate.address());
        }
    }

    public void delete() {
        this.active = false;
    }
}
