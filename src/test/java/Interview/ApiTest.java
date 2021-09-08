package Interview;

import api.ApiHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.base;

import java.io.IOException;
import java.util.List;

public class ApiTest extends base {
    public static Logger log = LogManager.getLogger(EmagTestUI.class.getName());
    public ApiHelper apiHelper;
    public String url;
    public List<Integer> list;

    @BeforeTest
    public void initialize() throws IOException {
        initializeFile();
        url = prop.getProperty("urlApi");
    }


    @Test
    public void testAPI_1() {

        apiHelper = new ApiHelper(url);
        list = apiHelper.employeeHigher();
        Assert.assertEquals(list.size(), 18, "List is not as expected.");
        log.info("Successfully retrieves all employees and counts the number of employees with age number higher than 30");

    }


    @Test
    public void testAPI_2() {
        apiHelper = new ApiHelper(url);
        String body = "{\n" +
                "            \"employee_name\": \"Bogdan Andrei\",\n" +
                "            \"employee_salary\": 320800,\n" +
                "            \"employee_age\": 34,\n" +
                "        }";
        apiHelper.createEmployee(body);
        list = apiHelper.employeeHigher();
        Assert.assertEquals(list.size(), 19, "List is not as expected.");
        log.info("* successfully adds new employee with age higher than 30 and assert that operation is successful");

    }

    @Test
    public void testAPI_3(){
        apiHelper = new ApiHelper(url);
        String name = "Tiger Nixon";
        String bodyUpdate = "{\n" +
                "            \"employee_name\": \"Tiger Nixon\",\n" +
                "            \"employee_salary\": 320800,\n" +
                "            \"employee_age\": 22,\n" +
                "            \"profile_image\": \"\"\n" +
                "        },";

        int idEmployee = apiHelper.getIDOfEmployeeByName(name);
        apiHelper.updateEmployee(idEmployee, bodyUpdate);
        list = apiHelper.employeeHigher();
        Assert.assertEquals(list.size(), 19, "List is not as expected.");
        log.info("* successfully updates the employee and asserts that operation is successful\n" +
                "* successfully retrieves all employees and asserts that employees with age number higher than 30 has modified");
    }

    @Test
    public void testAPI_4(){

        String name = "Bogdan Andrei";
        apiHelper = new ApiHelper(url);

        int idEmployee = apiHelper.getIDOfEmployeeByName(name);
        apiHelper.deleteEmployee(idEmployee);

        list = apiHelper.employeeHigher();
        Assert.assertEquals(list.size(), 18, "List is not as expected.");
        log.info("* successfully deletes the employee that he added and asserts the operation is successful");
    }

}

