package com.gym_website.service.utils;

import com.gym_website.dto.UserDto;
import com.gym_website.util.enums.EAppRoles;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Service;

@Service
public class LoginUtilService {
    public static EAppRoles getRole(UserDto userDto) {
        if (EAppRoles.MEMBER.label.matches(userDto.getRole()))
            return EAppRoles.MEMBER;
        return EAppRoles.MEMBER;
    }

    public static Cookie createJwtCookie(String jwt) {
        Cookie cookie = new Cookie("auth-cookie", jwt);
        cookie.setPath("/");
        cookie.setDomain("localhost");
        cookie.setHttpOnly(false);
        return cookie;
    }
}

