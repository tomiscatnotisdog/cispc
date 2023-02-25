package com.xxxx.cispc.query;

import com.xxxx.cispc.base.BaseQuery;

public class ModuleQuery extends BaseQuery {

    private String moduleName; //资源名称
    private String originator; //资源作者

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    @Override
    public String toString() {
        return "ModuleQuery{" +
                "moduleName='" + moduleName + '\'' +
                ", originator='" + originator + '\'' +
                '}';
    }
}
