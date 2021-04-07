package hello.hellospring.domain;

import javax.persistence.*;

@Entity //jpa가 관리하는 엔티티
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // db가 알아서 id를 생성해주는것이 identitiy라고 함.
    private Long id;

    private String name;

    public void setId(Long id) {

        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
