package com.plantuml.client.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.plantuml.client.core.PlantUMLClientException;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

public final class DiagramGenerator {
  private static DiagramGenerator instance;

  private DiagramGenerator() {
    // hide constructor
  }

  public static DiagramGenerator getInstance() {
    if (instance == null) {
      instance = new DiagramGenerator();
    }

    return instance;
  }

  public File getDiagramFile(final String umlCode) throws IOException, PlantUMLClientException {
    if (!StringUtils.getInstance().isValid(umlCode)) {
      throw new PlantUMLClientException("UML code can not be null nor empty.");
    }

    FileFormat format = FileFormat.PNG;

    SourceStringReader reader = new SourceStringReader(umlCode);
    File temporalFile = FileManager.getInstance().createTemporalFile();
    FileOutputStream fos = new FileOutputStream(temporalFile);
    reader.generateImage(fos, new FileFormatOption(format, false));

    fos.flush();
    fos.close();

    return temporalFile;
  }
}
