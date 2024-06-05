//package com.sparta.lunchrecommender.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Getter
//@NoArgsConstructor
//@Table(name = "user")
//public class TmpUser {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long userId;
//
//    @OneToMany(mappedBy = "tmpUser")
//    private List<Post> posts = new ArrayList<>();
//}
