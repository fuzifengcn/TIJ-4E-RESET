package annotation;

import net.mindview.atunit.Test;
import net.mindview.atunit.TestObjectCreate;
import net.mindview.util.OSExecute;

import javax.annotation.Resources;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author fuzifeng
 */
public class AtUnitExample3 {


    private int n ;
    public AtUnitExample3(int n){
        this.n = n;
    }
    public int getN(){
        return n;
    }
    public  String methodOne(){
        return "This is methodOne";
    }
    public int methodTwo(){
        System.out.println("This is methodTwo");
        return 2;
    }

    @TestObjectCreate
    static AtUnitExample3 create(){
        return new AtUnitExample3(47);
    }

    @Test
    boolean initialization(){
        return n == 47;
    }

    @Test
    boolean methodOneTest(){
        return "This is methodOne".equals(methodOne());
    }

    @Test
    boolean methodTwoTest(){
        return 2 == methodTwo();
    }


    public static void main(String[] args) {
        OSExecute.command("cd target\\production\\TIJ-4E-RESET && java net.mindview.atunit.AtUnit  annotation\\AtUnitExample3");
    }

}
