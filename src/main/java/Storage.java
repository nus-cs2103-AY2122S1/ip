import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

public class Storage {

    private String filepath;

    Storage(String filepath){
        this.filepath = filepath;
    }
    public void writeToFile(Tasklist tasklist) throws IOException {
        try {
            Task.saveTaskList(tasklist.getTasklist(), filepath);

        } catch(IOException ex) {
            throw new IOException();
        }
    }

    public Tasklist fileToTasklist() throws DukeException {
        try {
            File file = new File(System.getProperty("user.dir")+filepath);
            System.out.println(System.getProperty("user.dir")+filepath);
            Tasklist tasklist = new Tasklist();
            if (!file.exists()) {
                file.createNewFile();
                return tasklist;
            } else {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                StringBuffer sb = new StringBuffer();
                String line;

                while((line=br.readLine())!=null){
                    Task temp = Tasklist.parseInput(line);
                    tasklist.addTask(temp);
                }
                return tasklist;
            }


        } catch (NoSuchTaskException | IOException e) {
            throw new DukeException("File is corrupted.");
        }
    }

}
