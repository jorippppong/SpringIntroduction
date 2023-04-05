package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();  //멤버 다 찾아줌
        model.addAttribute("members", members);  //Model에 members 다 넣음
        return "members/memberList";
    }

    @PostMapping(value = "/members/new") //postMapping!!
    public String create(MemberForm form) {  //name이 들어오게 한다.
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";  //홈화면으로 보낸다.
    }

}
