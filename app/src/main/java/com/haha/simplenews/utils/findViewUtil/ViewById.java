package com.haha.simplenews.utils.findViewUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 格格不入 on 2017/12/11.
 */
@Retention(RetentionPolicy.RUNTIME)//运行时生效
@Target(ElementType.FIELD)//作用于属性
public @interface ViewById {
    int value();
}
