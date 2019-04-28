
package com.ev.annoation;

import com.ev.eunm.SysLogModelType;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Action {
    String description() default "无";
    boolean isEnable() default true;
    SysLogModelType modelName() default SysLogModelType.NULL;
}
