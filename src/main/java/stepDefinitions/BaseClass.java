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
            throw new Exception("Incorrect data displayed while trying to fetch Car main types using parameter " + pathParameter);
        }
    }

    public BuiltYear getCarBuiltYears(String pathParameter) throws Exception {
        response = getResponse(pathParameter);
        try {
            return new BuiltYear(response.jsonPath().get("page"), response.jsonPath().get("pageSize"),
                    response.jsonPath().get("totalPageCount"), response.jsonPath().get("wkda"));
        } catch (ClassCastException e) {
            throw new Exception("Incorrect data displayed while trying to fetch Car Built years using parameter " + pathParameter);
        }

    }

    public List<String> getCarMainTypesInList(Map<String, String> wkda) {
        List<String> allCarTypes = new ArrayList<>();
        for (Map.Entry<String, String> entry : wkda.entrySet()) {
            allCarTypes.add(entry.getKey());
        }
        return allCarTypes;
    }

    public Response getResponse(String pathParameter) throws Exception {
        RestAssured.baseURI = baseURI;
        requestSpec = RestAssured.given();
        response = requestSpec.request(Method.GET, pathParameter);
        if (validateStatusCode(response, pathParameter)) {
            return response;
        } else {
            return null;
        }
    }

    public boolean validateStatusCode(Response response, String parameter) throws Exception {
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            return true;
        } else {
            throw new Exception("Retrieved response StatusCode - " + statusCode + " and error - " + response.jsonPath().get("error").toString()
                    + ". Please look into parameter - " + parameter);
        }
    }
}
