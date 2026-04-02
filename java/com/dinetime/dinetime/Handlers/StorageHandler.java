package com.dinetime.dinetime.Handlers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StorageHandler {
    private final String fileName;

    public StorageHandler(String fileName) {
        this.fileName = "C:/WebAppData/" + fileName;
        new File("C:/WebAppData/").mkdirs(); // Ensure directory exists
    }

    // Save a line to the file
    public void saveLine(String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update a line that matches a search value at a specific index
    public void updateLine(String searchValue, int index, String newLine) {
        String[][] data = readAllLines();
        List<String> updatedLines = new ArrayList<>();
        boolean updated = false;

        for (String[] line : data) {
            if (line.length > index && line[index].equals(searchValue)) {
                updatedLines.add(newLine);
                updated = true;
            } else {
                updatedLines.add(String.join("~", line));
            }
        }

        if (updated) {
            writeAll(updatedLines);
        }
    }

    // Update a line that matches two search values at specific indexes
    public void updateLine(String searchValue1, int index1, String searchValue2, int index2, String newLine) {
        String[][] data = readAllLines();
        List<String> updatedLines = new ArrayList<>();
        boolean updated = false;

        for (String[] line : data) {
            if (line.length > Math.max(index1, index2) &&
                    line[index1].equals(searchValue1) &&
                    line[index2].equals(searchValue2)) {
                updatedLines.add(newLine);
                updated = true;
            } else {
                updatedLines.add(String.join("~", line));
            }
        }

        if (updated) {
            writeAll(updatedLines);
        }
    }


    // Delete a line matching a value at a specific index
    public void deleteLine(String searchValue, int index) {
        String[][] data = readAllLines();
        List<String> updatedLines = new ArrayList<>();
        boolean deleted = false;

        for (String[] line : data) {
            if (line.length > index && line[index].equals(searchValue)) {
                deleted = true; // Skip this line
            } else {
                updatedLines.add(String.join("~", line));
            }
        }

        if (deleted) {
            writeAll(updatedLines);
        }
    }

    public void deleteLine(String searchValue1, int index1, String searchValue2, int index2) {
        String[][] data = readAllLines();
        List<String> updatedLines = new ArrayList<>();
        boolean deleted = false;

        for (String[] line : data) {
            if (line.length > Math.max(index1, index2) &&
                    line[index1].equals(searchValue1) &&
                    line[index2].equals(searchValue2)) {
                deleted = true; // Skip this line
            } else {
                updatedLines.add(String.join("~", line));
            }
        }

        if (deleted) {
            writeAll(updatedLines);
        }
    }


    // Find and return the first matching line by index
    public String[] findLine(String searchValue, int index) {
        String[][] data = readAllLines();
        for (String[] line : data) {
            if (line.length > index && line[index].equals(searchValue)) {
                return line;
            }
        }
        return new String[0];
    }

    // Find a line that matches two search values at specific indexes
    public String[] findLine(String searchValue1, int index1, String searchValue2, int index2) {
        String[][] data = readAllLines();
        for (String[] line : data) {
            if (line.length > Math.max(index1, index2) &&
                    line[index1].equals(searchValue1) &&
                    line[index2].equals(searchValue2)) {
                return line;
            }
        }
        return new String[0];
    }

    // Return all lines in the file
    public String[][] readAllLines() {
        List<String[]> linesList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                linesList.add(line.split("~"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linesList.toArray(new String[0][0]);
    }

    // Get all lines that match a search value at a specific index
    public String[][] getAllLines(String searchValue, int index) {
        List<String[]> matchedLines = new ArrayList<>();
        String[][] data = readAllLines();

        for (String[] line : data) {
            if (line.length > index && line[index].equals(searchValue)) {
                matchedLines.add(line);
            }
        }

        return matchedLines.toArray(new String[0][0]);
    }

    // Helper method to write a list of strings back to the file
    private void writeAll(List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
