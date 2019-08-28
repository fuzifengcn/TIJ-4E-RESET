package annotation;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;
/**
 * @author fuzifeng
 */
public class AtUnitExample1 {

    public String methodOne() {
        return "This is methodOne";
    }

    public int methodTwo() {
        System.out.println("This is methodTwo");
        return 2;
    }

    @Test
    boolean methodOneTest() {
        return "This is methodOne".equals(methodOne());
    }

    @Test
    boolean methodTwoTest() {
        return (2 == methodTwo());
    }

    @Test
    private boolean m3() {
        return true;
    }

    @Test
    boolean failure() {
        return false;
    }

    @Test
    boolean anotherDisappointment() {
        return false;
    }

    public static void main(String[] args) {
        OSExecute.command("cd target\\production\\TIJ-4E-RESET && java net.mindview.atunit.AtUnit  annotation\\AtUnitExample1");
    }

}
