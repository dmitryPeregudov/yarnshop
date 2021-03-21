package com.kate.yarnshop.entity;

import com.kate.yarnshop.exceptions.EntityNotFoundException;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import static com.kate.yarnshop.constants.Constants.*;

@Entity
@Table(name = ROLE_TABLE)
@Data
public class Role implements GrantedAuthority {

    private static final Role SELLER = new Role(3L, "seller");
    private static final Role ADMIN = new Role(1L, "admin");
    private static final Role CUSTOMER = new Role(2L, CUSTOMER_ROLE);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, unique = true, nullable = false)
    private Long id;
    @Column(name = NAME_ROW, nullable = false)
    private String name;

    protected Role() {
    }

    public static Role getDefaultRole() {
        return CUSTOMER;
    }

    public static Role getInstance(Long id) throws EntityNotFoundException {
        switch (id.intValue()) {
            case 1:
                return ADMIN;
            case 2:
                return CUSTOMER;
            case 3:
                return SELLER;
            default:
                throw new EntityNotFoundException("role");
        }
    }

    private Role(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + getName().toUpperCase();
    }
}
