package com.lhw.blog.domain;


public class LhwSorts {

  private Integer sortId;
  private String sortName;
  private String sortAlias;
  private String sortDescription;
  private Integer parentSortId;


  public Integer getSortId() {
    return sortId;
  }

  public void setSortId(Integer sortId) {
    this.sortId = sortId;
  }


  public String getSortName() {
    return sortName;
  }

  public void setSortName(String sortName) {
    this.sortName = sortName;
  }


  public String getSortAlias() {
    return sortAlias;
  }

  public void setSortAlias(String sortAlias) {
    this.sortAlias = sortAlias;
  }


  public String getSortDescription() {
    return sortDescription;
  }

  public void setSortDescription(String sortDescription) {
    this.sortDescription = sortDescription;
  }


  public Integer getParentSortId() {
    return parentSortId;
  }

  public void setParentSortId(Integer parentSortId) {
    this.parentSortId = parentSortId;
  }

}
