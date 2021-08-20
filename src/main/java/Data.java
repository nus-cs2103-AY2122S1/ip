import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Data {
    static ArrayList<String[]> loadData(String pathname) throws FileNotFoundException{
    ArrayList<String[]> res = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(pathname))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] splited = line.split("\\|");
            res.add(splited);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return res;
    }

    static void saveData(ArrayList<Task> tasks) throws IOException{
        File fout = new File("out.txt");
        FileOutputStream fos = new FileOutputStream(fout);
     
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
    
        for(Task eachtask : tasks){
            String actionname = eachtask.getActionName();
            boolean compleated = eachtask.getCompleted();
            String type = eachtask.getType();
            String done = "0";
            String output;
 
            if (compleated){
                done = "1";
            }

            if(type.equals("T")){
                output = type + " | " + done + " | " + actionname;
            }else{
                String date = eachtask.toString().split("\\(")[1];
                date = date.substring(3, date.length() - 1 );
                output = type + " | " + done + " | " + actionname + " | " + date;
            }
            bw.write(output);
            bw.newLine();
        }
        bw.close();
    }

}
