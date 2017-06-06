package com.test.util;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import com.plantuml.client.core.PlantUMLClientException;
import com.plantuml.client.util.FileManager;

public class FileManagerTests {

  @Test
  public void testGetParentFolder() {
    try {
      String filePath = "/Users/larajo7/Desktop/hey_jude.puc";
      String expected = "/Users/larajo7/Desktop";
      File parentFolder = FileManager.getInstance().getParentFolder(filePath);
      String parentPath = parentFolder.getAbsolutePath();

      assertEquals(expected, parentPath);
    } catch (PlantUMLClientException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testGetFileExtension() {
    try {
      String filePath = "/Users/larajo7/Desktop/hey_jude.puc";
      String expected = "puc";

      String extension = FileManager.getInstance().getFileExtension(filePath);

      assertEquals(expected, extension);
    } catch (PlantUMLClientException e) {
      e.printStackTrace();
    }
  }

}
