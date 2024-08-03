package com.example.atomatedtest2.integration.swagger;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.atomatedtest2.configs.TestConfig;
import com.example.atomatedtest2.integration.containers.AbstractIntegrationTest;

@WebIntegrationTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest{
   
	@Test
    @DisplayName("JUnit test for Should Display Swagger UI Page")
    void testShouldDisplaySwaggerUiPage() {
        var content = given()
            .basePath("/swagger-ui/index.html")
            .port(TestConfig.SERVER_PORT)
            .when()
                .get()
            .then()
                .statusCode(200)
            .extract()
                .body()
                    .asString();
        assertTrue(content.contains("Swagger UI"));
    }
    

}
