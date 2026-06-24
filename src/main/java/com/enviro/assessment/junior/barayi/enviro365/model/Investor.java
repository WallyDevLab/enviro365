package com.enviro.assessment.junior.barayi.enviro365.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "investors")
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;

    @JsonIgnore
    @OneToMany(mappedBy = "investor", fetch = FetchType.EAGER)
    private List<InvestmentProduct> products;
}