package specs;

import helpers.CustomAllureListener;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ApiSpecs {

    public static RequestSpecification requestSpecDemoWebShop = new RequestSpecBuilder()
            .setBaseUri("https://demowebshop.tricentis.com")
            .setContentType("application/x-www-form-urlencoded; charset=UTF-8")
            .addFilter(CustomAllureListener.withCustomTemplates())
            .build();
}
