package com.sj.partybooking.domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NamedQuery(name = "findByEmail",
        query = "SELECT u FROM UserModel u WHERE u.email LIKE :email")
@Entity
@Table(name = "USER")
public class UserModel extends AbstractModel implements Serializable {

    @Id
    @SequenceGenerator(name = "person_generator", sequenceName = "person_sequence", allocationSize = 1)
    @GeneratedValue(generator = "person_generator")
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "ID", referencedColumnName = "ID")
    private Set<UserAddressModel> userAddres = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserAddressModel> getUserAddres() {
        return userAddres;
    }

    public void setUserAddres(Set<UserAddressModel> userAddres) {
        this.userAddres = userAddres;
    }

    @Override
    public String toString() {
        return "Person [firstName=" + this.firstName + ", lastName=" + this.lastName + ", email=" + this.email
                + "]";
    }

}

