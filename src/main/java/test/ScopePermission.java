package test;

import java.security.Permission;

public class ScopePermission extends Permission {
  private String actions;

  public ScopePermission(String name, String actions) {
    super(name);
    this.actions = actions;
  }

  @Override
  public boolean implies(Permission permission) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean equals(Object obj) {
    throw new UnsupportedOperationException();
  }

  @Override
  public int hashCode() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getActions() {
    return actions;
  }
}
