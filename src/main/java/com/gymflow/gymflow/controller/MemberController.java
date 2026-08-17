package com.gymflow.gymflow.controller;

import com.gymflow.gymflow.dto.MemberRequestDto;
import com.gymflow.gymflow.dto.MemberResponseDto;
import com.gymflow.gymflow.entity.Member;
import com.gymflow.gymflow.service.MemberService;
import com.gymflow.gymflow.dto.MemberRequestDto;
import com.gymflow.gymflow.dto.MemberResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired

     private MemberService memberService;

    @PostMapping("/add")
    public MemberResponseDto addmember(@Valid @RequestBody MemberRequestDto memberRequestDto){

        return memberService.addMember(memberRequestDto);
    }
    @GetMapping("/showall")
    public List<MemberResponseDto>  getallmember(){
        return memberService.getallMember();
    }
    @GetMapping("{id}")
    public MemberResponseDto  gememberBYId(@PathVariable Long id ){
        return memberService.getMemberById(id);
    }
    @GetMapping("/me")
    public MemberResponseDto getMyMember(Authentication authentication) {
        String username = authentication.getName();
        return memberService.getMemberByUserEmail(username);
    }
    @PutMapping("{id}")
    public MemberResponseDto updatemember(@RequestBody MemberRequestDto memberRequestDto,@PathVariable Long id  ){
        MemberResponseDto member1= memberService.Updatemember(memberRequestDto, id);
        return member1;
    }
    @DeleteMapping("{id}")
    public MemberResponseDto deletemember(@PathVariable Long id  ){
        MemberResponseDto member= memberService.deleteMember(id);
    return member;
    }



}
