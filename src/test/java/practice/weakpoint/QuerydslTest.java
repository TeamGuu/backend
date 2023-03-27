package practice.weakpoint;


import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import practice.weakpoint.domain.member.Member;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class QuerydslTest {

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("Test")
    void createQ() {
        //Given
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        //Member member = jpaQueryFactory

        //When

        //Then
    }
}
