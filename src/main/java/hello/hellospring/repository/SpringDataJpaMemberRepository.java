package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// spring data jpa는 japRepository(data jpa의 자체 기능) 를 상속받고 그 안에 들어가는 get,set 객체인 member 하고 pk를 설정해주면 된다.
// 또한 MemberRepoistory 인터페이스를 상속함으로써 자동으로 기능을 구현해준다.
// 즉 JpaRepository를 기준으로 해서 MemberRepository의 인터페이스를 기반으로 repository 기능을 구현한다 라고 생각하면 된다.

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> , MemberRepository  {

    // spring daata jpa 는 인터페이스 함수 이름을 형식에 맞게 설정해주면 오버라이딩 하면 저절로 쿼리를 짜서 기능을 등록해줌 .
    @Override
    Optional<Member> findByName(String name);
}
//SpringDataJpa 는 자동으로 빈으로 등록된다.