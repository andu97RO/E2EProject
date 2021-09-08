package api;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.*;

import static io.restassured.RestAssured.given;


public class ApiHelper {

    String url;
    Response response;

    public ApiHelper(String url){
        this.url = url;
    }


    public Response getAllEmployee(){
        RestAssured.baseURI = url;
        response = given().
                    header("Content-Type", "application/json")
                    .when().log().all()
                    .get("/api/v1/employees").then()
                    .log().all().extract().response();

        response.then().assertThat().statusCode(200);

        return response;
    }

    public void getEmployeeById(String id){
        RestAssured.baseURI = url;
        response = given().
                header("Content-Type", "application/json")
                .when().log().all()
                .get("/api/v1/employee/" + id).then()
                .log().all().extract().response();

        response.then().assertThat().statusCode(200);

    }


    public void createEmployee(String body){
        RestAssured.baseURI = url;
        response = given().
                header("Content-Type", "application/json")
                .body(body)
                .when().log().all()
                .post("/api/v1/create").then()
                .log().all().extract().response();

        response.then().assertThat().statusCode(200);

    }


    public void updateEmployee(int idEmployee, String body){
        RestAssured.baseURI = url;
        response = given().
                header("Content-Type", "application/json")
                .body(body)
                .when().log().all()
                .put("/api/v1/update/" + idEmployee).then()
                .log().all().extract().response();

        response.then().assertThat().statusCode(200);

    }

    public void deleteEmployee(int idEmployee){
        RestAssured.baseURI = url;
        response = given().
                header("Content-Type", "application/json")
                .when().log().all()
                .delete("/api/v1/delete/" + idEmployee).then()
                .log().all().extract().response();

        response.then().assertThat().statusCode(200);

    }


    public List<Integer> employeeHigher() {
        RestAssured.baseURI = url;
        List<Integer> list = new LinkedList<Integer>();
        response = this.getAllEmployee();
        String jsonValid = response.asString();

        Integer data = JsonPath.read(jsonValid, "$.data.length()");
        for (int i = 0; i < data; i++) {
            int value = JsonPath.read(jsonValid, "$.data.[" + i +"].employee_age");
            list.add(value);
        }
        System.out.println(list);

        list.removeIf(element -> (element < 30));
        System.out.println(list);

        return list;
    }


    public int getIDOfEmployeeByName(String name){
        RestAssured.baseURI = url;
        int idEmployee = 0;
        response = this.getAllEmployee();

        String jsonValid = response.asString();
        Integer data = JsonPath.read(jsonValid, "$.data.length()");
        for (int i = 0; i < data; i++) {
            String employee_name = JsonPath.read(jsonValid, "$.data[" + i + "].employee_name");
            if (name.equals(employee_name)){
                idEmployee = JsonPath.read(jsonValid, "$.data[" + i + "].id");
            }
        }

        return idEmployee;
    }


}
