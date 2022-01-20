package com.webapp;

import java.io.File;

public class MainFile {

    private static void printFilesAndFolders(File file, String space) {
        if (file != null) {
            for (File f : file.listFiles()) {
                if (f.isDirectory()) {
                    System.out.println(space + "[DIR] - " + f.getName());
                    printFilesAndFolders(f, space + "....");
                } else {
                    System.out.println(space + "[FILE] - " + f.getName());
                }
            }
        }
    }

    public static void main(String[] args) {
        File directory = new File("../");
        printFilesAndFolders(directory, "");
    }
}
