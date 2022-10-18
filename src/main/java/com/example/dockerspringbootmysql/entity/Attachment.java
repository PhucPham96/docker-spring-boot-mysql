package com.example.dockerspringbootmysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "attachment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Attachment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "path")
    private String path;
}
