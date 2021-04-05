package hello.hellospring.Controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 의존성 주입은 내부에서 객체를 선언해서 사용하는 것이 아닌 외부에서 객체를 주입하여 사용하는 것이다.
 * 종류로는
 * 1. 필드 주입 :  필드에 직접 주입해줌 하지만 별로 안좋음. 중간에 수정이 불가능함
 * 2. setter 주입 : 주입하려면 public 으로 메소드를 계속 호출할 수 있어서 안좋음. public으로 계속 접근이 돼서 위험함.
 * 3. 생성자 주입 : 이게 제일 좋음. 한번 생성자로 선언되면 끝.
 *
 * 스프링은 컨테이너에서 컴포넌트를 스캔하여 해당하는 스프링 빈을 찾아 의존성을 주입해준다.
 * 한번에 하나의 인스턴스만 생성해주며 따라서 싱글톤이 원칙이다.
 * 대부분 컨트롤러는 그냥 자동으로 넣어주는게 좋다.
 * 자동 설정의 장점은
 * 직접 주입 방식은 SpringConfig에서 설명
 * 하지만 스프링에 등록이 되어있는 빈만 선언이 가능하므로 정형화되지 않은 구조에서는 직접 등록해주어야한다.
 */

@Controller

public class MemberController {

    private final MemberService memberService;

    @Autowired        // 의존성 주입(Dependency injection , 의존적인 요소를 주입하는 것 , 여기서는 컨트롤러가 멤버 서비스에
    // 연결 되면서 컨트롤해야하는 멤버서비스의 객체를 컨트롤에 자동으로 주입.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";

    }
}
