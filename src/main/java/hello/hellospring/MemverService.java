package hello.hellospring;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

/**
 * SERVICE는 비즈니스 처리를 위해 비즈니스 용어에 가깝게 설계한다.
 */

public class MemverService {

    private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();


    /** 회원가입
     *
     * @param member
     * @return
     */
    public long join(Member member){

        validationDuplicateMember(member);// 중복 회원 검증.

        memberRepository.save(member);
        return member.getId();

    }

    private void validationDuplicateMember(Member member){

        // 같은 이름이 있는 중복 회원은 금지.
        memberRepository.findByName(member.getName())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers(){
        return  memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
