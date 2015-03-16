package com.querybuilder.operators;

import java.util.ArrayList;
import java.util.List;

import com.querybuilder.util.CQueryConstant;
import com.querybuilder.util.ToolKit;
import com.querybuilder.util.types.CQueryConditionMap;
import com.querybuilder.util.types.CQueryParamValueMap;

/**
 * @auther : chamly
 * @date : 3/9/15
 * @time : 3:56 PM
 */
public class Or implements LogicalOperator {

    private List<CQueryConditionMap> queryParamMaps;

    public Or(List<CQueryConditionMap> queryParamMaps) {
        this.queryParamMaps = queryParamMaps;
    }

    @Override
    public String getSql(boolean whereInitialized, Object model) {
        StringBuilder sql = new StringBuilder();
        boolean shouldAddOr = false;
        for (int i = 0; i < queryParamMaps.size(); i++) {
            CQueryConditionMap paramMap = queryParamMaps.get(i);
            try {
                String paramQuery = paramMap.getConditionalSQL(model);
                if (shouldAddOr) {
                    sql.append(" or ");
                }
                sql.append(paramQuery);
                shouldAddOr = true;
            } catch (Exception e) {
            }
        }
        if (!ToolKit.isEmpty(sql.toString())) {
            return CQueryConstant.Symbol.OPEN_PARENTHESIS + sql.toString() + CQueryConstant.Symbol.CLOSE_PARENTHESIS;
        }
        return CQueryConstant.Symbol.SPACE;
    }

    @Override
    public List<CQueryParamValueMap> getParamValues() {
        List<CQueryParamValueMap> paramValueList = new ArrayList<CQueryParamValueMap>();
        for (CQueryConditionMap queryParamMap : queryParamMaps) {
            CQueryParamValueMap paramValue = queryParamMap.getQueryParamValue();
            if (null != paramValue) {
                paramValueList.add(paramValue);
            }
        }
        return paramValueList;
    }
}
