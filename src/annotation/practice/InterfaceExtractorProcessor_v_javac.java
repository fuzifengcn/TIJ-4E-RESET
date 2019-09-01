package annotation.practice;

import annotation.ExtractInterface;
import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.ElementType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * ExtractorInterface注解解析器
 *
 * @author fuzifeng
 * created at 2019-08-27
 */
public class InterfaceExtractorProcessor_v_javac extends AbstractProcessor {

    /**
     *
     * @param annotations
     * @param roundEnv 注释处理工具框架将向注释处理器提供实现该接口的对象，使得处理器可以查询关于一轮注释处理的信息。
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(ExtractInterface.class);
        for (Element element : elementsAnnotatedWith) {

            ExtractInterface annotation = element.getAnnotation(ExtractInterface.class);
            Set<Modifier> modifiers = element.getModifiers();
            for (Modifier modifier : modifiers) {
                Class<Modifier> declaringClass = modifier.getDeclaringClass();
                Method[] declaredMethods = declaringClass.getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    if (Modifier.PUBLIC.equals(declaredMethod.getModifiers())){

                    }
                }
            }

        }

        return false;
    }
}
