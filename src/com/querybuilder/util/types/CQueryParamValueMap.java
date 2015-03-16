package com.querybuilder.util.types;

/**
 * @auther : chamly
 * @date : 3/12/15
 * @time : 10:28 PM
 * Containing Param Name and Value.
 */
public class CQueryParamValueMap {

    String paramName;
    Object value;

    public CQueryParamValueMap(String paramName, Object value) {
        this.paramName = paramName;
        this.value = value;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
