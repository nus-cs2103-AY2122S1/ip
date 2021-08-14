public class Storage {

    private String[] data;
    private int pos;

    public Storage() {
        data = new String[100];
        pos = 0;
    }

    public String add(String action) {
        data[pos] = action;
        pos++;
        return "added: " + action;
    }

    public String[] getStorage() {
        String[] temp = new String[pos];
        for(int x = 0; x < pos; x++){
            String list_pos = Integer.toString(x+1);
            temp[x] = list_pos + ". " + data[x];
        }
        return temp;
    }

}
