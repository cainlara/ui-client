package com.plantuml.client.ui.action;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.plantuml.client.core.PlantUMLClientException;
import com.plantuml.client.message.MessageManager;
import com.plantuml.client.ui.controller.MainFrameController;
import com.plantuml.client.ui.dialog.MessageDialog;
import com.plantuml.client.ui.image.ImageManager;
import com.plantuml.client.util.FileManager;
import com.plantuml.client.util.PUCFileFilter;
import com.plantuml.client.util.StringUtils;

public class OpenFileAction extends GenericAction {
  private static final long serialVersionUID = -2646524719648018599L;

  private MainFrameController mainController;

  public OpenFileAction(final MainFrameController mainController) {
    this(mainController, true);
  }

  public OpenFileAction(final MainFrameController mainController, boolean addCaption) {
    super(addCaption ? MessageManager.getInstance().getMessage("main_window-file_menu-open_action") : "",
        ImageManager.getInstance().getImageIcon("open_16"),
        MessageManager.getInstance().getMessage("main_window-file_menu-open_action-desc"), addCaption);

    this.mainController = mainController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      String umlCode = mainController.getMainFrame().getUmlCode();
      int answer = JOptionPane.YES_OPTION;

      if (StringUtils.getInstance().isValid(umlCode)) {
        answer = MessageDialog.getInstance().showYesNoMessage(mainController.getMainFrame(),
            MessageManager.getInstance().getMessage("main_window-file_menu-open_action-not_empty_code"));
      }

      if (answer == JOptionPane.YES_OPTION) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PUCFileFilter());
        fileChooser.setAcceptAllFileFilterUsed(false);
        int chooserAnswer = fileChooser.showOpenDialog(mainController.getMainFrame());

        if (chooserAnswer == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          String readedUmlCode = FileManager.getInstance().openSourceCodeFile(selectedFile);

          if (StringUtils.getInstance().isValid(readedUmlCode)) {
            mainController.getMainFrame().setUmlCode(readedUmlCode);
            mainController.requestDiagramGeneration();
            mainController.getMainFrame().updateTitle(selectedFile.getName());
            mainController.setCurrentFile(selectedFile);
          } else {
            MessageDialog.getInstance().showErrorMessage(mainController.getMainFrame(), MessageManager.getInstance()
                .getMessage("main_window-file_menu-open_action-error_reading_file", selectedFile.getAbsolutePath()));
          }
        }
      }
    } catch (PlantUMLClientException ex) {
      MessageDialog.getInstance().showErrorMessage(mainController.getMainFrame(), ex.getMessage());
    }
  }
}
