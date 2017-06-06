package com.plantuml.client;

import javax.swing.SwingUtilities;

import com.plantuml.client.core.EOS;
import com.plantuml.client.ui.MainFrame;
import com.plantuml.client.util.FileManager;
import com.plantuml.client.util.OSUtils;
import com.plantuml.client.util.UIUtils;

public class Application {
  private static MainFrame mainFrame = null;

  public static void main(String[] args) {
    if (OSUtils.getInstance().getOsType(System.getProperty("os.name").toLowerCase()).getId() == EOS.MACOS.getId()) {
      System.setProperty("apple.laf.useScreenMenuBar", "true");
    }
    
    FileManager.getInstance().cleanTemporalFolder();

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        UIUtils.getInstance().showCentered(getMainFrame());
        getMainFrame().splitPanel();
      }
    });
  }

  public static MainFrame getMainFrame() {
    if (mainFrame == null) {
      mainFrame = new MainFrame();
    }

    return mainFrame;
  }

  public static void endApplication(int status) {
    System.exit(status);
  }
}
