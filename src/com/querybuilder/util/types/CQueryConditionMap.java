package com.querybuilder.util.types;

import com.querybuilder.operators.util.OperatorUtil;
import com.querybuilder.util.CQueryUtil;

/**
 * @auther : chamly
 * @date : 3/9/15
 * @time : 4:42 PM
 * Query Condition Map
 * create query according to condition.
 */
public class CQueryConditionMap {

    private CQueryCondition queryCondition;
    private CQueryParameter parameter;
    private CQueryParamValueMap paramValue;

    public CQueryConditionMap(CQueryCondition queryCondition, CQueryParameter queryParameter) {
        this.queryCondition = queryCondition;
        this.parameter = queryParameter;
    }

    /**
     * create conditional sql according to condition / parameters / and values.
     * @param model
     * @return
     * @throws Exception
     */
    public String getConditionalSQL(Object model) throws Exception {
        String paramField = "";
        String field = parameter.getField();
        if (parameter.isParamType()) {
            paramField = CQueryUtil.formatToParameter(parameter.getParamName());
        } else {
            paramField = CQueryUtil.formatToParameter(field);
        }
        if (CQueryUtil.isValidParamMapValue(parameter, model)) {
            paramValue = OperatorUtil.getParamValue(paramField, CQueryUtil.getParamValue(parameter, model));
            return field + queryCondition.getParamCondition(paramField);
        }
        throw new RuntimeException("Invalid Condition");
    }

    public CQueryCondition getQueryCondition() {
        return queryCondition;
    }

    /**
     * return parameter name and value contain instance.
     * @return
     */
    public CQueryParamValueMap getQueryParamValue() {
        return paramValue;
    }

}
