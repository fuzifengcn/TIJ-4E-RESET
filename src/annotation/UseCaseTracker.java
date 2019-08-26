package annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseCaseTracker {

    public static void trackerUseCase(List<Integer> useCase, Class<?> cl){
        for (Method method : cl.getDeclaredMethods()) {
            UseCase annotation = method.getAnnotation(UseCase.class);
            System.out.println("Found Use Case:" + annotation.id() + " " + annotation.description());
            useCase.remove(new Integer(annotation.id()));
        }
        for (Integer useCaseId : useCase) {
            System.out.println("Warning: Missing use case-" + useCaseId);
        }
    }


    // output
    //    Found Use Case:47 Passwords must contain at least one numeric
    //    Found Use Case:49 New passwords can't equal previously used ones
    //    Found Use Case:48 no description
    //    Warning: Missing use case-50
    public static void main(String[] args) {
        ArrayList<Integer> useCase = new ArrayList<>();
        Collections.addAll(useCase,47,48,49,50);
        trackerUseCase(useCase,PasswordUtils.class);
    }
}
