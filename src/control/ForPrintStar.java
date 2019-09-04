package control;

import static net.mindview.util.Print.print;

public class ForPrintStar {


    public static void main(String[] args) throws InterruptedException {
        for (int x = 0; x < 20; x++){
            for (int y = 0; y > -20 ; y--){
//                System.out.print(" * ");
                System.out.print("("+x+","+y+")");
//                Thread.sleep(50L);
            }
            print();
        }
    }


}
