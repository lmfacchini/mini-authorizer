package com.vr.miniauthorizer.domain;


import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;



@Data
@Entity
@Table(name = "CRD_TB")
public class CardVO implements Serializable {


    @Id
    @Column(name = "CRD_ID")
    private Long id;

    @Column(name = "NMBR", nullable = false, unique = true, length = 16)
    private String number;

    @Column(name = "PSSWRD", nullable = false, length = 6)
    private String password;

    @Column(name = "BLNCE", nullable = false, scale = 3, precision = 8)
    private BigDecimal balance;
}
