package service;

import filenormal.EnhancedFile;

import java.io.File;

public class FileInformationService {
    public static String getFileLength(String filepath){
        return getFileLength(new EnhancedFile(filepath));
    }
    public static String getFileLength(File file){
        return getFileLength(new EnhancedFile(file));
    }
    public static String getFileLength(EnhancedFile enhancedFile){
        return enhancedFile.getSize();
    }
}
