package com.querybuilder.util.types;

/**
 * @auther : chamly
 * @date : 3/13/15
 * @time : 8:51 PM
 * Containing Attribute related to Query Conditional Parameter.
 */
public class CQueryParameter {

    private String paramName;
    private String field;
    private Object value;
    private boolean isParamType = false;

    public CQueryParameter(String field, String paramName, Object value) {
        this.paramName = paramName;
        this.field = field;
        this.value = value;
        isParamType = true;
    }

    public CQueryParameter(String field) {
        this.field = field;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isParamType() {
        return isParamType;
    }

    public void setParamType(boolean isParamType) {
        this.isParamType = isParamType;
    }
}
