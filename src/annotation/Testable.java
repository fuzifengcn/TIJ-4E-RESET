package annotation;

import net.mindview.atunit.Test;

/**
 * test marker annotation
 * override TIJ-4E code net.mindview.atunit.Testable.java
 * @author fuzifeng1994@gmail.com
 *
 */
public class Testable {
    public void execute(){
        System.out.println("Executing ...");
    }

    // @Test do nothing, so it's a marker annotation
    @Test
    void testExecute(){
        execute();
    }
}
