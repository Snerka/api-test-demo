package com.dhimahi.apitest;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Valentino Kasner
 * @TestDescription Automation test suite for Brand API
 * @TestType API
 * @ExecutionType Automatic
 * <p>
 * Preconditions:
 * - The API server must be running.
 * <p>
 * Postconditions:
 * - Test results will validate API functionality.
 */

@Listeners({AllureTestNg.class})
public class BrandApiTests {

    private static final Logger logger = LogManager.getLogger(BrandApiTests.class);
    private String brandId;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = TestConstants.BASE_URI; // Set base URI once for all tests
    }

    /**
     * Helper method to add authorization headers to requests
     *
     * @return RequestSpecification object with authorization headers
     */
    private RequestSpecification givenWithAuthorization() {
        return given()
                .header("Authorization", "Bearer " + TestConstants.TOKEN)
                .header("accept", "application/json")
                .header("Content-Type", "application/json");
    }

    /**
     * @TestDescription POST - Test case to create a new brand
     * @ExecutionType Automatic
     * @Precondition The server is up, and the /brands endpoint is reachable.
     * @TestStep Send POST request to create a new brand.
     * @TestStepResult The new brand should be created successfully.
     * @PostCondition A new brand is added to the system.
     */
    @Test(priority = 1)
    @Description("POST - Test case to create a new brand")
    public void createNewBrand() {
        logger.info("Attempting to create a new brand.");
        Allure.step("Sending POST request to create a new brand");
        Response response = givenWithAuthorization()
                .body(TestConstants.NEW_BRAND_JSON)
                .when()
                .post("/brands")
                .then()
                .log().all() // Log all details for both success and error responses
                .statusCode(201) // Expecting a 201 Created response
                .extract().response();

        // Capture the brand ID from the response
        brandId = response.jsonPath().getString("id");
        logger.info("Brand created successfully with ID: " + brandId);
        attachResponse("Create Brand Response", response.asString());
    }

    /**
     * @TestDescription GET - Validates that the newly created brand exists
     * @ExecutionType Automatic
     * @Precondition The brand has been created - TC createNewBrand().
     * @TestStep Send GET request to retrieve the newly created brand.
     * @TestStepResult The brand should be fetched successfully.
     */
    @Test(priority = 2)
    @Description("GET - Validates that the newly created brand exists")
    public void validateBrandCreation() {
        logger.info("Validating that the newly created brand exists.");
        Allure.step("Sending GET request to validate the newly created brand");

        Response response = given()
                .when()
                .get("/brands/" + brandId) // Use the captured brand ID
                .then()
                .log().all() // Log all details for both success and error responses
                .statusCode(200)
                .body("name", equalTo(TestConstants.NEW_BRAND_NAME)) // Validate the brand name in the response
                .body("slug", equalTo(TestConstants.NEW_BRAND_SLUG)) // Validate the brand slug update
                .extract().response();

        logger.info("Brand fetched successfully: " + response.asString());
        attachResponse("Validate Brand Response", response.asString());
    }

    /**
     * @TestDescription PUT - Updates an existing brand
     * @ExecutionType Automatic
     * @Precondition The brand already exists.
     * @TestStep Send PUT request to update the brand details.
     * @TestStepResult The brand should be updated successfully.
     * @PostCondition The updated brand is available in the system.
     */
    @Test(priority = 3)
    @Description("PUT - Updates an existing brand")
    public void updateBrand() {
        logger.info("Attempting to update the previously created brand.");
        Allure.step("Sending PUT request to update the brand details");

        Response response = givenWithAuthorization()
                .body(TestConstants.UPDATED_BRAND_JSON) // Use constant for updated brand JSON
                .when()
                .put("/brands/" + brandId) // Update the correct brand
                .then()
                .log().all() // Log all details for both success and error responses
                .statusCode(200) // Expecting a 200 OK response
                .body("success", equalTo(true)) // Validate the brand name update
                .extract().response();

        logger.info("Brand updated successfully: " + response.asString());
        attachResponse("Update Brand Response", response.asString());
    }

    @Attachment(value = "{0}", type = "text/plain")
    public String attachResponse(String name, String response) {
        return response;
    }

}
