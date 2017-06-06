package com.plantuml.client.ui.action;

import java.awt.event.ActionEvent;

import com.plantuml.client.message.MessageManager;
import com.plantuml.client.ui.controller.MainFrameController;
import com.plantuml.client.ui.image.ImageManager;

public class GenerateDiagramAction extends GenericAction {
  private static final long serialVersionUID = 5050830294760452526L;

  private MainFrameController mainController;

  public GenerateDiagramAction(final MainFrameController mainController) {
    this(mainController, true);
  }

  public GenerateDiagramAction(final MainFrameController mainController, boolean addCaption) {
    super(addCaption ? MessageManager.getInstance().getMessage("main_window-code_menu-generate_diagram_action") : "",
        ImageManager.getInstance().getImageIcon("build_16"),
        MessageManager.getInstance().getMessage("main_window-code_menu-generate_diagram_action-desc"), addCaption);

    this.mainController = mainController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    mainController.requestDiagramGeneration();
  }
}
