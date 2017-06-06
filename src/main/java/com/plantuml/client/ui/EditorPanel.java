package com.plantuml.client.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class EditorPanel extends JPanel {
  private static final long serialVersionUID = 8435124412470061107L;

  private JTextArea textArea;

  public EditorPanel() {
    super();
    setLayout(new BorderLayout());

    add(getTextArea(), BorderLayout.CENTER);
  }

  private JTextArea getTextArea() {
    if (textArea == null) {
      textArea = new JTextArea();
    }

    return textArea;
  }

  protected String getUmlCode() {
    return getTextArea().getText().trim();
  }

  protected void setUmlCode(final String umlCode) {
    getTextArea().setText(umlCode);
  }
}
