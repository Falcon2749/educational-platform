package com.fuser.educate.service;

import com.fuser.educate.domain.dto.MainUserDto;
import com.fuser.educate.domain.entity.MainUser;
import com.fuser.educate.exceptions.UserAlreadyExistsException;
import com.fuser.educate.repository.MainUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MainUserService implements UserDetailsService{

    @Autowired
    private final PasswordEncoder passwordEncoder;
    private final MainUserRepository mainUserRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public void register(MainUserDto mainUserDto){
        if (isExist(mainUserDto)){
            throw new UserAlreadyExistsException(" Користувач з даним email вже існує! ");
        }

        MainUser mainUser = modelMapper.map(mainUserDto, MainUser.class);

        String encodedPassword = passwordEncoder.encode(mainUserDto.getPassword());
        mainUser.setPassword(encodedPassword);

        mainUserRepository.save(mainUser);
    }

    private boolean isExist(MainUserDto mainUserDto){
        return mainUserRepository.isExistWithEmail(mainUserDto.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return mainUserRepository
                .getMainUserByEmail(email)
                .orElseThrow(() ->
                        new BadCredentialsException(String.format(" Невірний логін(%s). ", email)));
    }
}
