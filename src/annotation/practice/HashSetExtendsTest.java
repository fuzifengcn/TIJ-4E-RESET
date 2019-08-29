package annotation.practice;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

import java.util.HashSet;

public class HashSetExtendsTest extends HashSet<String> {

    @Test
    void initialization() {
        assert isEmpty();
    }
    @Test
    void _contains(){
        add("one");
        assert  contains("one");
    }

    @Test
    void _remove(){
        add("one");
        remove("one");
        assert isEmpty();
    }
    public static void main(String[] args) {
        OSExecute.command("cd target\\production\\TIJ-4E-RESET && java net.mindview.atunit.AtUnit  annotation\\practice\\HashSetExtendsTest");
    }



}
