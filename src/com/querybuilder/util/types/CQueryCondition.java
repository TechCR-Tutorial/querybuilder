package com.querybuilder.util.types;

import com.querybuilder.util.CQueryConstant.QueryConstant;
import com.querybuilder.util.CQueryUtil;

/**
 * @auther : chamly
 * @date : 3/4/15
 * @time : 10:09 PM
 * CQueryCondition : Query Condition Mapping Class.
 */
public enum  CQueryCondition {
    /**
     * Ex : firstName = :firstName
     */
    EQUAL() {
        @Override
        public String getParamCondition(String paramName) {
            setParameter(paramName);
            return QueryConstant.EQUAL + paramName;
        }
    },
    /**
     * Ex: firstName like '%' || :firstName || '%'
     */
    WRAP_LIKE() {
        @Override
        public String getParamCondition(String paramName) {
            setParameter(paramName);
            return QueryConstant.LIKE + CQueryUtil.getLikeConcatString(true) +paramName +
                    CQueryUtil.getLikeConcatString(false);
        }
    },
    /**
     * Ex : firstName like '%' || :firstName
     */
    PREFIX_LIKE() {
        @Override
        public String getParamCondition(String paramName) {
            setParameter(paramName);
            return QueryConstant.LIKE + CQueryUtil.getLikeConcatString(true) + paramName;
        }
    },
    /**
     * Ex : firstName like :firstName || '%'
     */
    POSTFIX_LIKE() {
        @Override
        public String getParamCondition(String paramName) {
            setParameter(paramName);
            return QueryConstant.LIKE + paramName + CQueryUtil.getLikeConcatString(false);
        }
    },
    /**
     * Ex : age > :age
     */
    GREATER_THAN() {
        @Override
        public String getParamCondition(String paramName) {
            setParameter(paramName);
            return QueryConstant.GT + paramName;
        }
    },
    /**
     * Ex : age < :age
     */
    LESS_THAN() {
        @Override
        public String getParamCondition(String paramName) {
            setParameter(paramName);
            return QueryConstant.LT + paramName;
        }
    };

    private String parameter;

    public abstract String getParamCondition(String paramName);
    public  String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
