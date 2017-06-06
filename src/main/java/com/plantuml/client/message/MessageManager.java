package com.plantuml.client.message;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public final class MessageManager {
  private static MessageManager instance;

  private ResourceBundle bundle;

  private MessageManager() {
    // hide constructor
  }

  public static MessageManager getInstance() {
    if (instance == null) {
      instance = new MessageManager();
    }

    return instance;
  }

  public String getMessage(final String key) {
    return getMessage(key, new Object[0]);
  }

  public String getMessage(final String key, final Object... args) {
    MessageFormat format = new MessageFormat(getBundle().getString(key));
    return format.format(args);
  }

  private ResourceBundle getBundle() {
    if (bundle == null) {
      bundle = ResourceBundle.getBundle("messages");
    }

    return bundle;
  }
}
