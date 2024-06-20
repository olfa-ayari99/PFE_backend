package com.exalead.derangement_pfe.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    AOT_READ("aot:read"),
    AOT_UPDATE("aot:update"),
    AOT_CREATE("aot:create"),
    AOT_DELETE("aot:delete"),
    ASC_READ("asc:read"),
    ASC_UPDATE("asc:update"),
    ASC_CREATE("asc:create"),
    ASC_DELETE("asc:delete"),



            ;

    @Getter
    private final String permission;
}
