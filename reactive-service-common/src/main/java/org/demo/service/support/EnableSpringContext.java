package org.demo.service.support;


import org.demo.service.context.SpringContextUtil;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({SpringContextUtil.class})
public @interface EnableSpringContext {
}
