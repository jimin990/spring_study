package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repsoitory.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Junit5에서는 @runWith 이 없어졌다.
 * 이제 spring boot에서 사용할 때에는 @springBootTest만 작성을 해도 동작이 된다.
 * 만약 특정 확장을 명시하고 싶다면 @ExtendWith()을 사용할 수 있다.
 */
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired private MemberRepository memberRepository;
    @Autowired private MemberService memberService;

    @Test
    public void 회원가입() {
        //given
        Member member = new Member();
        member.setName("name");
        //when
        Long id = memberService.join(member);
        //then
        assertThat(member).isEqualTo(memberRepository.findOne(id));

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("name");
        member2.setName("name");
        //when
        /**
         * Junit5에서는 @Test에 expected가 사라졌기 때문에 assertThrows를 사용해야한다.
         * 람다식을 이용하면 깔끔하게 코드를 정리할 수 있다.
         */
        // when
        Executable executable = () -> {
            memberService.join(member1);
            memberService.join(member2);
        };

        // then
        IllegalStateException exception = assertThrows(IllegalStateException.class, executable);
        assertEquals("이미 존재하는 회원입니다.", exception.getMessage());
    }


}