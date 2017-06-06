package com.plantuml.client.ui.dialog;

import java.awt.Component;

import javax.swing.JOptionPane;

import com.plantuml.client.message.MessageManager;
import com.plantuml.client.ui.image.ImageManager;

public final class MessageDialog {
  private static MessageDialog instance;

  private MessageDialog() {
    // blocking
  }

  public static MessageDialog getInstance() {
    if (instance == null) {
      instance = new MessageDialog();
    }

    return instance;
  }

  public void showErrorMessage(final Component parentComponent, final String message) {
    JOptionPane.showMessageDialog(parentComponent, message, MessageManager.getInstance().getMessage("error"),
        JOptionPane.ERROR_MESSAGE, ImageManager.getInstance().getImageIcon("error_32"));
  }

  public void showInformationMessage(final Component parentComponent, final String message) {
    JOptionPane.showMessageDialog(parentComponent, message, MessageManager.getInstance().getMessage("info"),
        JOptionPane.INFORMATION_MESSAGE, ImageManager.getInstance().getImageIcon("information_32"));
  }

  public int showYesNoMessage(final Component parentComponent, final String message) {
    return JOptionPane.showConfirmDialog(parentComponent, message, MessageManager.getInstance().getMessage("confirm"),
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
        ImageManager.getInstance().getImageIcon("question_32"));

  }
}
