package by.zbokostya.zerend.security;

import by.zbokostya.zerend.entity.Apikey;
import by.zbokostya.zerend.entity.User;
import by.zbokostya.zerend.service.impl.ApikeyProjectService;
import by.zbokostya.zerend.service.impl.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserService userService;
    private final ApikeyProjectService apikeyProjectService;

    public DomainUserDetailsService(UserService userService, ApikeyProjectService apikeyProjectService) {

        this.userService = userService;
        this.apikeyProjectService = apikeyProjectService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.startsWith("ApiKey ")) {
            return createSpringSecurityUser(username, apikeyProjectService.findByUsername(username));
        } else {
            return createSpringSecurityUser(username, userService.findUserByUsername(username));
        }
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
        List<GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, Apikey apikey) {
        List<GrantedAuthority> grantedAuthorities = List.of(
                new SimpleGrantedAuthority(apikey.getRole()));
        return new org.springframework.security.core.userdetails.User(lowercaseLogin, "", grantedAuthorities);
    }
}
