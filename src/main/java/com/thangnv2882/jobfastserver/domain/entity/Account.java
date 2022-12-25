package com.thangnv2882.jobfastserver.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thangnv2882.jobfastserver.application.constants.AccountType;
import com.thangnv2882.jobfastserver.domain.entity.base.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account extends AbstractAuditingEntity {

    private String email;

    @JsonIgnore
    private String password;

    private AccountType accountType;

    @Nationalized
    private String name;

    @Nationalized
    private String about;

    @Nationalized
    private String address;

    private String phoneNumber;

    private String avatar;

    @Nationalized
    private String jobPosition;

    private String birthday;

    @Nationalized
    private Integer experience;

    @Nationalized
    private String gender;

    private boolean enable = false;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
    @JsonIgnore
    private Set<JobDetail> jobDetails = new HashSet<>();

//    link to table Role
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    //phải để EAGER k thì sẽ lỗi "could not initialize proxy – no Session"
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "account_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();

//    link to table Rate
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
    @JsonIgnore
    private Set<Rate> Rate = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
    @JsonIgnore
    private Set<CV> CVs = new HashSet<>();

    public Account(String email, String password, String name, Set<Role> roles, boolean enable) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.roles = roles;
        this.enable = enable;
    }

}
