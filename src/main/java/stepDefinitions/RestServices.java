package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.util.Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestServices extends BaseClass {

    public String wa_key = "coding-puzzle-client-449cc9d";
    public String carTypes = "/main-types";
    public String builtYear = "/built-dates";
    public String carBrandCode, carBrandName, carModelName;
    public static Map<String, CarTypes> carModelType = new HashMap<>();
    public static Map<String, BuiltYear> carModelBuiltYears = new HashMap<>();
    List<String> carTypesList = new ArrayList<>();
    public static Map<String, Map<String, BuiltYear>> allCarBuiltYears = new HashMap<>();
    public String pathParameter;
    public CarBrands availableCarBrands;

    @Given("the base url {string} to sell my car")
    public void givenEndpoint(String baseurl) {
        baseURI = baseurl;
    }

    @When("user should able to view available Car brands in the market with parameter {string}")
    public void retrieveDifferentCarBrands(String manufacturer) throws Exception {
        pathParameter = manufacturer + "?wa_key=" + wa_key;
        storeCarBrands(pathParameter);
    }

    @And("user confirms if all Car brands are displayed")
    public void checkIfAllCarBrandsPresent() throws Exception {
        for (Map.Entry<String, String> entry : availableCarBrands.getWkda().entrySet()) {
            carBrandCode = entry.getKey();
            carBrandName = entry.getValue();
            if (Strings.isNullOrEmpty(carBrandName) || carBrandName.contains("[0-9]"))
                throw new Exception("Car Brand for " + carBrandCode + " is empty or contains numeric values. " + "Actual value is " + carBrandName);
        }
    }

    @And("user checks if page, pageSize, totalPageCount and wkda values are not empty")
    public void checkCarBrandsResponse() throws Exception {
        if (Strings.isNullOrEmpty(String.valueOf(availableCarBrands.getPage()))) {
            throw new Exception("Page value for Car Brands response is empty");
        }
        if (Strings.isNullOrEmpty(String.valueOf(availableCarBrands.getPageSize()))) {
            throw new Exception("Page size value for Car Brands response is empty");
        }
        if (availableCarBrands.getTotalPageCount() != 1) {
            throw new Exception("Total Page Count value for Car Brands response mismatches");
        }
        if (availableCarBrands.getWkda().size() == 0) {
            throw new Exception("No Car Brands displayed");
        }
    }

    @And("user should able to view Car types for each Brands")
    public void getCarTypesForAllCars() throws Exception {
        for (Map.Entry<String, String> entry : availableCarBrands.getWkda().entrySet()) {
            carBrandCode = entry.getKey();
            carBrandName = entry.getValue();
            pathParameter = carTypes + "?manufacturer=" + carBrandCode + "&wa_key=" + wa_key;
            carModelType.put(carBrandName, getCarMainTypes(pathParameter));
        }
    }

    @And("user should able to view Built Year for each Car Brands")
    public void getBuiltYearForAllCarBrands() throws Exception {
        for (Map.Entry<String, String> carBrand : availableCarBrands.getWkda().entrySet()) {
            carBrandCode = carBrand.getKey();
            carBrandName = carBrand.getValue();
            carTypesList = getCarMainTypesInList(carModelType.get(carBrandName).getWkda());
            carModelBuiltYears = new HashMap<>();
            for (int i = 0; i < carTypesList.size(); i++) {
                carModelName = carTypesList.get(i);
                pathParameter = builtYear + "?manufacturer=" + carBrandCode + "&main-type=" + carModelName + "&wa_key=" + wa_key;
                carModelBuiltYears.put(carModelName, getCarBuiltYears(pathParameter));
            }
            allCarBuiltYears.put(carBrandName, carModelBuiltYears);
        }
    }

    @Then("user checks if Car types available for all Car Brands")
    public void validateCarTypesForAllCarBrands() throws Exception {
        for (Map.Entry<String, CarTypes> entry : carModelType.entrySet()) {
            carBrandName = entry.getKey();
            CarTypes carType = entry.getValue();
            if (carType.getWkda().size() == 0) {
                throw new Exception("No Car types displayed when user tries to select Car - " + carBrandName);
            }
        }
    }

    @And("user checks if page, pageSize, totalPageCount in Car types for all Brands")
    public void checkPagePageSizeAndCountInCarTypes() throws Exception {
        for (Map.Entry<String, CarTypes> entry : carModelType.entrySet()) {
            carBrandName = entry.getKey();
            CarTypes carTypes = entry.getValue();
            if (Strings.isNullOrEmpty(String.valueOf(carTypes.getPage()))) {
                throw new Exception("Page value in car types is not displayed when user selects car - " + carBrandName);
            }
            if (Strings.isNullOrEmpty(String.valueOf(carTypes.getPageSize()))) {
                throw new Exception("PageSize value in car types is not displayed when user selects car - " + carBrandName);
            }
            if (carTypes.getTotalPageCount() != 1) {
                throw new Exception("TotalPageCount value in car types is not displayed when user selects car - " + carBrandName);
            }
        }
    }

    @And("user checks if car Built years page, pageSize, totalPageCount displayed for all Brands")
    public void checkCarBuiltYearsInEachModelForAllCars() {
        for (Map.Entry<String, String> carBrand : availableCarBrands.getWkda().entrySet()) {
            carBrandName = carBrand.getValue();
            Map<String, BuiltYear> builtYearData = allCarBuiltYears.get(carBrandName);
            for (Map.Entry<String, BuiltYear> builtYear : builtYearData.entrySet()) {
                BuiltYear carModelBuiltYear = builtYear.getValue();
                carModelName = builtYear.getKey();
                if (String.valueOf(carModelBuiltYear.getPage()).matches("[0-9]*")) {

                }
            }
        }
    }

    public void storeCarBrands(String pathParameter) {
        availableCarBrands = new CarBrands(getResponse(pathParameter).jsonPath().get("page"), getResponse(pathParameter).jsonPath().get("pageSize"),
                getResponse(pathParameter).jsonPath().get("totalPageCount"), getResponse(pathParameter).jsonPath().get("wkda"));
    }

}
