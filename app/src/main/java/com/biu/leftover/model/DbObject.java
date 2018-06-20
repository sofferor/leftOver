package com.biu.leftover.model;

import java.util.Date;

public interface DbObject {
    public String getDbId();
    public void setDbId(String dbId);
    public void setUpdate_time(Date date);
}
