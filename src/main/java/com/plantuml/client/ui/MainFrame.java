package com.plantuml.client.ui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

import com.plantuml.client.message.MessageManager;
import com.plantuml.client.ui.controller.MainFrameController;
import com.plantuml.client.ui.image.ImageManager;

public class MainFrame extends JFrame {
  private static final long serialVersionUID = 4215835542332082426L;

  private MainPanel mainPanel;
  private JToolBar toolBar;
  private JMenuBar mainMenuBar;

  private MainFrameController controller;

  public MainFrame() {
    super(MessageManager.getInstance().getMessage("main_window-title"));
    setLayout(new BorderLayout());

    add(getToolBar(), BorderLayout.PAGE_START);
    add(getMainPanel(), BorderLayout.CENTER);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    setIcon();
    setJMenuBar(getMainMenuBar());
  }

  public void splitPanel() {
    getMainPanel().splitPanel();
  }

  public String getUmlCode() {
    return getMainPanel().getUmlCode();
  }
  
  public void setUmlCode(final String umlCode) {
    getMainPanel().setUmlCode(umlCode);
  }

  public void refreshDiagramImage(final File diagramFile) {
    getMainPanel().refreshDiagramImage(diagramFile);
  }

  public File getRenderedFile() {
    return getMainPanel().getRenderedFile();
  }

  public void updateTitle(final String fileName) {
    setTitle(MessageManager.getInstance().getMessage("main_window-title") + " " + fileName);
  }

  private void setIcon() {
    setIconImage(ImageManager.getInstance().getImage("app_icon_16"));
  }

  private MainFrameController getController() {
    if (controller == null) {
      controller = new MainFrameController(this);
    }

    return controller;
  }

  private JMenuBar getMainMenuBar() {
    if (mainMenuBar == null) {
      mainMenuBar = getController().buildMenuBar();
    }

    return mainMenuBar;
  }

  private MainPanel getMainPanel() {
    if (mainPanel == null) {
      mainPanel = new MainPanel();
    }

    return mainPanel;
  }

  private JToolBar getToolBar() {
    if (toolBar == null) {
      toolBar = getController().buildToolBar();
    }

    return toolBar;
  }
}
