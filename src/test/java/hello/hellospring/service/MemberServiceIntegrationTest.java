package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional  // 테스트를 진행할때 먼저 트랜젝션을 수행하고 끝나면 연기 갱신을 해서 롤백을 해서 테스트에 쓰인 데이터들을 다 삭제

public class MemberServiceIntegrationTest {

    // Test에서는 그냥 필드로 주입해준다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    /**
     * given when then 요소가 중요하다.
     */
    void 회원가입() {
        //given
            Member member = new Member();
            member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();

        assertThat(member.getName()).isEqualTo(findMember.getName());

    }


    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 오류 상황 자체를 체크 하는 것, 오류가 나면 통과됨.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

       /*
        try{
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
             assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원니다.");
        }
        */
    }
    @Test
    void findMembers(){

    }

    @Test
    void findOne(){

    }
}
