package stepDefinitions;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseClass {

    public Response response;
    public RequestSpecification requestSpec;
    public String baseURI;

    public CarTypes getCarMainTypes(String pathParameter) throws Exception {
        response = getResponse(pathParameter);
        try {
            return new CarTypes(response.jsonPath().get("page"), response.jsonPath().get("pageSize"),
                    response.jsonPath().get("totalPageCount"), response.jsonPath().get("wkda"));
        } catch (ClassCastException e) {
            throw new Exception("Incorrect data displayed while trying to fetch Car main types");
        }
    }

    public BuiltYear getCarBuiltYears(String pathParameter) throws Exception {
        response = getResponse(pathParameter);
        try {
            return new BuiltYear(response.jsonPath().get("page"), response.jsonPath().get("pageSize"),
                    response.jsonPath().get("totalPageCount"), response.jsonPath().get("wkda"));
        } catch (ClassCastException e) {
            throw new Exception("Incorrect data displayed while trying to fetch Car Built years");
        }

    }

    public List<String> getCarMainTypesInList(Map<String, String> wkda) {
        List<String> allCarTypes = new ArrayList<>();
        for (Map.Entry<String, String> entry : wkda.entrySet()) {
            allCarTypes.add(entry.getKey());
        }
        return allCarTypes;
    }

    public Response getResponse(String pathParameter) {
        RestAssured.baseURI = baseURI;
        requestSpec = RestAssured.given();
        return requestSpec.request(Method.GET, pathParameter);
    }
}
