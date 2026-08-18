package com.gymflow.gymflow.service;


import com.gymflow.gymflow.dto.LoginRequestDto;
import com.gymflow.gymflow.dto.UserPageResponseDto;
import com.gymflow.gymflow.dto.UserRequestDto;
import com.gymflow.gymflow.dto.UserResponceDto;

import com.gymflow.gymflow.entity.Role;
import com.gymflow.gymflow.entity.User;
import com.gymflow.gymflow.exception.ResourceNotFoundException;
import com.gymflow.gymflow.exception.UserAlreadyExistsException;
import com.gymflow.gymflow.repository.RoleRepo;
import com.gymflow.gymflow.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gymflow.gymflow.entity.Member;
import com.gymflow.gymflow.repository.MemberRepo;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired

    private PasswordEncoder passwordEncoder;
    @Autowired
    private MemberRepo memberRepo;


    public UserResponceDto registration(UserRequestDto userRequestDto) {
        Optional<User> user = userRepo.findByusername(userRequestDto.getUsername());

        if (user.isPresent()) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        Optional<User> user1 = userRepo.findByemail(userRequestDto.getEmail());

        if (user1.isPresent()) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        Optional<Role> memberRole =
                roleRepo.findByRoleNameIgnoreCase("MEMBER");

        if (memberRole.isEmpty()) {
            throw new ResourceNotFoundException("Member role not found");
        }

        Role role = memberRole.get();

        User newuser = modelMapper.map(userRequestDto, User.class);
        newuser.setRole(role);
        newuser.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userRepo.save(newuser);

        Member member = new Member();
        member.setUser(newuser);
        member.setName(newuser.getUsername());
        member.setEmail(newuser.getEmail());

        memberRepo.save(member);


        UserResponceDto userResponceDto =
                modelMapper.map(newuser, UserResponceDto.class);
        userResponceDto.setMessage("User registered successfully");

        return userResponceDto;
    }

    public UserResponceDto login(LoginRequestDto loginRequestDto){
        UserResponceDto userResponceDto=new UserResponceDto();
        Optional<User> user= userRepo.findByusername(loginRequestDto.getUsername());
        if(user.isEmpty()){
            throw new ResourceNotFoundException("Invalid username and password Try again");

        } 

            boolean isvalid;
           isvalid= passwordEncoder.matches(loginRequestDto.getPassword(),
                    user.get().getPassword()
                    );
            if(!isvalid){
                throw new ResourceNotFoundException("Incoorect password");
            }

                UserResponceDto userResponceDto1=modelMapper.map(user.get(),UserResponceDto.class);
                userResponceDto1.setMessage("login successfully");




        return userResponceDto1;
    }

    public UserResponceDto getUserByUsername(String username){

        User user= userRepo.findByusername(username)
                .orElseThrow(()->new ResourceNotFoundException("User doest not exist in the record "));
        UserResponceDto userResponceDto= modelMapper.map(user,UserResponceDto.class);
        userResponceDto.setMessage("user is found");
        return  userResponceDto;

    }
     public UserPageResponseDto getAllusers(Integer PageNUmber,Integer PageSize){
         Pageable pageable= PageRequest.of(PageNUmber,PageSize, Sort.by("username").ascending());
         Page<User> pagedata = userRepo.findAll(pageable);
        List<User> pagedataa= pagedata.getContent();

           List<UserResponceDto> pageresponce=  pagedataa.stream().map(page-> modelMapper.map(page,UserResponceDto.class)).toList();
            UserPageResponseDto  userPageResponseDto= new UserPageResponseDto();
         userPageResponseDto.setContent(pageresponce);
         userPageResponseDto.setTotalPages(pagedata.getTotalPages());
         userPageResponseDto.setTotalElements(pagedata.getTotalElements());
         userPageResponseDto.setPageNumber(pagedata.getNumber());
         userPageResponseDto.setPageSize(pagedata.getSize());
         userPageResponseDto.setFirst(pagedata.isFirst());
         userPageResponseDto.setLast(pagedata.isLast());





        return userPageResponseDto;
     }

         public UserResponceDto deleteuser(Long id){
          User user= userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user not found"));

          UserResponceDto userResponceDto= modelMapper.map(user,UserResponceDto.class);
          userRepo.delete(user);
            return userResponceDto;
         }

         public UserResponceDto updateUser( Long id,UserRequestDto userRequestDto){
            User user= userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(" This user is not existed"));

            user.setUsername(userRequestDto.getUsername());
             user.setEmail(userRequestDto.getEmail());
            userRepo.save(user);
            UserResponceDto userResponceDto = modelMapper.map(user,UserResponceDto.class);

        return userResponceDto;


         }
         public List<UserResponceDto> findUserbyRolename(String roleName){
        List<User> userR= userRepo.findByRoleRoleName(roleName);
        if(userR.isEmpty()){
            throw new ResourceNotFoundException("This role is not in the database");
        }
             List< UserResponceDto> userResponceDtos= userR.stream().map(m-> modelMapper.map(m,UserResponceDto.class)).toList();
            


        return  userResponceDtos;
         }

}




