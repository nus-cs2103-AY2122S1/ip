import java.io.*;
import java.util.ArrayList;

public class Writer {

    public void fileSaver(ArrayList saveThis) {
        try {
            FileOutputStream fileOut = new FileOutputStream("saveFile");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(saveThis);
            out.flush();
            out.close();
            System.out.println("Saved Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Write failed");
        }
    }

    public ArrayList fileReader() {
        ArrayList result = null;
        File tempFile = new File("saveFile");

        if (tempFile.exists()) {
            try {
                FileInputStream file = new FileInputStream("saveFile");
                ObjectInputStream in = new ObjectInputStream(file);
                result = (ArrayList) in.readObject();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Read failed");
            }
        } else {
            System.out.println("New Save File Created");
        }
        return result;
    }
}
