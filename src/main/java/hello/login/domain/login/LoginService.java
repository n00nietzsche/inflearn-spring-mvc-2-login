package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    /**
     * @return null이면 로그인 실패
     */
    public Member login(String loginId, String password) {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);

        if(findMember.isPresent() && findMember.get().getPassword().equals(password)) {
            return findMember.get();
        }

        return null;
    }
}
