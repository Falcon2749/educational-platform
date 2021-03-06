package com.fuser.educate.domain.enums;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fuser.educate.domain.enums.MainUserPermissions.*;

@Getter
public enum MainUserRole {
    STUDENT(Set.of(USER_READ, COURCE_READ)),
    TEACHER(Set.of(USER_READ, COURCE_READ, COURCE_WRITE)),
    ADMIN(Set.of(USER_READ, USER_WRITE, COURCE_READ, COURCE_WRITE ));

    private final Set<MainUserPermissions> userPermissions;

    MainUserRole(Set<MainUserPermissions> userPermissionsSet) {
        this.userPermissions = userPermissionsSet;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        this.getUserPermissions()
                .forEach(mainUserPermissions -> {
                    authorities.add(
                            new SimpleGrantedAuthority(mainUserPermissions.getPermission()
                            ));
                });
        return authorities;
    }
}
