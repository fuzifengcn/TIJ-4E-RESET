package annotation;
import net.mindview.atunit.*;
import net.mindview.util.*;

public class AtUnitComposition {

    AtUnitExample1 testObject = new AtUnitExample1();

    @Test
    boolean _methodOne() {
        return testObject.methodOne().equals("This is methodOne");
    }
    @Test
    boolean _methodTwo() {
        return testObject.methodTwo() == 2;
    }
    public static void main(String[] args) throws Exception {
        OSExecute.command(
                "cd target\\production\\TIJ-4E-RESET && java net.mindview.atunit.AtUnit  annotation\\AtUnitComposition");
    }
}
