package com.plantuml.client.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import com.plantuml.client.core.PlantUMLClientException;
import com.plantuml.client.message.MessageManager;

public class PUCFileFilter extends FileFilter {
  private static final String PUC_EXTENSION = "puc";

  @Override
  public boolean accept(File f) {
    if (f.isDirectory()) {
      return true;
    }

    try {
      String extension = FileManager.getInstance().getFileExtension(f);

      if (StringUtils.getInstance().isValid(extension)) {
        return extension.equals(PUC_EXTENSION);
      }
    } catch (PlantUMLClientException e) {
      // Ignore exception
    }

    return false;
  }

  @Override
  public String getDescription() {
    return MessageManager.getInstance().getMessage("puc_file_filter-desc");
  }

}
