package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.MemberEntity;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {


    // 생성자 주입
    @Autowired
    private final MemberService memberService;

    // 아래 PostMApping /member/save 는 데이터베이스에 넣기전일단 memberDTO 확인한느것!
    // 즉 아래는 save함수는 DB에 넣기 위해 사용!
    // 즉 아래는 회원가입!
    @PostMapping("/member/save")
    public boolean save(@RequestBody MemberDTO memberDTO) {
        System.out.println("MemberController.save~!~!~!~!");
        System.out.println("제발.. : " + memberDTO);
        memberService.save(memberDTO);
        boolean save_check = true;
        return save_check;
    }

    //login할때 사용!
    @PostMapping("/member/login")
    public boolean login(@RequestBody MemberDTO memberDTO) {
        System.out.println(memberDTO);
        MemberDTO loginResult = memberService.login(memberDTO);
        boolean check_login_correct;
        if (loginResult != null) {
            //login 성공!!

            check_login_correct = true;
        } else {
            // 실패!
            check_login_correct = false;

        }
        return check_login_correct;
    }



    //아래 만든게 회원가입할때 id 복수 체크 부분
    @GetMapping("/member/save/{user_id}")
    public boolean checkIDExist(@PathVariable String user_id) {

        Optional<MemberEntity> memberEntity_cehckIDExist,memberEntity_checknicknameExist;
        memberEntity_cehckIDExist = memberService.findOne_withID(user_id);
        memberEntity_checknicknameExist = memberService.findOne_withnickname(user_id);
        System.out.println(memberEntity_cehckIDExist);
        boolean check;
        if(memberEntity_cehckIDExist.isPresent() || memberEntity_checknicknameExist.isPresent()) {
            // 이미 db에 존재한다 즉 아이디로 부적절!
            check = true;
        }
        else {
            // db에 아이디가 없어 아이디로 사용가능!
            check = false;
        }
        return check;


    }






}






