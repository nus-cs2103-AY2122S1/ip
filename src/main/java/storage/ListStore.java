package storage;

import java.io.*;
import java.util.ArrayList;

public class ListStore<T> {

    private String storeFilePath;

    public ListStore(String storeFilePath) {
        this.storeFilePath = storeFilePath;
        try {
            File storeFile = new File(storeFilePath);
            storeFile.createNewFile();
        } catch (IOException e) {
//            Session.output("We Are Trying To Save To A Wrong File.");
            // io output that it's wrong file
        }
    }

    public void saveListToFile(ArrayList<T> items) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.storeFilePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<T> getListFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream(this.storeFilePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            @SuppressWarnings("unchecked")
            // Casting justified here since we write to file with same object type (ideally_
            ArrayList<T> items = (ArrayList<T>) objectInputStream.readObject();

            return items;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
