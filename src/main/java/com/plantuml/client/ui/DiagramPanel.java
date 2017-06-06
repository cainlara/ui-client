package com.plantuml.client.ui;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.plantuml.client.util.FileManager;

public class DiagramPanel extends JPanel {
  private static final long serialVersionUID = -5914568080016362147L;

  private JLabel imageContainerLabel;
  private File previousFile;

  public DiagramPanel() {
    super();
    setLayout(new BorderLayout());
    add(getImageContainerLabel(), BorderLayout.CENTER);
  }

  private JLabel getImageContainerLabel() {
    if (imageContainerLabel == null) {
      imageContainerLabel = new JLabel("");
    }

    return imageContainerLabel;
  }

  public void refreshDiagramImage(final File diagramFile) {
    ImageIcon background = new ImageIcon(diagramFile.getAbsolutePath());

    if (previousFile != null) {
      try {
        FileManager.getInstance().deleteFile(previousFile);
      } catch (IOException e) {
        // Do nothing
      }
    }

    previousFile = diagramFile;

    repaintDiagram(background);
  }

  public File getPreviousFile() {
    return previousFile;
  }

  private void repaintDiagram(final ImageIcon background) {
    getImageContainerLabel().setIcon(null);
    getImageContainerLabel().setIcon(background);
    getImageContainerLabel().setHorizontalAlignment(JLabel.CENTER);
  }
}
