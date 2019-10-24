package ru.topjava.basejava;

import java.io.File;
import java.util.Objects;

public class MainFile {

    public static void main(String[] args) {
        File path = new File(".//");

        MainFile mf = new MainFile();
        mf.fileWalk(path);
    }

    //http://www.cyberforum.ru/post13277622.html
    private void fileWalk(File file) {
        File[] files = file.listFiles();
        for (File currentFile : Objects.requireNonNull(files)) {
            if (!currentFile.isDirectory()) {
                System.out.println(currentFile.getName());
            }
            if (currentFile.isDirectory()) {
                try {
                    System.out.println(currentFile.getName() + " Is Directory");
                    fileWalk(currentFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
