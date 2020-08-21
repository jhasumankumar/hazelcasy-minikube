package com.sj.partybooking.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER_ADDRESS")
public class UserAddressModel extends AbstractModel implements Serializable {

    @Id
    @SequenceGenerator(name = "address_generator", sequenceName = "address_sequence", allocationSize = 1)
    @GeneratedValue(generator = "address_generator")
    private Long id;

    public String street;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
