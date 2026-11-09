
package workshop.task_2_2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingDouble;

public class Exercise2OrdersTest extends CompanyDomain {

    @Test
    public void totalOrderValuesByCity() {
        // Aggregate the total order values by city.

        Map<String, Double> map = company.getCustomers().stream()
                .collect(groupingBy(
                        Customer::getCity,
                        summingDouble(Customer::getTotalOrderValue)
                ));

        Assert.assertEquals(2, map.size());
        Assert.assertEquals(446.25, map.get("London"), 0.0);
        Assert.assertEquals(857.0, map.get("Liphook"), 0.0);
    }

    @Test
    public void mostExpensiveItem() {
        // Group customers by the value of their most expensive item.

        Map<Double, List<Customer>> map = company.getCustomers().stream()
                .collect(groupingBy(Customer::getMostExpensiveItemValue));

        Assert.assertEquals(2, map.size());
        Assert.assertEquals(2, map.entrySet().size());

        Assert.assertEquals(
                Arrays.asList(
                        company.getCustomerNamed("Fred"),
                        company.getCustomerNamed("Bill")
                ),
                map.get(50.0)
        );
    }
}
