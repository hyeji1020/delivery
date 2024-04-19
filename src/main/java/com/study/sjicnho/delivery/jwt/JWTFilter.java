package com.study.sjicnho.delivery.jwt;

import com.study.sjicnho.delivery.user.entity.UserRole;
import com.study.sjicnho.delivery.user.dto.UserDto;
import com.study.sjicnho.delivery.user.entity.User;
import com.study.sjicnho.delivery.user.service.CustomUserDetails;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 내부 필터에 대한 특정한 내부 구현 진행
        // jwt를 request에서 뽑아서 검증 진행
        // //request에서 Authorization 헤더를 찾음
        String authorization = request.getHeader("Authorization");

        //Authorization 헤더 검증
        if(authorization == null || !authorization.startsWith("Bearer ")){

            System.out.println("token null");
            filterChain.doFilter(request, response);

            //조건에 해당되면 메소드 종료(필수)
            return;
        }
        System.out.println("authorization now");
        //Bearer 부분 제거 후 순수 토큰만 획득
        String token = authorization.split(" ")[1];

        //토큰 소멸 시간 검증
        try {
            jwtUtil.isExpired(token);
        } catch (ExpiredJwtException e) {

            System.out.println("token expired");

            filterChain.doFilter(request, response);
            return;
        }

        //토큰에서 email, role, userId(PK) 획득
        String email = jwtUtil.getUserEmail(token);
        String role = jwtUtil.getRole(token);
        String id = jwtUtil.getId(token);

        //UserEntity를 생성하여 값 set
        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        userDto.setUserId(Integer.valueOf(id));

        //요청 올 때마다 db를 조회하기 때문에 임시 비밀번호 넣기
        userDto.setPassword("password1234!");
        userDto.setUserRole(UserRole.valueOf(role));

        User user = userDto.toEntity();

        //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        //세션에 이용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);

    }
}
