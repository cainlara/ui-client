package com.plantuml.client.core;

public class PlantUMLClientException extends Exception {
  private static final long serialVersionUID = -565440947599227567L;

  public PlantUMLClientException(final String message) {
    super(message);
  }

  public PlantUMLClientException(final Throwable cause) {
    super(cause);
  }

  public PlantUMLClientException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
