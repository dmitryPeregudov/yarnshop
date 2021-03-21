package com.kate.yarnshop.validation;

import com.kate.yarnshop.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.kate.yarnshop.constants.Constants.ROLE_CUSTOMER;

@Component
public class SameCustomerValidation {

    public boolean isWrongUser(User user) {
        UserDetails userDetails = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .filter(e -> e instanceof UserDetails)
                .map(e -> (UserDetails) e)
                .orElseThrow(UnsupportedOperationException::new);
        GrantedAuthority authority = userDetails.getAuthorities().stream()
                .findFirst()
                .orElseThrow(UnsupportedOperationException::new);
        String userName = userDetails.getUsername();
        if (authority.getAuthority().equalsIgnoreCase(ROLE_CUSTOMER)) {
            return !user.getLogin()
                    .equalsIgnoreCase(userName) && !user.getEmail()
                    .equalsIgnoreCase(userName);
        }
        return false;
    }
}
