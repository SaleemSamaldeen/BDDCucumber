package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class restServices {

    public String baseURI = "http://api-aws-eu-qa-1.auto1-test.com/v1/car-types";
    public String wa_key = "coding-puzzle-client-449cc9d";
    public String brandCode;
    public String carModelCode;
    public static Map<String, String> carBrand;
    public static Map<String, String> carModelType;
    public static Map<String, String> carBuiltYear;
    public String pathParameter;

    @Given("the base url {string} to sell my car")
    public void givenEndpoint(String baseurl) {
        baseURI = baseurl;
    }

    @When("user should able to view available Car brands in the market with parameter {string}")
    public void retrieveDifferentCarBrands(String manufacturer) throws Exception {
        pathParameter = manufacturer + "?wa_key=" + wa_key;
        storeResponse("manufacturer", pathParameter);
    }

    @And("user checks if all Car brands are displayed")
    public void checkIfAllCarBrandsPresent() {

    }

    @Then("I want to see available model of selected Car with parameter {string}")
    public void retrieveCarBrandList(String carTypes) throws Exception {
        for (Map.Entry<String, String> entry : carBrand.entrySet()) {
            brandCode = entry.getKey();
            pathParameter = carTypes + "?manufacturer=" + brandCode + "&wa_key=" + wa_key;
            storeResponse("carType", pathParameter);
            break;
        }
    }

    @Then("I want to select make of my Car with parameter {string}")
    public void selectMakeOfMyCar(String builtYear) throws Exception {
        for (Map.Entry<String, String> entry : carModelType.entrySet()) {
            carModelCode = entry.getKey();
            pathParameter = builtYear + "?manufacturer=" + brandCode + "&main-type=" + carModelCode + "&wa_key=" + wa_key;
            storeResponse("builtYear", pathParameter);
            break;
        }
    }

    public void storeResponse(String option, String pathParameter) throws Exception {
        RestAssured.baseURI = baseURI;
        RequestSpecification requestSpec = RestAssured.given();
        Response response = requestSpec.request(Method.GET, pathParameter);

        switch (option) {
            case "manufacturer":
                carBrand = response.jsonPath().get("wkda");
                break;
            case "carType":
                carModelType = response.jsonPath().get("wkda");
                break;
            case "builtYear":
                carBuiltYear = response.jsonPath().get("wkda");
                break;
            default:
                throw new Exception("Incorrect option option name is given");
        }

    }


    /**@And("I want to select my model of Car")
    public void retrieveCarBrandModel(String parameter){
    System.out.println("Endpoint: "+parameter);
    }**/


}
