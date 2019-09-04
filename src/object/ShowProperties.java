package object;

public class ShowProperties {

    public static void main(String[] args) {
        System.getProperties().list(System.out);
        System.out.println("+++++++++++++++");
        System.out.println(System.getProperty("user.name"));
        System.out.println("+++++++++++++++");
        System.out.println(System.getProperty("line.separator"));
        System.out.println("+++++++++++++++");
        System.out.print(System.getProperty("java.library.path"));
    }

}
