package com.exalead.derangement_pfe.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.exalead.derangement_pfe.Entity.Permission.*;

@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),

    AOT(
            Set.of(
                    Permission.AOT_READ,
                    Permission.AOT_UPDATE,
                    Permission.AOT_DELETE,
                    Permission.AOT_CREATE
            )
    ),

    ASC(
            Set.of(
                    Permission.ASC_READ,
                    Permission.ASC_UPDATE,
                    Permission.ASC_DELETE,
                    Permission.ASC_CREATE
            )
    ),

    ADMIN(
            Set.of(
                    Permission.ADMIN_READ,
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_DELETE,
                    Permission.ADMIN_CREATE
                   /* Permission.AOT_READ,
                    Permission.AOT_UPDATE,
                    Permission.AOT_DELETE,
                    Permission.AOT_CREATE,
                    Permission.ASC_READ,
                    Permission.ASC_UPDATE,
                    Permission.ASC_DELETE,
                    Permission.ASC_CREATE*/
            )
    );

    @Getter
    private final Set<Permission> permissions;
    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
