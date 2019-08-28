package annotation;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * 注解处理器工厂
 *  Exec cmd {apt -factory annotation.InterfaceExtractorProcessorFactory -cp ..\..\target\production\TIJ-
 * 4-RESET -s ./apt Multiplier.java}
 * @author fuzifeng
 * created at 2019-08-27
 */
public class InterfaceExtractorProcessorFactory implements AnnotationProcessorFactory {

    @Override
    public Collection<String> supportedOptions() {
        return Collections.emptySet();
    }

    @Override
    public Collection<String> supportedAnnotationTypes() {
        return Collections.singleton("annotation.ExtractInterface");
    }

    @Override
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> set,
                                               AnnotationProcessorEnvironment annotationProcessorEnvironment) {
        return new InterfaceExtractorProcessor(annotationProcessorEnvironment);
    }
}
