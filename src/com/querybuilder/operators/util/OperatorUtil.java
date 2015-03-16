package com.querybuilder.operators.util;

import java.util.Arrays;

import com.querybuilder.operators.And;
import com.querybuilder.operators.LogicalOperator;
import com.querybuilder.operators.Or;
import com.querybuilder.util.types.CQueryCondition;
import com.querybuilder.util.types.CQueryConditionMap;
import com.querybuilder.util.types.CQueryParameter;
import com.querybuilder.util.types.CQueryParamValueMap;

/**
 * @auther : chamly
 * @date : 3/9/15
 * @time : 3:23 PM
 */
public class OperatorUtil {

    public static CQueryParamValueMap getParamValue(String field, Object value) {
        return new CQueryParamValueMap(field, value);
    }

    public static CQueryParameter getParameter(String field) {
        return new CQueryParameter(field);
    }

    public static CQueryParameter getParameter(String field, String paramName, Object value) {
        return new CQueryParameter(field, paramName, value);
    }

    public static CQueryConditionMap getConditionMap(CQueryCondition condition, String field) {
        return new CQueryConditionMap(condition, getParameter(field));
    }

    public static CQueryConditionMap getConditionMap(CQueryCondition condition, String field, String paramName, Object value) {
        return new CQueryConditionMap(condition, getParameter(field, paramName, value));
    }

    public static And AND(CQueryCondition condition, String field) {
        return new And(getConditionMap(condition, field));
    }

    public static And AND(CQueryCondition condition, String field, String paramName, Object value) {
        return new And(getConditionMap(condition, field, paramName, value));
    }

    public static And AND(LogicalOperator logicalOperator) {
        return new And(logicalOperator);
    }

    public static Or OR(CQueryConditionMap... params) {
        return new Or(Arrays.asList(params));
    }
}
