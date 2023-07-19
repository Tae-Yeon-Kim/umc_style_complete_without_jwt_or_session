package com.example.demo.dto;

import com.example.demo.entity.MemberEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO {
    private Long memberLoginId;
    private String id,pw,name,nickname;

    // LocalDate가 날짜만 저장!
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul") //날짜 포멧 바꾸기
    private LocalDate birth;


    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();

        //memberDTO.setMemberLoginId(memberEntity.getMemberLoginId());
        memberDTO.setId(memberEntity.getId());
        memberDTO.setPw(memberEntity.getPw());
        memberDTO.setName(memberEntity.getName());
        memberDTO.setNickname(memberEntity.getNickname());
        memberDTO.setBirth(memberEntity.getBirth());

        return memberDTO;

    }



}
