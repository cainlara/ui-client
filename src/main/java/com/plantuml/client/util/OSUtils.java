package com.plantuml.client.util;

import com.plantuml.client.core.EOS;

public final class OSUtils {
  private static OSUtils instance;

  private OSUtils() {
    // hide constructor
  }

  public static OSUtils getInstance() {
    if (instance == null) {
      instance = new OSUtils();
    }

    return instance;
  }

  public EOS getOsType(final String osName) {
    if (osName.indexOf("win") >= 0) {
      return EOS.WIN;
    }

    if (osName.indexOf("mac") >= 0) {
      return EOS.MACOS;
    }

    if (osName.indexOf("sunos") >= 0) {
      return EOS.SOLARIS;
    }

    if (osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") > 0) {
      return EOS.LINUX;
    }

    return EOS.OTHER;
  }
}
