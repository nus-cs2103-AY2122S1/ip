public class DukeList {
    private String[] list = new String[100];
    private int count = 0;

    public DukeList() {}


    public void add(String text) {
        list[count] = (count + 1) + ". " + text;
        count += 1;
        System.out.println("added: " + text);
    }


    public void list() {
        for (int i = 0; i < count; i++) {
            System.out.println(list[i]);
        }
    }

    
}
