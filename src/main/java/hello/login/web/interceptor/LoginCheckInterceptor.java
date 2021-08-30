package hello.login.web.interceptor;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Interceptor 는 whitelist 생성 과정 등이 필요 없음.
        // 나중에 registry 가 경로에 대한 편리한 작성을 지원함.
        String requestURI = request.getRequestURI();

        log.info("인증 체크 인터셉터 실행 {}", requestURI);
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자 요청");
            // 로그인으로 redirect
            response.sendRedirect("/login?redirectURL=" + requestURI);
            // 중요 더 이상 인터셉터 진행하지 않겠다는 뜻임
            return false;
        }

        return true;
    }
}
