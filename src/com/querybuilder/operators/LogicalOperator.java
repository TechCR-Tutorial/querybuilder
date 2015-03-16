package com.querybuilder.operators;

import java.util.List;

import com.querybuilder.util.types.CQueryParamValueMap;

/**
 * @auther : chamly
 * @date : 3/4/15
 * @time : 9:53 PM
 */
public interface LogicalOperator {

    public String getSql(boolean whereInitialized, Object model);
    public List<CQueryParamValueMap> getParamValues();

}
