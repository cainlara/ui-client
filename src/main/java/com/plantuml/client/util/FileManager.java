package com.plantuml.client.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import com.plantuml.client.core.PlantUMLClientException;

public final class FileManager {
  private static final String PNG_EXTENSION = ".png";
  private static final String PUC_EXTENSION = ".puc";
  private static final String TEMP_FOLDER_NAME = "tempUml";

  private static FileManager instance;

  private FileManager() {
    // hide constructor
  }

  public static FileManager getInstance() {
    if (instance == null) {
      instance = new FileManager();
    }

    return instance;
  }

  public File createTemporalFile() throws IOException {
    File temporalFile = null;
    String temporalFolderName = getTemporalFolderName();

    File temporalFolder = new File(temporalFolderName);

    if (!temporalFolder.exists()) {
      temporalFolder.mkdir();
    }

    if (temporalFolder.exists()) {
      Date now = new Date();
      String fileName = "tempFile" + now.getTime() + ".png";
      temporalFile = new File(temporalFolderName + File.separator + fileName);

      if (temporalFile.exists()) {
        temporalFile.delete();
      }

      temporalFile.createNewFile();
    }

    return temporalFile;
  }

  public void cleanTemporalFolder() {
    try {
      deleteFolder(getTemporalFolderName());
    } catch (IOException e) {
      // Do nothing
    }
  }

  public void deleteFolder(final String folderAbsolutePath) throws IOException {
    if (!StringUtils.getInstance().isValid(folderAbsolutePath)) {
      throw new IllegalArgumentException("Invalid folder path");
    }

    File folder2Delete = new File(folderAbsolutePath);
    deleteFolder(folder2Delete);
  }

  public void deleteFolder(final File folder2Delete) throws IOException {
    if (folder2Delete == null) {
      throw new IOException("Folder to delete can not be null.");
    }

    if (!folder2Delete.exists()) {
      throw new IOException("Folder to delete does not exist.");
    }

    if (folder2Delete.isFile()) {
      throw new IOException(folder2Delete.getAbsolutePath() + " is a File.");
    }

    if (folder2Delete.listFiles().length == 0) {
      folder2Delete.delete();
    } else {
      for (File file : folder2Delete.listFiles()) {
        if (file.isDirectory()) {
          deleteFolder(file);
        } else if (file.isFile()) {
          deleteFile(file);
        }
      }

      folder2Delete.delete();
    }
  }

  public void deleteFile(final String fileAbsolutePath) throws IOException {
    if (!StringUtils.getInstance().isValid(fileAbsolutePath)) {
      throw new IllegalArgumentException("Invalid file path");
    }

    File file2delete = new File(fileAbsolutePath);
    deleteFile(file2delete);
  }

  public void deleteFile(final File file2delete) throws IOException {
    if (file2delete == null) {
      throw new IOException("File to delete can not be null.");
    }

    if (!file2delete.exists()) {
      throw new IOException("File to delete does not exist.");
    }

    if (file2delete.isDirectory()) {
      throw new IOException(file2delete.getAbsolutePath() + " is a Folder.");
    }

    file2delete.delete();
  }

  private String getTemporalFolderName() {
    return System.getProperty("user.home") + File.separator + TEMP_FOLDER_NAME;
  }

  public String addPngExtension(final String fileName) throws PlantUMLClientException {
    if (!StringUtils.getInstance().isValid(fileName)) {
      throw new PlantUMLClientException("FileName can not be null nor empty.");
    }

    String fixedFileName = fileName;

    if (!fixedFileName.endsWith(PNG_EXTENSION)) {
      fixedFileName += PNG_EXTENSION;
    }

    return fixedFileName;
  }

  public String addPucExtension(final String fileName) throws PlantUMLClientException {
    if (!StringUtils.getInstance().isValid(fileName)) {
      throw new PlantUMLClientException("FileName can not be null nor empty.");
    }

    String fixedFileName = fileName;

    if (!fixedFileName.endsWith(PUC_EXTENSION)) {
      fixedFileName += PUC_EXTENSION;
    }

    return fixedFileName;
  }

  public void copyFile(final File sourceFile, final File targetFile) throws IOException {
    if (sourceFile == null || !sourceFile.exists()) {
      throw new IOException("Source file (" + sourceFile.getAbsolutePath() + ") does not exist.");
    }

    if (targetFile == null) {
      throw new IOException("Target file can not be null.");
    }

    Path source = sourceFile.toPath();
    Path target = targetFile.toPath();

    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
  }

  public File saveSourceCodeFile(final String umlCode, final String targetFilePath) throws PlantUMLClientException {
    if (!StringUtils.getInstance().isValid(targetFilePath)) {
      throw new PlantUMLClientException("Invalid target path.");
    }

    File targetFile = new File(targetFilePath);
    return saveSourceCodeFile(umlCode, targetFile);
  }

  public File saveSourceCodeFile(final String umlCode, final File targetFile) throws PlantUMLClientException {
    try {
      if (targetFile == null) {
        throw new PlantUMLClientException("Invalid target file.");
      }

      if (!targetFile.exists()) {
        targetFile.createNewFile();
      }

      FileWriter fileWriter = new FileWriter(targetFile);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

      bufferedWriter.write(umlCode);
      bufferedWriter.flush();
      bufferedWriter.close();
    } catch (IOException e) {
      throw new PlantUMLClientException("File could not be saved.");
    }

    return targetFile;
  }

  public String openSourceCodeFile(final File sourceFile) throws PlantUMLClientException {
    StringBuilder builder = new StringBuilder();

    try {
      if (sourceFile == null) {
        throw new PlantUMLClientException("Invalid source file.");
      }

      if (!sourceFile.exists()) {
        throw new PlantUMLClientException("File " + sourceFile.getAbsolutePath() + " does not exist.");
      }

      FileReader fileReader = new FileReader(sourceFile);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String currentLine = null;

      while ((currentLine = bufferedReader.readLine()) != null) {
        builder.append(currentLine);
        builder.append("\n");
      }

      bufferedReader.close();
      fileReader.close();
    } catch (IOException e) {
      throw new PlantUMLClientException("Error reading file " + sourceFile.getAbsolutePath() + "\n" + e.getMessage());
    }

    return builder.toString().trim();
  }

  public File getParentFolder(final String filePath) throws PlantUMLClientException {
    if (!StringUtils.getInstance().isValid(filePath)) {
      throw new PlantUMLClientException("Invalid file path.");
    }

    File file = new File(filePath);

    return getParentFolder(file);
  }

  public File getParentFolder(final File file) throws PlantUMLClientException {
    if (file == null) {
      throw new PlantUMLClientException("File can not be null");
    }

    if (!file.exists()) {
      throw new PlantUMLClientException("File " + file.getAbsolutePath() + " does not exist.");
    }

    return file.getParentFile();
  }

  public String getFileExtension(final File file) throws PlantUMLClientException {
    if (file == null) {
      throw new PlantUMLClientException("File can not be null");
    }

    String filePath = file.getAbsolutePath();

    return getFileExtension(filePath);
  }

  public String getFileExtension(final String filePath) throws PlantUMLClientException {
    String extension = null;

    if (!StringUtils.getInstance().isValid(filePath)) {
      throw new PlantUMLClientException("File path is invalid");
    }

    if (filePath.indexOf(".") < 0) {
      throw new PlantUMLClientException("File pathdoes not contain dot character");
    }

    extension = filePath.substring(filePath.lastIndexOf(".") + 1);

    return extension;
  }
}
