package vn.duongvct.test.epl_app.domain;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import vn.duongvct.test.epl_app.constant.GenderEnum;
import vn.duongvct.test.epl_app.util.SecurityUtil;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @NotBlank(message = "Please enter your email")
    private String email;

    @NotBlank(message = "Please enter your password")
    private String password;

    private int age;


    @Enumerated(value = EnumType.STRING)
    private GenderEnum gender;

    private String address;
    private String nation;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String refreshtoken;

    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;


    @PrePersist
    public void handleBeforeCreate() {
        this.createdBy = SecurityUtil.getCurrentUserLogin().isPresent() == true
                ? SecurityUtil.getCurrentUserLogin().get()
                : "";
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updatedBy = SecurityUtil.getCurrentUserLogin().isPresent() == true
                ? SecurityUtil.getCurrentUserLogin().get()
                : "";

        this.updatedAt = Instant.now();
    }

}
