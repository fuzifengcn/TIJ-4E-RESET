package annotation;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * ExtractorInterface注解解析器
 *
 * @author fuzifeng
 * created at 2019-08-27
 */
public class InterfaceExtractorProcessor implements AnnotationProcessor {
    /** 私有的静态变量，唯一的赋值方式就是在构造其中进行赋值*/
    private final AnnotationProcessorEnvironment env;

    private final List<MethodDeclaration>  interfaceMethods = new ArrayList<>();

    public InterfaceExtractorProcessor(AnnotationProcessorEnvironment env) {
        this.env = env;
    }

    @Override
    public void process() {
        // TypeDeclaration 相当于反射中的Class类
        for (TypeDeclaration typeDeclaration : env.getSpecifiedTypeDeclarations()) {
            ExtractInterface annotation = typeDeclaration.getAnnotation(ExtractInterface.class);
            if(annotation == null){
                break;
            }
            // 获取类中的public修饰的方法，并且不是静态方法（static）
            for (MethodDeclaration method : typeDeclaration.getMethods()) {
                if(method.getModifiers().contains(Modifier.PUBLIC)
                        && !(method.getModifiers().contains(Modifier.STATIC))){
                    interfaceMethods.add(method);
                }
            }

            // 遍历接口列表生成新的接口
            if(interfaceMethods.size() > 0){
                String interfaceName = annotation.value();
                try {
                    PrintWriter sourceFileWriter = env.getFiler().createSourceFile(interfaceName);
                    sourceFileWriter.println("package " + typeDeclaration.getPackage().getQualifiedName() + ";");
                    // 如果返回值不是基本数据类型 是否需要import
                    sourceFileWriter.println(Modifier.PUBLIC.toString() + interfaceName +" {");
                    for (MethodDeclaration interfaceMethod : interfaceMethods) {
                        // 接口中的方法默认的修饰符是 public abstract 所以将这两个修饰符去掉
                        for (Modifier modifier : interfaceMethod.getModifiers()) {
                            if(modifier.toString().equals(Modifier.PUBLIC.toString())
                                    || modifier.toString().equals(Modifier.ABSTRACT.toString()) ){
                                continue;
                            }
                            sourceFileWriter.print(modifier.toString() +" ");
                        }
                        sourceFileWriter.print(interfaceMethod.getReturnType() +" " + interfaceMethod.getSimpleName() + "(");
                        int countParam = 0;
                        for (ParameterDeclaration parameter : interfaceMethod.getParameters()) {
                            sourceFileWriter.print(parameter.getType() +" " +parameter.getSimpleName());
                            if(++countParam < interfaceMethod.getParameters().size()){
                                sourceFileWriter.print(", ");
                            }else {
                                sourceFileWriter.print(");");
                            }
                        }
                        sourceFileWriter.print("}");
                        sourceFileWriter.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
