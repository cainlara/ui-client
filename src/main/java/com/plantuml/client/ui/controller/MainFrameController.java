package com.plantuml.client.ui.controller;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import com.plantuml.client.core.PlantUMLClientException;
import com.plantuml.client.message.MessageManager;
import com.plantuml.client.ui.MainFrame;
import com.plantuml.client.ui.action.ExitAction;
import com.plantuml.client.ui.action.ExportGeneratedImage;
import com.plantuml.client.ui.action.GenerateDiagramAction;
import com.plantuml.client.ui.action.NewAction;
import com.plantuml.client.ui.action.OpenFileAction;
import com.plantuml.client.ui.action.SaveAction;
import com.plantuml.client.ui.action.SaveAsAction;
import com.plantuml.client.ui.dialog.MessageDialog;
import com.plantuml.client.util.DiagramGenerator;

public class MainFrameController {
  private MainFrame mainFrame;
  private File currentFile;

  public MainFrameController(final MainFrame mainFrame) {
    this.mainFrame = mainFrame;
  }

  public MainFrame getMainFrame() {
    return mainFrame;
  }

  public File getCurrentFile() {
    return currentFile;
  }

  public void setCurrentFile(File currentFile) {
    this.currentFile = currentFile;
  }

  public JMenuBar buildMenuBar() {
    JMenuBar mainMenuBar = new JMenuBar();

    JMenu fileMenu = new JMenu(MessageManager.getInstance().getMessage("main_window-file_menu"));

    JMenuItem newMenuItem = new JMenuItem(new NewAction(this));
    JMenuItem openMenuItem = new JMenuItem(new OpenFileAction(this));

    JMenuItem saveMenuItem = new JMenuItem(new SaveAction(this));

    JMenuItem saveAsMenuItem = new JMenuItem(new SaveAsAction(this));

    JMenuItem exportMenuItem = new JMenuItem(new ExportGeneratedImage(this));

    JMenuItem exitMenuItem = new JMenuItem(new ExitAction());

    fileMenu.add(newMenuItem);
    fileMenu.add(openMenuItem);
    fileMenu.add(saveMenuItem);
    fileMenu.add(saveAsMenuItem);
    fileMenu.addSeparator();
    fileMenu.add(exportMenuItem);
    fileMenu.addSeparator();
    fileMenu.add(exitMenuItem);

    JMenu codeMenu = new JMenu(MessageManager.getInstance().getMessage("main_window-code_menu"));

    JMenuItem generateMenuItem = new JMenuItem(new GenerateDiagramAction(this));

    codeMenu.add(generateMenuItem);

    mainMenuBar.add(fileMenu);
    mainMenuBar.add(codeMenu);

    return mainMenuBar;
  }

  public JToolBar buildToolBar() {
    JToolBar toolBar = new JToolBar();

    JButton generateDiagramButton = new JButton(new GenerateDiagramAction(this, false));
    JButton exportDiagramButton = new JButton(new ExportGeneratedImage(this, false));

    toolBar.add(generateDiagramButton);
    toolBar.addSeparator();
    toolBar.add(exportDiagramButton);

    return toolBar;
  }

  public void requestDiagramGeneration() {
    try {
      String umlCode = mainFrame.getUmlCode();
      File diagramFile = DiagramGenerator.getInstance().getDiagramFile(umlCode);
      mainFrame.refreshDiagramImage(diagramFile);
    } catch (IOException | PlantUMLClientException e) {
      MessageDialog.getInstance().showErrorMessage(mainFrame, e.getMessage());
    }
  }
}
