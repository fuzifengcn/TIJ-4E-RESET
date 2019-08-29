package annotation;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author fuzifeng
 */
public class AtUnitExample2 {

    public String methodOne() {
        return "This is methodOne";
    }

    public int methodTwo() {
        System.out.println("This is methodTwo");
        return 1;
    }

    @Test
    void assertExample(){
        assert methodOne().equals("This is methodOne");
    }

    @Test
    void failureAssertExample(){
        assert 1 == methodTwo() : "what a surprise";
    }

    @Test
    void  exceptionExample()throws IOException{
        new FileInputStream("nofile.txt");
    }

    @Test
    boolean assertAndReturn(){
        assert methodTwo() == 2 : "methodTwo must equal 2";
        return methodOne().equals("This is methodOne");
    }


    public static void main(String[] args) {
        OSExecute.command("cd target\\production\\TIJ-4E-RESET && java net.mindview.atunit.AtUnit  annotation\\AtUnitExample2");
    }

}
