package com.fuser.educate.domain.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MainUserRequest {
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String email;
    @NotNull
    private Integer ecoin;
    @NotNull
    private Integer xp;
}
