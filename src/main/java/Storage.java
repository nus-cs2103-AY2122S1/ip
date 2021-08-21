import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Storage {
    private String path;
    private DukeList list;

    public Storage(String path, DukeList list) {
        this.path = path;
        this.list = list;
    }


    public void save() {
        try {
            FileWriter fileWriter = new FileWriter(this.path);
            fileWriter.write(list.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }


    public void load() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.path));
            String data = bufferedReader.readLine();
            String type;
            String state;
            String body;

            String[] segregate;

            while (data != null) {
                segregate = data.split(" ", 3);

                type = segregate[0];
                state = segregate[1];
                body = segregate[2];

                this.list.loadData(type, state, body);

                data = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
