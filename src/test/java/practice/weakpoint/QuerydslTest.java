package practice.weakpoint;


import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import practice.weakpoint.domain.member.entity.Authority;
import practice.weakpoint.domain.member.entity.Member;
import practice.weakpoint.domain.member.QMember;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class QuerydslTest {

    @Autowired
    EntityManager em;

    @BeforeEach
    public void before() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        Member newMember = Member.builder()
                .username("a8118199")
                .password("4637wlsdud")
                .nickname("wee")
                .name("jinyoung")
                .authority(Authority.ROLE_USER)
                .build();
        em.persist(newMember);
    }

    @Test
    @DisplayName("Test")
    void createQ() {
        //Given
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QMember m = QMember.member;
        //When
        Member findMember = jpaQueryFactory
                .select(m)
                .from(m)
                .where(m.username.eq("a8118199"))
                .fetchOne();
        //Then
        assertThat(findMember.getUsername()).isEqualTo("a8118199");
    }
}
