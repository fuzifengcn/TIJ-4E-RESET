package annotation.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据库字符串类型
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
    // 字段所占长度
    int value() default 0;
    // 字段名称
    String name() default "";
    // 字段约束
    Constraints constraints() default @Constraints;
}
