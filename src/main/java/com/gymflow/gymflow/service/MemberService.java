package com.gymflow.gymflow.service;

import com.gymflow.gymflow.dto.MemberResponseDto;
import com.gymflow.gymflow.entity.Member;
import com.gymflow.gymflow.entity.User;
import com.gymflow.gymflow.exception.ResourceNotFoundException;
import com.gymflow.gymflow.repository.MemberRepo;
import com.gymflow.gymflow.repository.UserRepo;
import org.modelmapper.ModelMapper;
import com.gymflow.gymflow.dto.MemberRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private  MemberRepo memberRepo;
    @Autowired
    private UserRepo userRepo;


    public MemberResponseDto addMember(MemberRequestDto memberRequestDto){
        Member member = modelMapper.map(memberRequestDto, Member.class);
        Member savedMember= memberRepo.save(member);
        MemberResponseDto responseDto =
                modelMapper.map(savedMember, MemberResponseDto.class);

        return responseDto ;

    }
    public List<MemberResponseDto>  getallMember(){
        List<Member> member=memberRepo.findAll();
         List<MemberResponseDto> showall= member.stream().map(m-> modelMapper.map(m,MemberResponseDto.class)).toList();
        return showall;
    }
    public MemberResponseDto getMemberById( Long id ){
        Member member= memberRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Member not found with id : " + id));
        MemberResponseDto show= modelMapper.map(member,MemberResponseDto.class);
        return show;

    }
    public MemberResponseDto Updatemember(MemberRequestDto memberRequestDto, Long id){
        Member member1 = memberRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Member not found with id : " + id));
            member1.setName(memberRequestDto.getName());
            member1.setEmail(memberRequestDto.getEmail());
            member1.setPhone(memberRequestDto.getPhone());
            member1.setMembershipType(memberRequestDto.getMembershipType());
        member1.setDailyCalorieGoal(memberRequestDto.getDailyCalorieGoal());   // naya
        member1.setDailyProteinGoal(memberRequestDto.getDailyProteinGoal());
        Member safemember= memberRepo.save(member1);
            MemberResponseDto memberResponseDto1= modelMapper.map(safemember,MemberResponseDto.class);

        return  memberResponseDto1;

    }
    public MemberResponseDto deleteMember(Long id ){
        Member member = memberRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Member not found with id : " + id));
            MemberResponseDto memberResponseDto= modelMapper.map(member,MemberResponseDto.class);
        memberRepo.delete(member);
        return  memberResponseDto;

    }
    public MemberResponseDto getMemberByUserEmail(String username) {

        User user = userRepo.findByusername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Member member = user.getMember();

        if (member == null) {
            throw new ResourceNotFoundException("Member profile not found");
        }

        return modelMapper.map(member, MemberResponseDto.class);
    }
}
