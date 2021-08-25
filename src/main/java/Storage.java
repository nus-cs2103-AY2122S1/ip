import java.io.*;

public class Storage {

    //path of file containing stored data
    private static final String FILE_PATH = "data/test.txt";
    //path of folder containing data file
    private  static final String DIR_PATH = "data";
    //FIle containing stored data
    private static File dataFile;


    public static void start() {
        //set up the file
        dataFile = new File(FILE_PATH);
        //file does not exist: attempt to create a new file.
        if (!dataFile.canRead()) {
            try {
                new File(DIR_PATH).mkdir();
                if (dataFile.createNewFile()) {
                    System.out.println("A new save file has been created in root/data.");
                }

            } catch (Exception e) {
                System.out.println("An error has occurred when creating your file.");
                System.out.println("Please try running Duke again!");
                return;
            }
        }

        loadData();
    }

    private static void loadData() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                Duke.taskArray.add(DataParser.readData(nextLine));
                Duke.listIndex += 1;

            }
        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Whoops, there is some problem with your file!");
            } else {
                System.out.println("Your save file is corrupted! Did you not take care of it?");
                System.out.println("Try deleting your save file then try again!");
            }

        }

    }

    public static void saveData() {
        try {
            FileWriter writer = new FileWriter(dataFile);
            StringBuilder str = new StringBuilder();
            for (Task task: Duke.taskArray) {
                str.append(task.toDataString()).append("\n");
            }
            writer.write(str.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occured while saving your file.");
        }

    }
}
