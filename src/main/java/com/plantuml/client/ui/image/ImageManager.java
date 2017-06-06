package com.plantuml.client.ui.image;

import java.awt.Image;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

public class ImageManager {
  private static ImageManager instance;

  private ResourceBundle bundle;

  private ImageManager() {
    // hide constructor
  }

  public static ImageManager getInstance() {
    if (instance == null) {
      instance = new ImageManager();
    }

    return instance;
  }

  public ImageIcon getImageIcon(final String key) {
    String path = getFilePath(key);
    URL imgURL = getClass().getResource(path);

    if (imgURL != null) {
      return new ImageIcon(imgURL);
    }

    return null;
  }

  public Image getImage(final String key) {
    ImageIcon imgIcon = getImageIcon(key);

    return imgIcon == null ? null : imgIcon.getImage();
  }

  private String getFilePath(final String key) {
    return getBundle().getString(key);
  }

  private ResourceBundle getBundle() {
    if (bundle == null) {
      bundle = ResourceBundle.getBundle("images");
    }

    return bundle;
  }
}
