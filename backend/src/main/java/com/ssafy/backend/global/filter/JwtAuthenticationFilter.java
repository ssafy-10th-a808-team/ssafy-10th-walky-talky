package com.ssafy.backend.global.filter;

import com.ssafy.backend.global.error.WTException;
import com.ssafy.backend.global.util.JwtProvider;
import com.ssafy.backend.global.util.RedisDao;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
//@WebFilter(urlPatterns = {"/member/logout", "/member/reIssue"})
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;
    private final RedisDao redisDao;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String requestURI = request.getRequestURI();
        try {
            if ("/api/member/reissue".equals(requestURI)) { // 토큰 재발급 요청
                String rtk = getToken(request.getHeader("Authorization"));

                try {
                    if (rtk != null && jwtProvider.validateToken(rtk)) {
                        Long seq = jwtProvider.getSeq(rtk);
                        request.setAttribute("seq", seq);

                        String token = (String) redisDao.readFromRedis("rtk:" + seq);

                        if (token == null) {
                            throw new WTException("세션이 만료되었습니다.");
                        }
                    } else {
                        throw new WTException("세션이 만료되었습니다.");
                    }
                } catch (Exception e) {
                    throw new WTException("세션이 만료되었습니다.");
                }
            } else { // 그 외의 모든 경우는 atk으로 사용자 검증
                String atk = getToken(request.getHeader("Authorization"));

                try {
                    if (atk != null && jwtProvider.validateToken(atk)) {
                        Long seq = jwtProvider.getSeq(atk);
                        request.setAttribute("seq", seq);

                        String token = (String) redisDao.readFromRedis("atk:" + seq);

                        if (token == null) {
                            throw new WTException("세션이 만료되었습니다.");
                        }
                    } else {
                        throw new WTException("세션이 만료되었습니다.");
                    }
                } catch (Exception e) {
                    throw new WTException("세션이 만료되었습니다.");
                }
            }

        } catch (WTException e) {
            request.setAttribute("message", e.getMessage());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getToken(String header) {
        if (header != null && header.startsWith("Bearer")) {
            return header.substring("Bearer ".length());
        }
        return null;
    }


}