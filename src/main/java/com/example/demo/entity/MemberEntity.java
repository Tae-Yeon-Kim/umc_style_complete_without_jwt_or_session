package com.example.demo.entity;

import com.example.demo.dto.MemberDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "member_table_07_19")
public class MemberEntity {

    @Id//pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberLoginId;

    @Column(unique = true)
    private String id;

    @Column
    private String pw;

    @Column
    private String name;

    @Column(unique = true) //unique 제약조건 추가
    private String nickname;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul") //날짜 포멧 바꾸기
    private LocalDate birth;



    public static MemberEntity toMemberEntity(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        MemberEntity memberEntity = new MemberEntity();
        //memberEntity.setMemberLoginId(memberDTO.getMemberLoginId());
        memberEntity.setId(memberDTO.getId());
        memberEntity.setPw(passwordEncoder.encode(memberDTO.getPw()));
        memberEntity.setName(memberDTO.getName());
        memberEntity.setNickname(memberDTO.getNickname());
        memberEntity.setBirth(memberDTO.getBirth());
        return memberEntity;
    }




}


