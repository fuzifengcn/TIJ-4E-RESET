package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作用是将类中的public方法提取出来，生成一个新的接口
 * @author fuzifeng
 * created at 2019-08-27
 */
// ElementType.TYPE 使用在接口 类 枚举上
@Target(ElementType.TYPE)
// 源码级别 操作源码
@Retention(RetentionPolicy.SOURCE)
public @interface ExtractInterface {
    String value();
}
