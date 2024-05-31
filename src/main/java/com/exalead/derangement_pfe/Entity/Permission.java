package com.exalead.derangement_pfe.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    AOT_READ("management:read"),
    AOT_UPDATE("management:update"),
    AOT_CREATE("management:create"),
    AOT_DELETE("management:delete"),
    ASC_READ("management:read"),
    ASC_UPDATE("management:update"),
    ASC_CREATE("management:create"),
    ASC_DELETE("management:delete"),



            ;

    @Getter
    private final String permission;
}
