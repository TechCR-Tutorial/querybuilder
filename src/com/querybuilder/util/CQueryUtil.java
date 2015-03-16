package com.querybuilder.util;

import java.lang.reflect.Field;

import com.querybuilder.util.types.CQueryParameter;


/**
 * @auther : chamly
 * @date : 3/4/15
 * @time : 10:05 PM
 */
public class CQueryUtil {

    /**
     * Return Field from model Class.
     * @param fieldName
     * @param clazz
     * @return
     * @throws Exception
     */
    public static Field getField(String fieldName, Class clazz) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;

    }

    /**
     * return field value from model.
     * @param field
     * @param model
     * @return
     * @throws Exception
     */
    public static Object getFieldValue(Field field, Object model) throws Exception {
        return field.get(model);

    }

    /**
     * find field from class by passing string field name and find value.
     * @param fieldName
     * @param model
     * @return
     * @throws Exception
     */
    public static Object getFieldValue(String fieldName, Object model) throws Exception {
        Field field = getField(fieldName, model.getClass());
        return field.get(model);

    }

    /**
     * return value related to parameter.
     * @param parameter
     * @param model
     * @return
     * @throws Exception
     */
    public static Object getParamValue(CQueryParameter parameter, Object model) throws Exception{
        if (parameter.isParamType()) {
            return parameter.getValue();
        }
        return getFieldValue(parameter.getField(), model);
    }

    /**
     * check is valid field in class.
     * if field is not exists / if field value is null return false.
     * @param fieldName
     * @param model
     * @return
     * @throws Exception
     */
    public static boolean validField(String fieldName, Object model) throws Exception {
        if (ToolKit.isEmpty(fieldName)) {
            return false;
        }

        Field field = getField(fieldName, model.getClass());
        if (!CQueryUtil.allowedField(field)) {
            return false;
        }
        Object value = getFieldValue(field, model);
        if (null == value) {
            return false;
        }

        return true;
    }

    /**
     * validate param value is valid.
     * @param parameter
     * @param model
     * @return
     * @throws Exception
     */
    public static boolean isValidParamMapValue(CQueryParameter parameter, Object model) throws Exception{
        if (parameter.isParamType()) {
            if (null != parameter.getValue()) {
                return true;
            }
            return false;
        }
        return validField(parameter.getField(), model);
    }

    /**
     * check allowed field.
     *
     * @param field
     * @return
     */
    public static boolean allowedField(Field field) {
        return true;
    }

    public static boolean allowedToWhere(Field field, Object model) {
        if (allowedField(field)) {
            try {
                Object object = field.get(model);
                if (null != object) {
                    return true;
                }
            } catch (IllegalAccessException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * format parameter to sql parameter
     * if pass as name return :name
     * @param parameterName
     * @return
     */
    public static String formatToParameter(String parameterName) {
        if (!ToolKit.isEmpty(parameterName)) {
            return CQueryConstant.PARAM_PREFIX + parameterName;
        }
        return CQueryConstant.Symbol.EMPTY_STRING;
    }

    /**
     * return like string.
     * if isPrefix - '%' ||
     * else - || '%'
     * @param isPrefixLike
     * @return
     */
    public static String getLikeConcatString(boolean isPrefixLike) {
        return oracleConcat(CQueryConstant.QueryConstant.LIKE_SYMBOL, !isPrefixLike);
    }

    public static String oracleConcat(String value, boolean concatFromPrefix) {
        return concatFromPrefix ? CQueryConstant.QueryConstant.CONCAT + value :
                value + CQueryConstant.QueryConstant.CONCAT;
    }
}
