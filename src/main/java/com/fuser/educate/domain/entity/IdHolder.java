package com.fuser.educate.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class IdHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
