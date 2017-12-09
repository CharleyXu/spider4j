package com.xu.spider4j.entity;

//排序类
public class Sort {
  private String field;
  private String type;//ASC	DESC
  public Sort() {
  }

  public Sort(String field) {
    this.field = field;
    type = "ASC";
  }

  public Sort(String field, String type) {
    this.field = field;
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }
}

