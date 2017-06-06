package com.plantuml.client.ui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MainPanel extends JPanel {
  private static final long serialVersionUID = 6334451504140700208L;

  private JSplitPane splitPanel;
  private EditorPanel editorPanel;
  private DiagramPanel diagramPanel;

  public MainPanel() {
    super();

    setLayout(new BorderLayout());
    add(getSplitPanel(), BorderLayout.CENTER);
  }

  private JSplitPane getSplitPanel() {
    if (splitPanel == null) {
      splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
      splitPanel.setLeftComponent(getEditorPanel());
      splitPanel.setRightComponent(getDiagramPanel());
    }

    return splitPanel;
  }

  private EditorPanel getEditorPanel() {
    if (editorPanel == null) {
      editorPanel = new EditorPanel();
    }

    return editorPanel;
  }

  private DiagramPanel getDiagramPanel() {
    if (diagramPanel == null) {
      diagramPanel = new DiagramPanel();
    }

    return diagramPanel;
  }

  protected String getUmlCode() {
    return getEditorPanel().getUmlCode();
  }

  protected void setUmlCode(final String umlCode) {
    getEditorPanel().setUmlCode(umlCode);
  }

  protected void refreshDiagramImage(final File diagramFile) {
    getDiagramPanel().refreshDiagramImage(diagramFile);
  }

  protected void splitPanel() {
    getSplitPanel().setDividerLocation(0.5);
  }

  protected File getRenderedFile() {
    return getDiagramPanel().getPreviousFile();
  }
}
