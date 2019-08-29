package annotation;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

import java.util.HashSet;

public class HashSetTest {

    HashSet<String> testObject = new HashSet<>();

    @Test
    void initialization() {
        assert testObject.isEmpty();
    }
    @Test
    void _contains(){
        testObject.add("one");
        assert  testObject.contains("one");
    }

    @Test
    void _remove(){
        testObject.add("one");
        testObject.remove("one");
        assert testObject.isEmpty();
    }
    public static void main(String[] args) {
        OSExecute.command("cd target\\production\\TIJ-4E-RESET && java net.mindview.atunit.AtUnit  annotation\\HashSetTest");
    }

}
