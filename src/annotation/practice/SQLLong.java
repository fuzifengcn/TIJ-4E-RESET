package annotation.practice;

import annotation.database.Constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLLong {

    String name() default "";
    int value() default 0;
    Constraints constraints() default @Constraints;
}
