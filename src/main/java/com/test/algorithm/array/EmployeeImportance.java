package com.test.algorithm.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 690. 员工的重要性
 * 给定一个保存员工信息的数据结构，它包含了员工唯一的id，重要度 和 直系下属的id。
 * <p>
 * 比如，员工1是员工2的领导，员工2是员工3的领导。他们相应的重要度为15, 10, 5。那么员工1的数据结构是[1, 15, [2]]，员工2的数据结构是[2, 10, [3]]，员工3的数据结构是[3, 5, []]。注意虽然员工3也是员工1的一个下属，但是由于并不是直系下属，因此没有体现在员工1的数据结构中。
 * <p>
 * 现在输入一个公司的所有员工信息，以及单个员工id，返回这个员工和他所有下属的重要度之和。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
 * 输出: 11
 * 解释:
 * 员工1自身的重要度是5，他有两个直系下属2和3，而且2和3的重要度均为3。因此员工1的总重要度是 5 + 3 + 3 = 11。
 * 注意:
 * <p>
 * 一个员工最多有一个直系领导，但是可以有多个直系下属
 * 员工数量不超过2000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/employee-importance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author: xiaoqiangqiang
 * @date: 2019/11/1
 */
public class EmployeeImportance {
    static class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    }

    ;

    static class Solution {
        //第一次写
        public int getImportance(List<Employee> employees, int id) {
            if (employees.isEmpty()) {
                return 0;
            } else {
                int count = 0;
                HashMap<Integer, Employee> collect = new HashMap<>();
                for (Employee employee : employees) {
                    if (!collect.containsKey(employee.id)) {
                        collect.put(employee.id, employee);
                    }
                }
                if (collect.containsKey(id)) {
                    Employee employee = collect.get(id);
                    count += employee.importance;
                    count += countImportance(employee.subordinates, collect);
                    return count;
                } else {
                    return 0;
                }
            }
        }

        public int countImportance(List<Integer> subordinates, Map<Integer, Employee> collect) {
            int count = 0;
            if (subordinates.isEmpty()) {
                return 0;
            } else {
                for (Integer subordinate : subordinates) {
                    Employee employee1 = collect.get(subordinate);
                    count += employee1.importance;
                    count += countImportance(employee1.subordinates, collect);
                }
            }
            return count;
        }

        public int getImportance1(List<Employee> employees, int id) {
            if (employees.isEmpty()) {
                return 0;
            }
            for (Employee employee : employees) {
                if (employee.id == id) {
                    int sum = employee.importance;
                    if (!employee.subordinates.isEmpty()) {
                        for (Integer subId : employee.subordinates) {
                            sum += getImportance1(employees, subId);
                        }
                    }
                    return sum;
                }
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);
        employee.subordinates = list;
        employee.id = 1;
        employee.importance = 5;
        employees.add(employee);
        for (int i = 0; i < 2; i++) {
            Employee employee1 = new Employee();
            employee1.subordinates = new ArrayList<>();
            employee1.id = i + 2;
            employee1.importance = 3;
            employees.add(employee1);
        }
        System.out.println(new Solution().getImportance1(employees, 2));
    }
}
