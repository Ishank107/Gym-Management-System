package com.in.GymManagementSystem.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Builder.Default
    private boolean active = true;

    @ManyToMany
    @JoinTable(
        name = "member_trainer",
        joinColumns = @JoinColumn(name = "member_id"),
        inverseJoinColumns = @JoinColumn(name = "trainer_id")
    )
    @Builder.Default
    private Set<Trainer> trainers = new HashSet<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Attendance> attendances = new HashSet<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Payment> payments = new HashSet<>();
}