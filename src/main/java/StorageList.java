import java.util.ArrayList;

public class StorageList {
    private final ArrayList<Task> storageList = new ArrayList<>();

    public StorageList(){};
    public void add(Task text){
        storageList.add(text);
    }

    public void display(){
        System.out.println("Here are the tasks in your list:");
        for(int i=0; i<storageList.size(); i++){
            int num = i+1;
            Task task = storageList.get(i);
            System.out.println(num + "." + task.toString());
        }
    }

    public Task get(int i){
        return storageList.get(i);
    }

    public int size(){
        return storageList.size();
    }

}
