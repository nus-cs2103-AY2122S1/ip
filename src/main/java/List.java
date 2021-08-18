import java.util.ArrayList;

/**
 * List class: Duke HAS-A List
 *
 * @author Timothy Wong Eu-Jin
 * @version Level-2
 */

public class List {

    //Class fields
    private ArrayList<String> array;

    //Constructor
    public List() {
        this.array = new ArrayList<String>();
    }

    //Add method to insert new entry into list
    public void add(String s) {
        this.array.add(s);
        System.out.println("\t" + "added: "  + s);
    }


    //getAll method to return all entries in list
    public void getAll() {
        int count = 1;
        for (String s : this.array) {
            System.out.println("\t" + count + ": " + s);
            count += 1;
        }
    }
}
