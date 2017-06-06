package com.plantuml.client.core;

public enum EOS {
  MACOS {
    @Override
    public int getId() {
      return 0;
    }
  },
  WIN {
    @Override
    public int getId() {
      return 1;
    }
  },
  LINUX {
    @Override
    public int getId() {
      return 2;
    }
  },
  SOLARIS {
    @Override
    public int getId() {
      return 3;
    }
  },
  OTHER {
    @Override
    public int getId() {
      return -1;
    }
  };

  public abstract int getId();
}
