package praticeServlet.praticeServlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save() {
        Member member1 = new Member("member1", 26);
        Member saveMember = memberRepository.save(member1);
        Assertions.assertThat(member1).isEqualTo(saveMember);
    }

    @Test
    void findById() {
        Member member1 = new Member("member1", 26);
        Member saveMember = memberRepository.save(member1);

        Member findMember = memberRepository.findById(saveMember.getId());
        Assertions.assertThat(saveMember).isEqualTo(findMember);
    }

    @Test
    void findAll() {
        //when
        Member member1 = new Member("member1", 26);
        Member member2 = new Member("member2", 27);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //given
        List<Member> all = memberRepository.findAll();

        //then
        Assertions.assertThat(all.size()).isEqualTo(2);
        Assertions.assertThat(all).contains(member1);
    }

    @Test
    void clearStore() {
    }
}