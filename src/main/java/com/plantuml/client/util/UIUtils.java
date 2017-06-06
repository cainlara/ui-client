package com.plantuml.client.util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class UIUtils {
  private static UIUtils instance;

  private UIUtils() {
    // hide constructor
  }

  public static UIUtils getInstance() {
    if (instance == null) {
      instance = new UIUtils();
    }

    return instance;
  }

  public void showCentered(final JFrame window) {
    if (window != null) {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

      int windowWidth = (int) (screenSize.getWidth() / 2);
      int windowHeight = (int) (screenSize.getHeight() / 2);

      window.setSize(new Dimension(windowWidth, windowHeight));
      window.setLocationRelativeTo(null);
      window.setVisible(true);
    }
  }

  public void showCentered(final JFrame parent, final JDialog dialog) {
    if (dialog != null) {
      dialog.setLocationRelativeTo(parent);
      dialog.setVisible(true);
    }
  }
}
