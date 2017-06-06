package com.plantuml.client.ui.action;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.plantuml.client.message.MessageManager;
import com.plantuml.client.ui.controller.MainFrameController;
import com.plantuml.client.ui.dialog.MessageDialog;
import com.plantuml.client.ui.image.ImageManager;
import com.plantuml.client.util.StringUtils;

public class NewAction extends GenericAction {
  private static final long serialVersionUID = 2322849784452323812L;

  private static final String EMPTY_CODE = "@startuml\nAlice -> Bob : Hi\n@enduml";

  private MainFrameController mainController;

  public NewAction(final MainFrameController mainController) {
    this(mainController, true);
  }

  public NewAction(final MainFrameController mainController, boolean addCaption) {
    super(addCaption ? MessageManager.getInstance().getMessage("main_window-file_menu-new_action") : "",
        ImageManager.getInstance().getImageIcon("new_16"),
        MessageManager.getInstance().getMessage("main_window-file_menu-new_action-desc"), addCaption);

    this.mainController = mainController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String umlCode = mainController.getMainFrame().getUmlCode();
    int answer = JOptionPane.YES_OPTION;

    if (StringUtils.getInstance().isValid(umlCode)) {
      answer = MessageDialog.getInstance().showYesNoMessage(mainController.getMainFrame(),
          MessageManager.getInstance().getMessage("main_window-file_menu-open_action-not_empty_code"));
    }

    if (answer == JOptionPane.YES_OPTION) {
      mainController.getMainFrame().setUmlCode(EMPTY_CODE);
      mainController.requestDiagramGeneration();
    }
  }
}
