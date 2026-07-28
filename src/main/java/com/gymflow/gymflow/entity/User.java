package com.gymflow.gymflow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "user_role")
    private Role role;
    private String message;


@OneToOne(mappedBy="user")
    private Member member;

}
