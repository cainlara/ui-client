package com.plantuml.client.ui.action;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import com.plantuml.client.core.PlantUMLClientException;
import com.plantuml.client.message.MessageManager;
import com.plantuml.client.ui.controller.MainFrameController;
import com.plantuml.client.ui.dialog.MessageDialog;
import com.plantuml.client.ui.image.ImageManager;
import com.plantuml.client.util.FileManager;

public class ExportGeneratedImage extends GenericAction {
  private static final long serialVersionUID = 8724274728749143320L;

  private MainFrameController mainController;

  public ExportGeneratedImage(final MainFrameController mainController) {
    this(mainController, true);
  }

  public ExportGeneratedImage(final MainFrameController mainController, boolean addCaption) {
    super(addCaption ? MessageManager.getInstance().getMessage("main_window-file_menu-export_action") : "",
        ImageManager.getInstance().getImageIcon("export_16"),
        MessageManager.getInstance().getMessage("main_window-file_menu-export_action-desc"), addCaption);

    this.mainController = mainController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      File sourceFile = mainController.getMainFrame().getRenderedFile();

      if (sourceFile == null) {
        MessageDialog.getInstance().showErrorMessage(mainController.getMainFrame(),
            MessageManager.getInstance().getMessage("main_window-file_menu-export_action-error_no_file"));
      } else {
        JFileChooser fileChooser = new JFileChooser();
        File homeFolder = new File(System.getProperty("user.home"));
        fileChooser.setCurrentDirectory(homeFolder);
        int option = fileChooser.showSaveDialog(mainController.getMainFrame());

        if (option == JFileChooser.APPROVE_OPTION) {
          String fileName = FileManager.getInstance().addPngExtension(fileChooser.getSelectedFile().getAbsolutePath());
          File targetFile = new File(fileName);

          FileManager.getInstance().copyFile(sourceFile, targetFile);

          MessageDialog.getInstance().showInformationMessage(mainController.getMainFrame(),
              MessageManager.getInstance().getMessage("main_window-file_menu-export_action-success"));
        }
      }
    } catch (PlantUMLClientException | IOException ex) {
      MessageDialog.getInstance().showErrorMessage(mainController.getMainFrame(), ex.getMessage());
    }
  }

}
