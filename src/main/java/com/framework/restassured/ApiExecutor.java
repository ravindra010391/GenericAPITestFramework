/**
 * 
 */
package com.framework.restassured;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.LinkedHashMap;
import org.apache.http.util.TextUtils;
import com.framework.constants.Constants.ApiExecutorConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * @author nikhil
 *
 */

public class ApiExecutor {
	APIExecutorHelper apiExecutorHelperObj = new APIExecutorHelper();
	RestAssuredConfig config = RestAssured.config()
			.httpClient(HttpClientConfig.httpClientConfig().setParam("CONNECTION_MANAGER_TIMEOUT", 3000));

	/*
	 * Rest-Assured is using given/when/then notation. In below section we
	 * declare things like content type or request body. below method is use for
	 * getapi.
	 */
	public Response apiGet(LinkedHashMap<String, String> data, ExtentTest extentTest) {
		String url = apiExecutorHelperObj.getUrl(data);
		extentTest.log(LogStatus.INFO, "URL-->" + url);
		extentTest.log(LogStatus.INFO, "Input json" + data.get("input json"));
		Response resp = null;
		try {
			RequestSpecification rs = given().contentType(ApiExecutorConstants.CONTENTTYPE.toString())
					.body(data.get("input json"));

			if (!TextUtils.isEmpty(data.get("headers"))) {
				extentTest.log(LogStatus.INFO, "Headers-->" + apiExecutorHelperObj.setHeaders(data));
				rs = rs.given().headers(apiExecutorHelperObj.setHeaders(data));
			}

			resp = rs.when().get(url).then().extract().response();
			extentTest.log(LogStatus.INFO, "Response" + resp.asString());
			long executionTimeInMillis = resp.getTime();
			apiExecutorHelperObj.printTestExecutionTime(executionTimeInMillis, extentTest);
			if (!TextUtils.isEmpty(data.get("output"))) {
				assertThat(resp.asString(), matchesJsonSchemaInClasspath(data.get("output")));
			}
		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "Fail " + e.getMessage());
			org.testng.Assert.fail("*******************************api_Get_schemavalidator " + e.getMessage());
		}
		return resp;

	}

	/*
	 * Rest-Assured is using given/when/then notation. In below section we
	 * declare things like content type or request body. below method is use for
	 * postapi.
	 */

	public Response apiPost(LinkedHashMap<String, String> data, ExtentTest extentTest) {
		String url = apiExecutorHelperObj.getUrl(data);
		Response resp = null;
		try {
			extentTest.log(LogStatus.INFO, "URL-->" + url);
			extentTest.log(LogStatus.INFO, "Input json" + data.get("input json"));
			RequestSpecification rs = given().contentType(ApiExecutorConstants.CONTENTTYPE.toString())
					.body(data.get("input json"));
			if (!TextUtils.isEmpty(data.get("headers"))) {
				extentTest.log(LogStatus.INFO, "Headers-->" + apiExecutorHelperObj.setHeaders(data));
				rs = rs.given().headers(apiExecutorHelperObj.setHeaders(data));
			}

			resp = rs.when().post(url).then().extract().response();
			extentTest.log(LogStatus.INFO, "Response" + resp.asString());
			long executionTimeInMillis = resp.getTime();
			apiExecutorHelperObj.printTestExecutionTime(executionTimeInMillis, extentTest);
			if (!TextUtils.isEmpty(data.get("output"))) {
				assertThat(resp.asString(), matchesJsonSchemaInClasspath(data.get("output")));
			}

		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "Fail " + e.getMessage());
			org.testng.Assert.fail("*******************************api_Post_schemavalidator: " + e.getMessage());
		}
		return resp;
	}

	/*
	 * Rest-Assured is using given/when/then notation. In below section we
	 * declare things like content type or request body. below method is use for
	 * putapi.
	 */

	public Response apiPut(LinkedHashMap<String, String> data, ExtentTest extentTest) {
		String url = apiExecutorHelperObj.getUrl(data);
		extentTest.log(LogStatus.INFO, "URL-->" + url);
		Response resp = null;
		try {
			extentTest.log(LogStatus.INFO, "Input json" + data.get("input json"));
			RequestSpecification rs = given().contentType(ApiExecutorConstants.CONTENTTYPE.toString())
					.body(data.get("input json"));
			if (!TextUtils.isEmpty(data.get("headers"))) {
				extentTest.log(LogStatus.INFO, "Headers-->" + apiExecutorHelperObj.setHeaders(data));
				rs = rs.given().headers(apiExecutorHelperObj.setHeaders(data));
			}
			resp = rs.when().put(url).then().extract().response();
			extentTest.log(LogStatus.INFO, "Response" + resp.asString());
			long executionTimeInMillis = resp.getTime();
			apiExecutorHelperObj.printTestExecutionTime(executionTimeInMillis, extentTest);
			if (!TextUtils.isEmpty(data.get("output"))) {
				assertThat(resp.asString(), matchesJsonSchemaInClasspath(data.get("output")));
			}

		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "Fail " + e.getMessage());
			org.testng.Assert.fail("*******************************apiPut:" + e.getMessage());
		}
		return resp;
	}

	/*
	 * Rest-Assured is using given/when/then notation. In below section we
	 * declare things like content type or request body. below method is use for
	 * deleteapi.
	 */

	public Response apiDelete(LinkedHashMap<String, String> data, ExtentTest extentTest) {
		String url = apiExecutorHelperObj.getUrl(data);
		extentTest.log(LogStatus.INFO, "URL-->" + url);
		Response resp = null;
		try {
			extentTest.log(LogStatus.INFO, "Input json" + data.get("input json"));
			RequestSpecification rs = given().contentType(ApiExecutorConstants.CONTENTTYPE.toString())
					.body(data.get("input json"));
			if (!TextUtils.isEmpty(data.get("headers"))) {
				extentTest.log(LogStatus.INFO, "Headers-->" + apiExecutorHelperObj.setHeaders(data));
				rs = rs.given().headers(apiExecutorHelperObj.setHeaders(data));
			}
			resp = rs.when().delete(url).then().extract().response();
			extentTest.log(LogStatus.INFO, "Response" + resp.asString());
			long executionTimeInMillis = resp.getTime();
			apiExecutorHelperObj.printTestExecutionTime(executionTimeInMillis, extentTest);
			if (!TextUtils.isEmpty(data.get("output"))) {
				assertThat(resp.asString(), matchesJsonSchemaInClasspath(data.get("output")));
			}

		} catch (Exception e) {
			extentTest.log(LogStatus.FAIL, "Fail " + e.getMessage());
			org.testng.Assert.fail("*******************************apiDelete:" + e.getMessage());
		}
		return resp;
	}

}
