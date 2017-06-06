package com.plantuml.client.ui.action;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.plantuml.client.core.PlantUMLClientException;
import com.plantuml.client.message.MessageManager;
import com.plantuml.client.ui.controller.MainFrameController;
import com.plantuml.client.ui.dialog.MessageDialog;
import com.plantuml.client.util.FileManager;

public abstract class GenericSaveAction extends GenericAction {
  private static final long serialVersionUID = 5669081905321511498L;

  private MainFrameController mainController;

  public GenericSaveAction(final MainFrameController mainController, final String name, final ImageIcon imageIcon,
      final String desc) {
    this(mainController, name, imageIcon, desc, true);
  }

  public GenericSaveAction(final MainFrameController mainController, final String name, final ImageIcon imageIcon,
      final String desc, boolean addCaption) {
    super(addCaption ? name : "", imageIcon, desc, addCaption);

    this.mainController = mainController;
  }

  protected MainFrameController getMainController() {
    return this.mainController;
  }

  protected File getTargetFile(final File homeFolder) {
    JFileChooser fileChooser = new JFileChooser();
    File rootFolder = null;
    File selectedFile = null;

    if (homeFolder == null) {
      rootFolder = new File(System.getProperty("user.home"));
    } else {
      rootFolder = homeFolder;
    }

    fileChooser.setCurrentDirectory(rootFolder);
    int option = fileChooser.showSaveDialog(mainController.getMainFrame());

    if (option == JFileChooser.APPROVE_OPTION) {
      selectedFile = fileChooser.getSelectedFile();
    }

    return selectedFile;
  }

  protected void write2File(final File selectedFile, final String umlCode, boolean forceOverride) throws PlantUMLClientException {
    String absolutPath = selectedFile.getAbsolutePath();
    String fileName = FileManager.getInstance().addPucExtension(absolutPath);
    File targetFile = new File(fileName);
    int option = JOptionPane.YES_OPTION;

    if (!forceOverride && targetFile.exists()) {
      option = MessageDialog.getInstance().showYesNoMessage(getMainController().getMainFrame(), MessageManager.getInstance()
          .getMessage("main_window-file_menu-save_action-file_exists_warning", targetFile.getName()));
    }

    if (option == JOptionPane.YES_OPTION) {
      File currentFile = FileManager.getInstance().saveSourceCodeFile(umlCode, targetFile);
      mainController.setCurrentFile(selectedFile);
      mainController.getMainFrame().updateTitle(currentFile.getName());
    }
  }
}
