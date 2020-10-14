import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Plz give path:");
        Map<String,ArrayList<File>> mapOfFileAddress = new HashMap<>();
        String folderPath = scanner.nextLine();
        File targetFile = new File(folderPath);
        recursiveDBCOnAllFiles(targetFile, mapOfFileAddress);
        printTheMap(mapOfFileAddress);
    }

    public static void printTheMap(Map<String,ArrayList<File>> map) {
        for (Map.Entry<String, ArrayList<File>> entry : map.entrySet()) {
            System.out.println("Hash : " + entry.getKey() + " | File Name : "
                    + stringOfAnArrayOfFiles(entry.getValue()));
        }
    }

    public static String stringOfAnArrayOfFiles(ArrayList<File> arrayFiles) {
        String output = "";
        for (File file : arrayFiles) {
            output += file.getName()+ " - ";
        }
        return output;
    }

    public static String getFileContent(File targetFile) {
       String fileContent = "We have a problem if this is not deleted by the end of the program.";
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(targetFile.getPath())));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return fileContent;
    }

    public static void dataBaseCreation(File targetFile, Map<String,ArrayList<File>> mapOfFileAddress) throws NoSuchAlgorithmException {
        ShaOneEncryptor encryptor = new ShaOneEncryptor(getFileContent(targetFile));
        String hashOfCurrentFile = encryptor.convertStringToShaOne();
        if (!mapOfFileAddress.containsKey(hashOfCurrentFile))
            mapOfFileAddress.put(hashOfCurrentFile, new ArrayList<>());
        mapOfFileAddress.get(hashOfCurrentFile).add(targetFile);
    }

    public static void recursiveDBCOnAllFiles(File targetFolder, Map<String,ArrayList<File>> mapOfFileAddress) throws NoSuchAlgorithmException {
        for (File currentFile : targetFolder.listFiles()) {
            if (currentFile.isDirectory())
                recursiveDBCOnAllFiles(currentFile, mapOfFileAddress);
            else if(currentFile.isFile() && currentFile.canRead())
                dataBaseCreation(currentFile, mapOfFileAddress);
            else System.out.println("Oh Hi Mark!");
        }
    }
}











