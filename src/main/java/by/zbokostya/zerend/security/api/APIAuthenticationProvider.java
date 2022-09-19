package by.zbokostya.zerend.security.api;

import by.zbokostya.zerend.dao.impl.ApikeyDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class APIAuthenticationProvider {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ApikeyDao apikeyDao;

    public APIAuthenticationProvider(ApikeyDao apikeyDao) {
        this.apikeyDao = apikeyDao;
    }


    public Authentication authenticate(String token) throws AuthenticationException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(apikeyDao.findByUsername(token).getRole()));
        User principal = new User(token, "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
}
