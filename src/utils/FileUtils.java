package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    // Read
    public static List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();
        File file = new File(filePath);
        
        // KIEM TRA FILE NEU CO HOAC KO TON TAI
        if (!file.exists()) {return lines;}
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error when read" + filePath + ": " + e.getMessage());
        }
        return lines;
    }
    
    // Wirte
    public static void writeLines(String filePath, List<String> lines) {
        File file = new File(filePath);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error when write" + filePath + ": " + e.getMessage());
        }
    }
}
