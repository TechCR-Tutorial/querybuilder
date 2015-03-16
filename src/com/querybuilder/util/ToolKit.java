package com.querybuilder.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther : chamly
 * @date : 3/4/15
 * @time : 11:04 PM
 */
public class ToolKit {

    public static boolean isEmpty(String value) {
        return value == null || CQueryConstant.Symbol.EMPTY_STRING.equals(value.trim());
    }

    public static String nvl(String value) {
        if (isEmpty(value)) {
            return CQueryConstant.Symbol.EMPTY_STRING;
        }
        return value.trim();
    }

    public static String getAlias(String value) {
        if (!isEmpty(value)) {
            return wrapBySpace(value.substring(0, 1).toLowerCase());
        }
        return CQueryConstant.DefaultValues.DEFAULT_ALIAS;
    }

    public static String wrapBySpace(String value) {
        return CQueryConstant.Symbol.SPACE + nvl(value) + CQueryConstant.Symbol.SPACE;
    }

    public static <E> boolean isListEmpty(List<E> list) {
        return list == null || list.size() == 0;
    }

    public static String getListAsCommaSeparateString(List<String> list) {
        return getListAsString(list, CQueryConstant.Symbol.COMMA);
    }

    public static String getListAsString(List<String> list, String separator) {
        StringBuilder value = new StringBuilder();
        if (!isListEmpty(list)) {
            for (String item : list) {
                value.append(item);
                value.append(separator);
            }
            String strValue = value.toString();
            if (strValue.length() > 0) {
                strValue = strValue.substring(0, strValue.length() - 1);
            }
            return strValue;
        }
        return value.toString();
    }

    public static List<String> convertCommaSeparateStringToList(String value) {
        return convertStringToList(value, CQueryConstant.Symbol.COMMA);
    }

    public static List<String> convertStringToList(String value, String separator) {
        List<String> result = new ArrayList<String>();
        if (!isEmpty(value)) {
            String[] elements = value.split(separator);
            for (String element : elements) {
                result.add(element);
            }
        }
        return result;
    }

    public static <E> List<E> getList(E model) {
        List<E> result =  new ArrayList<E>();
        if (null != model) {
            result.add(model);
        }
        return result;
    }

}
