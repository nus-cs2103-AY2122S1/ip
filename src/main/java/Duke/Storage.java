package Duke;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



 /**
  * <pre>
  * Stroage class, 
  * this class is to deal with 
  * loading tasks from the file and saving tasks in the file.
  * </pre>
  * @param <String> Filepath path to the data txt file
  */
public class Storage {

    String filepath;

    /**
     * Defult constructor  
     * @param filepath filepath to the data txt file
     */
    public Storage (String filepath){
        this.filepath = filepath;
    }

    /**
     * Method to load data from txt file,
     * returns the arraylist of tring array representation of tasks.
     * @return String array representation of tasks
     */           
    public ArrayList<String[]> loadData() throws FileNotFoundException{
    ArrayList<String[]> res = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(this.filepath))) {
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

    /**
     * Method to save data to txt file,
     * @param tasks String array representation of tasks
     */     
    public void saveData(ArrayList<Task> tasks) throws IOException{
        File fout = new File(this.filepath);
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
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
                Date date = eachtask.getDate();
                output = type + " | " + done + " | " + actionname + " | " + dateFormat.format(date) ;
            }
            bw.write(output);
            bw.newLine();
        }
        bw.close();
    }

}
