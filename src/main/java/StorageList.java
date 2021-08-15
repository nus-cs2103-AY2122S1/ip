import java.util.ArrayList;

public class StorageList {
    private final ArrayList<String> storageList = new ArrayList<>();

    public StorageList(){};
    public void add(String text){
        storageList.add(text);
    }

    public void display(){
        for(int i=0; i<storageList.size(); i++){
            int num = i+1;
            System.out.println(num + ". " + storageList.get(i));
        }
    }
    public void clear(){
        storageList.clear();
    }
}
