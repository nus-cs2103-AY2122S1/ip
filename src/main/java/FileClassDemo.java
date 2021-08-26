import java.io.File;

public class FileClassDemo {

    public static void main(String[] args) {
        File f = new File("text-ui-test/fruits.txt");
        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        System.out.println("is Directory?: " + f.isDirectory());
    }

}