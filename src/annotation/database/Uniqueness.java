package annotation.database;

/**
 * 声明一个唯一注解
 */
public @interface Uniqueness {
    // 默认的生成"唯一"注解
    Constraints constraints() default @Constraints(unique = true);
}
