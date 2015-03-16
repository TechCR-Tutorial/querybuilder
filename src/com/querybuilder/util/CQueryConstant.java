package com.querybuilder.util;

/**
 * @auther : chamly
 * @date : 3/4/15
 * @time : 11:12 PM
 */
public class CQueryConstant {

    public static final String PARAM_PREFIX = ":";

    public static final class QueryConstant {
        public static final String EQUAL = " = ";
        public static final String LIKE_SYMBOL = "'%'";
        public static final String LIKE = " like ";
        public static final String CONCAT = " || ";
        public static final String GT = " > ";
        public static final String LT = " < ";
    }

    public static final class Symbol {
        public static final String SPACE = " ";
        public static final String EMPTY_STRING = "";
        public static final String COMMA = ",";
        public static final String OPEN_PARENTHESIS = "(";
        public static final String CLOSE_PARENTHESIS = ")";
    }

    public static final class DefaultValues {
        public static final String DEFAULT_ALIAS = " e ";
    }
}
