package gnosis.place;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gnosis.model.Place;


public class PlaceStorageManager {

    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/places.csv";
    private static final File FILE = new File(FILE_PATH);
    private static final String DELIMITER = ",";


    /**
     * Loads up Gnosis file to display.
     *
     * @return places Returns a list of places from file.
     */
    public static List<Place> loadGnosisPlaces() {
        //check if user has folder:
        // if no folder -> means no data found -> create one from scratch
        // if folder found -> load to arraylist places
        if (PlaceStorageManager.isDataFileAvail()) {
            return PlaceStorageManager.getTasksFromFile();
        } else {
            PlaceStorageManager.createDataFolder();
            return new ArrayList<>();
        }
    }

    /**
     * Writes Places into file from input.
     *
     * @param places List of places to write to file.
     */
    public static void writePlacesToFile(List<Place> places) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH));
            writer.write("Place visited,Address,DateTime");
            writer.newLine();

            for (Place record: places) {

                String oneLine = record.getName() + DELIMITER
                        + record.getAddress() + DELIMITER
                        + record.getDateVisited();

                writer.write(oneLine);
                writer.newLine();
            }

            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static List<Place> getTasksFromFile() {

        List<Place> places = new ArrayList<>();
        try {
            places = Files.newBufferedReader(Paths.get(FILE_PATH))
                    .lines()
                    .skip(1)
                    .map(PlaceStorageManager::parsePlace)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return places;
    }

    private static Place parsePlace(String s) {
        String[] tokens = s.split(DELIMITER);

        String name = tokens[0];
        String address = tokens[1];
        LocalDateTime dt = LocalDateTime.parse(tokens[2]);
        return new Place(name, address, dt);
    }

    // returns value whether data folder and file was created successfully
    private static boolean createDataFolder() {
        // create folder
        return !FILE.exists() && FILE.getParentFile().mkdir();
    }

    public static boolean isDataFileAvail() {
        return Files.isDirectory(Paths.get(DIRECTORY_PATH)) && FILE.exists();
    }


    /**
     * copy saved tasked file to indicated path to export to.
     *
     * @param pathToExport path to export file to
     */
    public static void copyTaskToPath(File pathToExport) {

        try {

            Files.copy(FILE.toPath(), pathToExport.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
