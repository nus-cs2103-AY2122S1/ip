import java.io.File;
import java.io.FileNotFoundException;


public class FileUpdater {
    File f;

    FileUpdater(File f) throws FileNotFoundException {
        this.f = f;

        if (!f.exists()) {
            throw new FileNotFoundException("file does not exist!");
        }
    }

    public void updateLine(String s){
        String fs = "";
        boolean isCompleted = false;
        if(findFirstSection(cut(s)).equals("1")) isCompleted = true;

        if(s.charAt(0) == 'T'){
            fs = "todo " + (cut(cut(s)));
            Duke.addTodo(fs, isCompleted);
        } else if(s.charAt(0) == 'E'){
            fs = "event " + findFirstSection(cut(cut(s))) + " /at " + (cut(cut(cut(s))));
            Duke.addEvent(fs, isCompleted);
        } else if(s.charAt(0) == 'D'){
            fs = "deadline " + findFirstSection(cut(cut(s))) + " /by " + (cut(cut(cut(s))));
            Duke.addDeadline(fs, isCompleted);
        }
    }


    public String cut(String s) {
        return s.substring(s.indexOf('|') + 2);
    }

    public String findFirstSection(String s){
        return s.substring(0, s.indexOf('|') - 1);
    }


    public static void main(String[] args) {
        File f = new File("data/Duke.txt");
        try {
            FileUpdater fu = new FileUpdater(f);
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

}