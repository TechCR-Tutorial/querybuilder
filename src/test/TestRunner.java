package test;

import java.util.Map;

import com.querybuilder.operators.util.OperatorUtil;
import com.querybuilder.query.CQuery;
import com.querybuilder.util.types.CQueryCondition;
import com.querybuilder.util.types.CQueryConditionMap;

/**
 * @auther : chamly
 * @date : 3/4/15
 * @time : 11:17 PM
 */
public class TestRunner {
    public static void main(String[] args) {
        User user = new User("Chamly", "Idunil", "Rathnayaka");
        //user.setAge(10);
        CQuery query = new CQuery(user);
        query.setSelectFields("lastName", "age");

//        "select x from X where age < :age1 or age > :age2";
        query.addLogicalOperator(OperatorUtil.AND(CQueryCondition.EQUAL, "middleName"));
        query.addLogicalOperator(OperatorUtil.AND(CQueryCondition.EQUAL, "middleName", "midName", "Nilanthi"));
        query.addLogicalOperator(OperatorUtil.AND(CQueryCondition.LESS_THAN, "age"));
        query.addLogicalOperator(OperatorUtil.AND(CQueryCondition.WRAP_LIKE, "firstName"));
//
        CQueryConditionMap lastNameParam = OperatorUtil.getConditionMap(CQueryCondition.EQUAL, "lastName");
        CQueryConditionMap firstNameParam = OperatorUtil.getConditionMap(CQueryCondition.POSTFIX_LIKE, "firstName");
        CQueryConditionMap ageParam = OperatorUtil.getConditionMap(CQueryCondition.EQUAL, "age");
        query.addLogicalOperator(OperatorUtil.AND(OperatorUtil.OR(ageParam, firstNameParam, lastNameParam)));

        CQueryConditionMap ageGreat = OperatorUtil.getConditionMap(CQueryCondition.GREATER_THAN, "age", "age1", null);
        CQueryConditionMap ageLess = OperatorUtil.getConditionMap(CQueryCondition.LESS_THAN, "age", "age2", 10);
        query.addLogicalOperator(OperatorUtil.AND(OperatorUtil.OR(ageGreat, ageLess)));
        String qString = query.getSql();
        Map<String, Object> paramMap = query.getParameterMap();
        System.out.println(qString);
        for (String paramName : paramMap.keySet()) {
            System.out.println("__ ParamName - " + paramName + " : " + paramMap.get(paramName));
        }
    }
}
