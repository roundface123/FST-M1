package activities;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class Activity14 {
    public static void main(String[] args) throws IOException {
        String fileName = "src/main/java/activities/sample.txt";
        try {
            File file = new File(fileName);
            boolean fStatus = file.createNewFile();
            if(fStatus) {
                System.out.println("File created successfully!");
            } else {
                System.out.println("File already exists at this path.");
            }

            //get the file Object
            File fileUtil = FileUtils.getFile(fileName);
            //Read file
            System.out.println("Data in file: " + FileUtils.readFileToString(fileUtil, "UTF8"));

            //Create directory
            File destDir = new File("src/main/java");
            //Copy file to directory
            FileUtils.copyFileToDirectory(file, destDir);

            //Get file from new directory
            File newFile = FileUtils.getFile(destDir, "sample.txt");
            FileUtils.writeStringToFile(newFile, "testing string", "UTF8");
            //Read data from file
            String newFileData = FileUtils.readFileToString(newFile, "UTF8");
            //Print it
            System.out.println("Data in new file: " + newFileData);
        } catch(IOException errMessage) {
            System.out.println(errMessage);
        }
    }
}
