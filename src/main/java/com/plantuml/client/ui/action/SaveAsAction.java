package com.plantuml.client.ui.action;

import java.awt.event.ActionEvent;
import java.io.File;

import com.plantuml.client.core.PlantUMLClientException;
import com.plantuml.client.message.MessageManager;
import com.plantuml.client.ui.controller.MainFrameController;
import com.plantuml.client.ui.dialog.MessageDialog;
import com.plantuml.client.ui.image.ImageManager;
import com.plantuml.client.util.FileManager;
import com.plantuml.client.util.StringUtils;

public class SaveAsAction extends GenericSaveAction {
  private static final long serialVersionUID = 7522774577789421033L;

  public SaveAsAction(final MainFrameController mainController) {
    this(mainController, true);
  }

  public SaveAsAction(final MainFrameController mainController, boolean addCaption) {
    super(mainController, MessageManager.getInstance().getMessage("main_window-file_menu-save_as_action"),
        ImageManager.getInstance().getImageIcon("save_16"),
        MessageManager.getInstance().getMessage("main_window-file_menu-save_as_action-desc"), addCaption);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      String umlCode = getMainController().getMainFrame().getUmlCode();
      File selectedFile = null;

      if (!StringUtils.getInstance().isValid(getMainController().getMainFrame().getUmlCode())) {
        MessageDialog.getInstance().showErrorMessage(getMainController().getMainFrame(),
            MessageManager.getInstance().getMessage("main_window-file_menu-save_action-error_no_code"));
      } else {
        getMainController().requestDiagramGeneration();

        File homeFolder = new File(System.getProperty("user.home"));

        if (getMainController().getCurrentFile() != null) {
          homeFolder = FileManager.getInstance().getParentFolder(getMainController().getCurrentFile());
        }

        selectedFile = getTargetFile(homeFolder);

        if (selectedFile != null) {
          write2File(selectedFile, umlCode, false);
        }
      }
    } catch (PlantUMLClientException ex) {
      MessageDialog.getInstance().showErrorMessage(getMainController().getMainFrame(), ex.getMessage());
    }
  }
}
