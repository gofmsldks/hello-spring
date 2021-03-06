package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.swing.*;
import javax.xml.crypto.Data;

/**
 * 자동으로 등록안해주고 직접 하려면
 * bean을 직접 등록하여 의존성을 주입하면 된다.
 * 과거엔 이걸 xml으로 입력함 하지만 잘 사용 안함.
 * 상황에 따라 구현 클래스를 변경해야 하면 직접 bean을 등록해준다.
 * 여기선 나중에 DB를 바꿔주어야 하므로 직접 bean을 선언해준다.
 */

@Configuration
public class SpringConfig {

    // private DataSource dataSource;
    //private EntityManager em;

    private final MemberRepository memberRepository;

    /**
     * 스프링 컨피그는 이미 컴포넌트 설정을 통해 빈으로 등록되있으니까 의존성을 주입할때 오토 와이어드를 사용해야함.
     * 이것은 JDBC와 JDBCTemplate을 쓸 때 사용하는 생성자
     * @param dataSource
     */
    /**
     @Autowired public SpringConfig(DataSource dataSource){
     this.dataSource = dataSource;
     }
     */

    /**
     * JPA를 쓸때 사용하는 생성자
     * @return
     */
    /*
    @Autowired
    public SpringConfig(EntityManager em){
        this.em = em;
    }

    */

    /**
     * SPRING DATA JPA 사용할때 생성자
     *
     * @param memberRepository Spring Data Jpa를 memberRepository 를 바탕으로 구현해준것이므로 memberRepository를 springconfig에서 의존성 주입을 받고
     *                         이것을 member service 에 의존성 주입해준다.
     */

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository); // wired를 직접 해준다고 생각하면 됨.
    }

    //@Bean
    //public MemberRepository memberRepository() {
    //return new MemoryMemberRepository(); // 구조체
    //return new JdbcMemberRepository(dataSource); // JDBC
    // return new JdbcTemplateMemberRepository(dataSource); //JDBCTEMPLATE
    // return new JpaMemberRepository(em); // JPA

    // }

/**

 @Configuration

 public class SpringConfig {

 @Bean
 public TimeTraceAop timeTraceAop() {
 return new TimeTraceAop();
 }
 } AOP 객체를  따로 빈으로 등록할때 .


 */






}


/*
빈을 설정하는 법 에는 컨피그에 수동으로 어노테이션 넣어서 하는 법이랑 컴포넌트 설정해서 하는 법이 있는데
이때 수동으로 넣을때에는 빈으로 설정을 해주고 의존성을 주입해준다.
컴포넌트를 설정해서 해주면 오토와이어드를 써서 간단하게 의존성을 주입해줄수 있다.

또 의존성을 주입해주는 법에는 아예 수동으로 코드를 하나하나 작성하는 법도 있다.
 */