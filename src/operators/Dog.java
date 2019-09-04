package operators;

import java.awt.print.PrinterGraphics;

import static net.mindview.util.Print.print;

public class Dog {
    String name;
    String says;

    public static void main(String[] args) {
        Dog spot = new Dog();
        spot.name = "spot";
        spot.says = "Ruff!";
        Dog scruffy = new Dog();
        scruffy.name = "scruffy";
        scruffy.says = "Wurf!";

        Dog newDog = spot;

        print("Dog " +spot.name + " says " + spot.says);
        print("Dog " +scruffy.name + " says " + scruffy.says);
        print(newDog == spot);
        print(newDog.equals(spot));
    }


}
