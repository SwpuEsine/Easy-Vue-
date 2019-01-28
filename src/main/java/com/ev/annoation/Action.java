
package com.ev.annoation;

import com.ev.entity.SysLogModelType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Action {
    String description() default "无";
    boolean isEnable() default true;
    SysLogModelType modelName() default SysLogModelType.NULL;
}
