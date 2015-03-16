package com.querybuilder.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.querybuilder.operators.LogicalOperator;
import com.querybuilder.util.CQueryConstant;
import com.querybuilder.util.ToolKit;
import com.querybuilder.util.types.CQueryParamValueMap;

/**
 * @auther : chamly
 * @date : 3/4/15
 * @time : 9:49 PM
 */
public class CQuery {

    private List<String> selectFields;
    private Object model;
    private List<LogicalOperator> operators;
    private Map<String, Object> parameterMap;
    List<CQueryParamValueMap> paramValues = new ArrayList<CQueryParamValueMap>();

    public CQuery(Object model) {
        this.model = model;
    }

    public void setSelectFields(String... selectFields) {
        this.selectFields = Arrays.asList(selectFields);
    }

    public void addLogicalOperator(LogicalOperator operator) {
        if (ToolKit.isListEmpty(operators)) {
            operators = new ArrayList<LogicalOperator>();
        }
        operators.add(operator);
    }

    public String getSql() {
        StringBuilder selectQuery = new StringBuilder();
        getSelectClause(selectQuery);
        String whereClause = getWhereClause();
        selectQuery.append(whereClause);
        return selectQuery.toString();
    }

    private void getSelectClause(StringBuilder query) {
        String className = model.getClass().getSimpleName();
        String alias = ToolKit.getAlias(className);
        query.append(" select ");
        if (!ToolKit.isListEmpty(selectFields)) {
            query.append(ToolKit.getListAsCommaSeparateString(selectFields));
        } else {
            query.append(alias);
        }
        query.append(" from ").append(model.getClass().getSimpleName());
        query.append(alias);
    }

    private String getWhereClause() {
        if (!ToolKit.isListEmpty(operators)) {
            boolean whereInitialized = false;
            StringBuilder where = new StringBuilder(" where ");
            for (LogicalOperator operator : operators) {
                String operatorSql = operator.getSql(whereInitialized, model);
                if (!ToolKit.isEmpty(operatorSql)) {
                    where.append(CQueryConstant.Symbol.SPACE);
                    where.append(operatorSql);
                    where.append(CQueryConstant.Symbol.SPACE);
                    whereInitialized = true;
                    paramValues.addAll(operator.getParamValues());
                }
            }
            return whereInitialized ? where.toString() : CQueryConstant.Symbol.EMPTY_STRING;
        }
        return CQueryConstant.Symbol.EMPTY_STRING;
    }

    public Map<String, Object> getParameterMap() {
        if (null == parameterMap) {
            parameterMap = new HashMap<String, Object>();
        }
        for (CQueryParamValueMap paramValue : paramValues) {
            parameterMap.put(paramValue.getParamName(), paramValue.getValue());
        }
        return parameterMap;
    }
}
