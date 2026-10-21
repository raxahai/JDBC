package org.example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

record Employee(
        long id,
        String name,
        BigDecimal salary,
        long department_id
) {
}

class AggregateState {
    long count;
    BigDecimal salary = BigDecimal.ZERO;
}

public class Custom {
    public static void main(String[] args) {
        Map<Long, AggregateState> map = new HashMap<>();
        List<Employee> employees = List.of(
                new Employee(1L, "Syed Raza", BigDecimal.valueOf(10000.012), 10),
                new Employee(1L, "Syed Irteza", BigDecimal.valueOf(990.012), 20),
                new Employee(1L, "Syed Muzamil", BigDecimal.valueOf(1200.012), 10),
                new Employee(1L, "Syed Hussain", BigDecimal.valueOf(129320.1), 10)
        );

        for (Employee e : employees) {
            long key = e.department_id();
            AggregateState state = map.computeIfAbsent(key, (k) -> new AggregateState());
            state.count++;
            state.salary = state.salary.add(e.salary());
        }

        for (var group : map.entrySet()) {
            System.out.println(
                    "department_id: " + group.getKey() +
                            "\n count: " + group.getValue().count +
                            "\n salary: " + group.getValue().salary
            );
        }
    }
}
