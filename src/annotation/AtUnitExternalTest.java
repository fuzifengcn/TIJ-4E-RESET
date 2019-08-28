package annotation;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

/**
 * @author fuzifeng
 * created at 2019-08-28
 */
public class AtUnitExternalTest extends AtUnitExample1 {

    @Test
    boolean _methodOne(){
        return "This is methodOne".equals(methodOne());
    }

    @Test
    boolean _methodTwo(){
        return (2==methodTwo());
    }

    public static void main(String[] args) {
        OSExecute.command("cd target\\production\\TIJ-4E-RESET && java net.mindview.atunit.AtUnit  annotation\\AtUnitExternalTest");
    }
}
