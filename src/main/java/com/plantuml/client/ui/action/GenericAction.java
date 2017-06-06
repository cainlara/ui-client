package com.plantuml.client.ui.action;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public abstract class GenericAction extends AbstractAction {
  private static final long serialVersionUID = 7416616418023324099L;

  public GenericAction(final String name, final ImageIcon imageIcon, final String desc, boolean addCaption) {
    super(name, imageIcon);

    if (addCaption) {
      putValue(NAME, name);
    }
    
    putValue(SHORT_DESCRIPTION, desc);
  }

}
