package com.yed.glhf.common.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class SessionInitializer extends AbstractHttpSessionApplicationInitializer {

    public SessionInitializer() {
        super(CacheConfig.class);
    }
}
