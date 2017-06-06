package com.plantuml.client.ui.action;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import com.plantuml.client.Application;
import com.plantuml.client.message.MessageManager;
import com.plantuml.client.ui.image.ImageManager;

public class ExitAction extends GenericAction {
  private static final long serialVersionUID = -2646524719648018599L;

  public ExitAction() {
    this(true);
  }

  public ExitAction(boolean addCaption) {
    this(MessageManager.getInstance().getMessage("main_window-file_menu-exit_action"),
        ImageManager.getInstance().getImageIcon("exit_16"),
        MessageManager.getInstance().getMessage("main_window-file_menu-exit_action-desc"), addCaption);
  }

  public ExitAction(final String name, final ImageIcon imageIcon, final String desc, boolean addCaption) {
    super(addCaption ? name : "", imageIcon, desc, addCaption);
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    Application.endApplication(0);
  }
}
