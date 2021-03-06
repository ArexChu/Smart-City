package net.oschina.ecust.improve.detail.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 主键
 * Created by haibin on 2017/5/23.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
 @interface PrimaryKey {
    boolean autoincrement() default true;

    String column();
}
