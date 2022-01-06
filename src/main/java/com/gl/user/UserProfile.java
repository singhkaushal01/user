package com.gl.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
@AllArgsConstructor
public class UserProfile {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String address;
    private String points;
}
