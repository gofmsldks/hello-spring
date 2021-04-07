package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * SERVICE는 비즈니스 처리를 위해 비즈니스 용어에 가깝게 설계한다.
 */

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } // DI : 직접 memberRepository를 new로 선언해주지 않고 외부에서 넣어주는 것 - > 의존성 주입

    /**
     * 회원가입
     *
     * @param member
     * @return
     */
    public long join(Member member) {


        validationDuplicateMember(member);// 중복 회원 검증.

        memberRepository.save(member);
        return member.getId();


    }

    private void validationDuplicateMember(Member member) {
        // 같은 이름이 있는 중복 회원은 금지.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     *
     * @return
     */
    public List<Member> findMembers() {


        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId) {

        return memberRepository.findById(memberId);

    }
}
