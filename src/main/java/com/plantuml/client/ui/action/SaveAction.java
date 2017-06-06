package com.plantuml.client.ui.action;

import java.awt.event.ActionEvent;
import java.io.File;

import com.plantuml.client.core.PlantUMLClientException;
import com.plantuml.client.message.MessageManager;
import com.plantuml.client.ui.controller.MainFrameController;
import com.plantuml.client.ui.dialog.MessageDialog;
import com.plantuml.client.ui.image.ImageManager;
import com.plantuml.client.util.StringUtils;

public class SaveAction extends GenericSaveAction {
  private static final long serialVersionUID = 7522774577789421033L;

  public SaveAction(final MainFrameController mainController) {
    this(mainController, true);
  }

  public SaveAction(final MainFrameController mainController, boolean addCaption) {
    super(mainController, MessageManager.getInstance().getMessage("main_window-file_menu-save_action"),
        ImageManager.getInstance().getImageIcon("save_16"),
        MessageManager.getInstance().getMessage("main_window-file_menu-save_action-desc"), addCaption);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      String umlCode = getMainController().getMainFrame().getUmlCode();
      File selectedFile = null;
      boolean forceOverride = true;

      if (!StringUtils.getInstance().isValid(getMainController().getMainFrame().getUmlCode())) {
        MessageDialog.getInstance().showErrorMessage(getMainController().getMainFrame(),
            MessageManager.getInstance().getMessage("main_window-file_menu-save_action-error_no_code"));
      } else {
        getMainController().requestDiagramGeneration();

        if (getMainController().getCurrentFile() == null) {
          File homeFolder = new File(System.getProperty("user.home"));
          selectedFile = getTargetFile(homeFolder);
          forceOverride = false;
        } else {
          selectedFile = getMainController().getCurrentFile();
        }

        if (selectedFile != null) {
          write2File(selectedFile, umlCode, forceOverride);
        }
      }
    } catch (PlantUMLClientException ex) {
      MessageDialog.getInstance().showErrorMessage(getMainController().getMainFrame(), ex.getMessage());
    }
  }
}
