package com.example.demo.service;


import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor// 이게 있어야 MemberRepository 성공!
public class MemberService {

    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public void save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO, passwordEncoder);
        memberRepository.save(memberEntity);


    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byMemberId = memberRepository.findById(memberDTO.getId());

        if(byMemberId.isPresent()){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            MemberEntity memberEntity =byMemberId.get();
            if(encoder.matches(memberDTO.getPw(), memberEntity.getPw())){
                // 비밀번호 일치!
                // 처음에 시작을 MemberDTO 라 했으니! entity -> dto 로 변환 후 리턴하는 과정이 필요!!
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                System.out.println("현재 MemberService의 login 의 if 첫문장 즉 로그인 성공");
                return  dto;
            }
            else{
                // 비밀번호 불일치!!(로그인 실패!)
                System.out.println(memberEntity);
                System.out.println("현재 MemberService의 login 의 else  즉 로그인 실패");
                return null;
            }

        }
        return null;

    }

    public Optional<MemberEntity> findOne_withID(String insertedUserId) {
        return memberRepository.findById(insertedUserId);
    }

    public Optional<MemberEntity> findOne_withnickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }
}
