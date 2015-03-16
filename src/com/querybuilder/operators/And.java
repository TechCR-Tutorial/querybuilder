package com.querybuilder.operators;


import java.util.List;

import com.querybuilder.util.CQueryConstant;
import com.querybuilder.util.ToolKit;
import com.querybuilder.util.types.CQueryConditionMap;
import com.querybuilder.util.types.CQueryParamValueMap;

/**
 * @auther : chamly
 * @date : 3/4/15
 * @time : 10:03 PM
 * And Operator.
 * Can set query condition map or logical operator.
 */
public class And implements LogicalOperator {

    private CQueryConditionMap queryParamMap;
    private LogicalOperator logicalOperator;

    public And(CQueryConditionMap queryParamMap) {
        this.queryParamMap = queryParamMap;
    }

    public And(LogicalOperator logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    @Override
    public String getSql(boolean whereInitialized, Object model) {
        String sql = !whereInitialized ? " " : " and ";
        if (null != logicalOperator) {
            String logicalOperatorSql = logicalOperator.getSql(whereInitialized, model);
            if (!ToolKit.isEmpty(logicalOperatorSql)) {
                return sql + logicalOperator.getSql(whereInitialized, model);
            }
            return CQueryConstant.Symbol.EMPTY_STRING;
        }
        try {
            return sql + queryParamMap.getConditionalSQL(model);
        } catch (Exception e) {
            return CQueryConstant.Symbol.EMPTY_STRING;
        }

    }

    @Override
    public List<CQueryParamValueMap> getParamValues() {
        if (null != logicalOperator) {
            return logicalOperator.getParamValues();
        }
        return ToolKit.getList(queryParamMap.getQueryParamValue());
    }

}
