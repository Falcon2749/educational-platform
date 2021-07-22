package com.fuser.educate.repository;

import com.fuser.educate.domain.entity.MainUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MainUserRepository extends JpaRepository<MainUser, Long> {
    @Query("select case when count(user) > 0 then true else false end " +
            "from MainUser user where lower(user.email) = :varEmail")
    boolean isExistWithEmail(String varEmail);

    Optional<MainUser> getMainUserByEmail(String email);
}
