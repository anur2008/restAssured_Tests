package test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.hamcrest.Matcher;
import org.kissflow.qa.process.NewItemActivityPage;
import org.kissflow.qa.process.NewItemPage;
import org.kissflow.qa.process.ProcessEditorPage;
import org.kissflow.qa.process.ProcessFormEditor;
import org.kissflow.qa.process.ProcessHomePage;
import org.kissflow.qa.process.ProcessPermissionEditor;
import org.kissflow.qa.process.ProcessReportPage;
import org.kissflow.qa.process.ProcessWorkflowEditor;
import org.kissflow.qa.shared.AdminPage;
import org.kissflow.qa.shared.HomePage;
import org.kissflow.qa.shared.LoginPage;
import org.kissflow.qa.utils.KissflowPage;
import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.module.jsv.JsonSchemaValidator;

public class KFProcessAPISmokeTest extends KissflowPage {

	// RequestSpecification REQUEST = RestAssured.with();
	

	HashMap<String, String> jsonAsMap = new HashMap<String, String>();
	final String apiKeyUser = prop.getProperty("yahooApiKey");
	final String apiKeyAdmin = prop.getProperty("apiKey");
	// String apiKey = RestAssured.sessionId;
	// String apiKeyUser = RestAssured.rootPath;
	String acctId = RestAssured.rootPath;

	public String processId = null;
	public String processInstId = null;

	@BeforeTest
	public void setUp() {
		super.initialization();

	}

	@Test(priority = 1)
	public void completeProcessTest() throws Exception {

		String Admin = getJsonData("ProcessSmokeTest.json", "Admin");
		String timeZoneInd = getJsonData("ProcessSmokeTest.json", "timeZone1");
		String timeZonePacific = getJsonData("ProcessSmokeTest.json", "timeZone2");
		String LocaleInd = getJsonData("ProcessSmokeTest.json", "Locale");
		String FlowType = getJsonData("ProcessSmokeTest.json", "flowType");
		String FlowName = getJsonData("ProcessSmokeTest.json", "flowName");
		String TextFieldType = getJsonData("ProcessSmokeTest.json", "TextFieldData.FieldType");
		String TextFieldName = getJsonData("ProcessSmokeTest.json", "TextFieldData.FieldName");
		String TextFieldDefault = getJsonData("ProcessSmokeTest.json", "TextFieldData.Default");
		String TextFieldDefaultParam = getJsonData("ProcessSmokeTest.json", "TextFieldData.DefaultParam");
		String TextFieldRequired = getJsonData("ProcessSmokeTest.json", "TextFieldData.Required");
		String TextFieldRequiredParam = getJsonData("ProcessSmokeTest.json", "TextFieldData.RequiredParam");
		String TextAreaFieldType = getJsonData("ProcessSmokeTest.json", "TextAreaFieldData.FieldType");
		String TextAreaFieldName = getJsonData("ProcessSmokeTest.json", "TextAreaFieldData.FieldName");
		String TextAreaFieldValue = getJsonData("ProcessSmokeTest.json", "TextAreaFieldData.FieldValue");

		String NumberFieldType = getJsonData("ProcessSmokeTest.json", "NumberFieldData.FieldType");
		String NumberFieldName = getJsonData("ProcessSmokeTest.json", "NumberFieldData.FieldName");
		String NumberFieldValue = getJsonData("ProcessSmokeTest.json", "NumberFieldData.FieldValue");

		String CurrencyFieldType = getJsonData("ProcessSmokeTest.json", "CurrencyFieldData.FieldType");
		String CurrencyFieldName = getJsonData("ProcessSmokeTest.json", "CurrencyFieldData.FieldName");
		String CurrencyFieldValue = getJsonData("ProcessSmokeTest.json", "CurrencyFieldData.FieldValue");
		String ExpCurrencyFieldValue = getJsonData("ProcessSmokeTest.json", "CurrencyFieldData.ExpectedFieldValue");

		String DateFieldType = getJsonData("ProcessSmokeTest.json", "DateFieldData.FieldType");
		String DateFieldName = getJsonData("ProcessSmokeTest.json", "DateFieldData.FieldName");
		String DateFieldValue = getJsonData("ProcessSmokeTest.json", "DateFieldData.FieldValue");
		String ExpDateFieldValue = getJsonData("ProcessSmokeTest.json", "DateFieldData.ExpectedFieldValue");

		String DateAndTimeFieldType = getJsonData("ProcessSmokeTest.json", "DateAndTimeFieldData.FieldType");
		String DateAndTimeFieldName = getJsonData("ProcessSmokeTest.json", "DateAndTimeFieldData.FieldName");
		String DateAndTimeFieldValue = getJsonData("ProcessSmokeTest.json", "DateAndTimeFieldData.FieldValue");
		String ExpDateAndTimeFieldValue = getJsonData("ProcessSmokeTest.json",
				"DateAndTimeFieldData.ExpectedFieldValue");

		String YesNoFieldType = getJsonData("ProcessSmokeTest.json", "YesNoFieldData.FieldType");
		String YesNoFieldName = getJsonData("ProcessSmokeTest.json", "YesNoFieldData.FieldName");
		String YesNoFieldValue = getJsonData("ProcessSmokeTest.json", "YesNoFieldData.FieldValue");
		String ImageFieldType = getJsonData("ProcessSmokeTest.json", "ImageFieldData.FieldType");
		String ImageFieldName = getJsonData("ProcessSmokeTest.json", "ImageFieldData.FieldName");
		String ImageFieldValue = getJsonData("ProcessSmokeTest.json", "ImageFieldData.FieldValue");

		String RatingFieldType = getJsonData("ProcessSmokeTest.json", "RatingFieldData.FieldType");
		String RatingFieldName = getJsonData("ProcessSmokeTest.json", "RatingFieldData.FieldName");
		String RatingFieldValue = getJsonData("ProcessSmokeTest.json", "RatingFieldData.FieldValue");

		String DropdownFieldType = getJsonData("ProcessSmokeTest.json", "DropdownFieldData.FieldType");
		String DropdownFieldName = getJsonData("ProcessSmokeTest.json", "DropdownFieldData.FieldName");
		String DropdownListType = getJsonData("ProcessSmokeTest.json", "DropdownFieldData.listType");
		String DropdownListName = getJsonData("ProcessSmokeTest.json", "DropdownFieldData.ListName");
		String DropdownFieldValue = getJsonData("ProcessSmokeTest.json", "DropdownFieldData.FieldValue");

		String CheckboxFieldType = getJsonData("ProcessSmokeTest.json", "CheckboxFieldData.FieldType");
		String CheckboxFieldName = getJsonData("ProcessSmokeTest.json", "CheckboxFieldData.FieldName");
		String CheckboxFieldValue = getJsonData("ProcessSmokeTest.json", "CheckboxFieldData.FieldValue");

		String SliderFieldType = getJsonData("ProcessSmokeTest.json", "SliderFieldData.FieldType");
		String SliderFieldName = getJsonData("ProcessSmokeTest.json", "SliderFieldData.FieldName");
		String SliderMin = getJsonData("ProcessSmokeTest.json", "SliderFieldData.Min");
		String SliderMinValue = getJsonData("ProcessSmokeTest.json", "SliderFieldData.MinValue");
		String SliderMax = getJsonData("ProcessSmokeTest.json", "SliderFieldData.Max");
		String SliderMaxValue = getJsonData("ProcessSmokeTest.json", "SliderFieldData.MaxValue");
		String SliderInterval = getJsonData("ProcessSmokeTest.json", "SliderFieldData.IntervalSize");
		String SliderIntervalValue = getJsonData("ProcessSmokeTest.json", "SliderFieldData.IntervalValue");
		String SliderFieldValue = getJsonData("ProcessSmokeTest.json", "SliderFieldData.FieldValue");

		String UserFieldType = getJsonData("ProcessSmokeTest.json", "UserFieldData.FieldType");
		String UserFieldName = getJsonData("ProcessSmokeTest.json", "UserFieldData.FieldName");
		String UserFieldValue = getJsonData("ProcessSmokeTest.json", "UserFieldData.FieldValue");

		String ChecklistFieldType = getJsonData("ProcessSmokeTest.json", "ChecklistFieldData.FieldType");
		String ChecklistFieldName = getJsonData("ProcessSmokeTest.json", "ChecklistFieldData.FieldName");
		String ChecklistFieldValue = getJsonData("ProcessSmokeTest.json", "ChecklistFieldData.FieldValue");

		String AttachmentFieldType = getJsonData("ProcessSmokeTest.json", "AttachmentFieldData.FieldType");
		String AttachmentFieldName = getJsonData("ProcessSmokeTest.json", "AttachmentFieldData.FieldName");
		String AttachmentFieldValue = getJsonData("ProcessSmokeTest.json", "AttachmentFieldData.FieldValue");

		String SignFieldType = getJsonData("ProcessSmokeTest.json", "SignFieldData.FieldType");
		String SignFieldName = getJsonData("ProcessSmokeTest.json", "SignFieldData.FieldName");

		String LookupFieldType = getJsonData("ProcessSmokeTest.json", "LookupFieldData.FieldType");
		String LookupFieldName = getJsonData("ProcessSmokeTest.json", "LookupFieldData.FieldName");

		String LookedupFieldParam = getJsonData("ProcessSmokeTest.json", "LookupFieldData.LookedUpParam");
		String LookedupFieldSource = getJsonData("ProcessSmokeTest.json", "LookupFieldData.LookedUpSource");

		String LookedupField = getJsonData("ProcessSmokeTest.json", "LookupFieldData.LookedUpField");
		String LookedupFieldValue = getJsonData("ProcessSmokeTest.json", "LookupFieldData.LookedUpFieldValue");

		String RemoteLookupFieldType = getJsonData("ProcessSmokeTest.json", "RemoteLookupFieldData.FieldType");
		String RemoteLookupFieldName = getJsonData("ProcessSmokeTest.json", "RemoteLookupFieldData.FieldName");

		String RemoteLookedupFieldParam = getJsonData("ProcessSmokeTest.json",
				"RemoteLookupFieldData.RemoteLookedUpParam");
		String RemoteLookedupFieldSource = getJsonData("ProcessSmokeTest.json",
				"RemoteLookupFieldData.RemoteLookedUpSource");

		String RemoteLookedupField = getJsonData("ProcessSmokeTest.json", "RemoteLookupFieldData.RemoteLookedUpField");
		String RemoteLookedupFieldValue = getJsonData("ProcessSmokeTest.json",
				"RemoteLookupFieldData.ReLookedUpFieldValue");

		String WorkflowName1 = getJsonData("ProcessSmokeTest.json", "WorkflowData.WorkflowName1");
		String WorkflowName2 = getJsonData("ProcessSmokeTest.json", "WorkflowData.WorkflowName2");

		String Approver1 = getJsonData("ProcessSmokeTest.json", "WorkflowData.Approver1");
		String Approver2 = getJsonData("ProcessSmokeTest.json", "WorkflowData.Approver2");

		String Formula1 = getJsonData("ProcessSmokeTest.json", "WorkflowData.Formula1");
		String Formula2 = getJsonData("ProcessSmokeTest.json", "WorkflowData.Formula2");
		String ReadonlyPermission = getJsonData("ProcessSmokeTest.json", "Permission.ReadOnly");

		String Sendbackmess = getJsonData("ProcessSmokeTest.json", "SendingBackMess");
		String ReassignMess = getJsonData("ProcessSmokeTest.json", "ReassigningMess");
		String RejectMess = getJsonData("ProcessSmokeTest.json", "RejectMess");
		String WithdrawMess = getJsonData("ProcessSmokeTest.json", "WithdrawMess");

		LoginPage lg = new LoginPage();
		NewItemActivityPage newItemNewPg = new NewItemActivityPage();
		ProcessReportPage prp = new ProcessReportPage();
		HomePage homePage = lg.login(Admin);
		String hmTitle = homePage.getHomePageTitle();
		Assert.assertEquals(hmTitle, "Kissflow");
		AdminPage adpg = homePage.clickOnAdminLink();
		adpg.timeZoneChange(timeZoneInd);
		adpg.localeChange(LocaleInd);
		adpg.saveLocaleAndTimezoneChange();
		ProcessHomePage pg = homePage.createFlowNew(FlowType, FlowName);
		ProcessEditorPage pge = new ProcessEditorPage();
		String ProcessPgElement = pg.getProcessName();
		Assert.assertEquals(ProcessPgElement, FlowName);
		ProcessFormEditor pgfe = pge.navToFormEditor();
		HashMap<String, String> hm1 = new HashMap<String, String>();
		hm1.put(TextFieldDefault, TextFieldDefaultParam);
		hm1.put(TextFieldRequired, TextFieldRequiredParam);
		pgfe.createField(TextFieldType, TextFieldName, hm1);
		pgfe.clearUntitledField();

		HashMap<String, String> hm2 = new HashMap<String, String>();
		pgfe.createField(TextAreaFieldType, TextAreaFieldName, hm2);

		HashMap<String, String> hm3 = new HashMap<String, String>();
		pgfe.createField(NumberFieldType, NumberFieldName, hm3);

		HashMap<String, String> hm4 = new HashMap<String, String>();
		pgfe.createField(CurrencyFieldType, CurrencyFieldName, hm4);

		HashMap<String, String> hm5 = new HashMap<String, String>();
		pgfe.createField(DateFieldType, DateFieldName, hm5);

		HashMap<String, String> hm6 = new HashMap<String, String>();
		pgfe.createField(DateAndTimeFieldType, DateAndTimeFieldName, hm6);

		HashMap<String, String> hm7 = new HashMap<String, String>();
		pgfe.createField(YesNoFieldType, YesNoFieldName, hm7);

		HashMap<String, String> hm8 = new HashMap<String, String>();
		pgfe.createField(ImageFieldType, ImageFieldName, hm8);

		HashMap<String, String> hm9 = new HashMap<String, String>();
		pgfe.createField(RatingFieldType, RatingFieldName, hm9);

		HashMap<String, String> hm10 = new HashMap<String, String>();
		hm10.put(DropdownListType, DropdownListName);
		pgfe.createField(DropdownFieldType, DropdownFieldName, hm10);

		HashMap<String, String> hm11 = new HashMap<String, String>();
		hm11.put(DropdownListType, DropdownListName);
		pgfe.createField(CheckboxFieldType, CheckboxFieldName, hm11);

		HashMap<String, String> hm12 = new HashMap<String, String>();
		hm12.put(SliderMin, SliderMinValue);
		hm12.put(SliderMax, SliderMaxValue);
		hm12.put(SliderInterval, SliderIntervalValue);
		pgfe.createField(SliderFieldType, SliderFieldName, hm12);

		HashMap<String, String> hm13 = new HashMap<String, String>();
		pgfe.createField(UserFieldType, UserFieldName, hm13);

		HashMap<String, String> hm14 = new HashMap<String, String>();
		hm14.put(DropdownListType, DropdownListName);
		pgfe.createField(ChecklistFieldType, ChecklistFieldName, hm14);

		HashMap<String, String> hm15 = new HashMap<String, String>();
		pgfe.createField(AttachmentFieldType, AttachmentFieldName, hm15);

		HashMap<String, String> hm16 = new HashMap<String, String>();
		pgfe.createField(SignFieldType, SignFieldName, hm16);

		HashMap<String, String> hm17 = new HashMap<String, String>();
		hm17.put(LookedupFieldParam, LookedupFieldSource);
		hm17.put(LookedupField, LookedupFieldValue);
		pgfe.createField(LookupFieldType, LookupFieldName, hm17);

		HashMap<String, String> hm18 = new HashMap<String, String>();
		hm18.put(RemoteLookedupFieldParam, RemoteLookedupFieldSource);
		hm18.put(RemoteLookedupField, RemoteLookedupFieldValue);
		pgfe.createField(RemoteLookupFieldType, RemoteLookupFieldName, hm18);
		
		pgfe.addTable();

		/*
		 * HashMap<String, String> hm19= new HashMap<String, String>();
		 * hm19.put("Aadharam", LookedupFieldSource); hm19.put("AggFieldSelect",
		 * "Initiated by"); hm19.put("AggTypeSelect", "Count");
		 * pgfe.createField("Aggregation", "ThisIsAggreg", hm19);
		 */

		ProcessWorkflowEditor pgwe = pge.navToWorkflowEditor();
		pgwe.createWorkflow(WorkflowName1, Approver1, Formula1);
		pgwe.createWorkflow(WorkflowName2, Approver2, Formula2);
		ProcessPermissionEditor pgpe = pge.navToPermissionEditor();
		pgpe.setPermission(TextFieldName, ReadonlyPermission);
		NewItemPage netItempg = pge.publish();
		pg.newItemClickOfProcess(FlowName);
		netItempg.enterValueToTetAreaField(TextAreaFieldValue, TextAreaFieldName);
		String title1 = pg.driver.getCurrentUrl();
		System.out.println("title1:" + title1);
		String[] processId1 = title1.split("/");
		processId = processId1[6];
		processInstId = processId1[8];
		System.out.println("Processid:" + processId);
		System.out.println("prcessInstId:" + processInstId);
		netItempg.enterValueToField(NumberFieldValue, NumberFieldName);
		netItempg.enterValueToField(CurrencyFieldValue, CurrencyFieldName);
		netItempg.enterValueToField(DateFieldValue, DateFieldName);
		netItempg.enterValueToDateTimeField(DateAndTimeFieldValue, DateAndTimeFieldName);
		netItempg.selectYesNoValueToField(YesNoFieldValue, YesNoFieldName);
		netItempg.uploadImage(ImageFieldValue, ImageFieldName);
		netItempg.enterValueToRatingField(RatingFieldValue, RatingFieldName);
		netItempg.enterValueToCheckboxAndDropdownField(DropdownFieldValue, DropdownFieldName);
		netItempg.enterValueToCheckboxAndDropdownField(CheckboxFieldValue, CheckboxFieldName);
		netItempg.setValueForSliderField(SliderFieldValue, SliderFieldName);
		netItempg.selectUserFromUserField(UserFieldValue, UserFieldName);
		netItempg.selectValueFromChecklistField(ChecklistFieldValue, ChecklistFieldName);
		netItempg.uploadAttachment(AttachmentFieldValue, AttachmentFieldName);
		netItempg.signToSignatureField(SignFieldName);
		netItempg.selectValueForLookUpField(LookedupFieldSource, LookupFieldName);
		netItempg.getdataForRemoteLookUpField(RemoteLookupFieldName);

		ProcessHomePage pg1 = netItempg.Submit();
		homePage.clickOnAdminLink1();
		adpg.timeZoneChange(timeZonePacific);
		adpg.saveLocaleAndTimezoneChange();
		homePage.getBacktoProcess(FlowName);
		pg1.openMyItemProcess(FlowName);
		newItemNewPg.checkCreatedFieldValue(TextFieldName, TextFieldDefaultParam);
		newItemNewPg.checkCreatedFieldValue(TextAreaFieldName, TextAreaFieldValue);
		newItemNewPg.checkCreatedFieldValue(NumberFieldName, NumberFieldValue);
		newItemNewPg.checkCreatedFieldContainsValue(CurrencyFieldName, ExpCurrencyFieldValue);
		// newItemNewPg.checkCreatedFieldValue(DateFieldName, ExpDateFieldValue);
		// newItemNewPg.overAndFindCurrentFieldValue(DateAndTimeFieldName,
		// ExpDateAndTimeFieldValue);
		newItemNewPg.checkCreatedFieldValue(YesNoFieldName, YesNoFieldValue);
		newItemNewPg.checkUploadedImage(ImageFieldName);
		newItemNewPg.checkCreatedFieldValue(DropdownFieldName, DropdownFieldValue);
		newItemNewPg.checkCreatedFieldValue(CheckboxFieldName, CheckboxFieldValue);
		newItemNewPg.overAndFindCurrentFieldValue(SliderFieldName, SliderFieldValue);
		newItemNewPg.checkCreatedFieldValue(UserFieldName, UserFieldValue);
		newItemNewPg.verifyApprovername(Approver1);
		newItemNewPg.closeNewItemActivityOfProcess(FlowName);
		pg.shareProcess();

	}

	//@Test(priority = 8)
	public void CreateNewProcessItem() {
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************Create New Process item************");
		Response response = REQUEST.header("X-Api-Key", apiKeyUser).log().all().contentType(ContentType.JSON).body("{}")
				.when().post("/process/1/AcJwbAAFemos/" + processId);
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().log().body().extract()
				.asString().contains("ModelId:" + processId);

		// response.then().assertThat()
		// .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("createNewProcessItem.json"));
		JsonPath jp = new JsonPath(response.asString());
		String respprcessId = jp.get("ModelId").toString();
		// String prcessInstId = jp.get("_id").toString();
		assertThat(respprcessId, equalTo(processId));

	}

	//@Test(priority = 2)
	public void getProcessItemList() {
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		System.out.println("************getProcessItemList()************");
		Response response = REQUEST.replaceHeader("X-Api-Key", apiKeyAdmin).log().all().when()
				.get("process/1/AcJwbAAFemos/admin/" + processId + "/item/p1/5");
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).log().body();
		// response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getItemList.json"));
		// ApplicationLog.info("Testing is complete");
		JsonPath jp = new JsonPath(response.asString());
		String prcessId = jp.get("_id").toString();
		// String prcessInstId = jp.getString("Data[0]._id").toString();
		System.out.println("Value:" + prcessId);
		// assertThat(prcessId, equalTo(processId));

	}

	 //@Test(priority = 3)
	public void getProcessItemDetails() {
		System.out.println("************getProcessItemDetails************");
		// Response response = REQUEST.when().get("process/1/AcJwbAAFemos/admin/" +
		// processId + "/" + processInstId);
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().when()
				.get("process/1/AcJwbAAFemos/admin/" + processId + "/" + processInstId);
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).log().body();
		// var resp=response.getBody().asString();
		// response.then().assertThat()
		// .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getProcessItemDetails.json"));
		// response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getItemList.json"));
		JsonPath jp = new JsonPath(response.asString());
		String prcessInstId = jp.get("_id").toString();
		String flowName = jp.getString("_flow_name").toString();
		System.out.println("Value:" + prcessInstId);
		assertThat(prcessInstId, equalTo(processInstId));
		assertThat(flowName, equalTo("SmokeProcess"));

		/*
		 * Assert.assertEquals(prcessInstId, prcessInstId1, "ProcessName from API
		 * response doesn't match Value from getMyItem"); Assert.assertEquals(flowName,
		 * "SmokeProcess", "ProcessName from API response doesn't match Value from
		 * getMyItem"); ApplicationLog.info("Testing is complete");
		 */

	}

	// @Test(priority = 9)
	public void getMyItemList() {
		System.out.println("************getMyItemList************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyUser).when().get("process/1/AcJwbAAFemos/" + processId + "/myitems/p5/10");
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		// .and().body("CardFields[0].Model",equalTo("SmokeProcess_127"));
		JsonPath jp = new JsonPath(response.asString());
		
	}

	 //@Test(priority = 10)
	public void getMyTaskList() {
		System.out.println("************getMyItemList************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyUser).when().get("process/1/AcJwbAAFemos/"+processId+"/pending?page_number=1&page_size=5");
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		// .and().body("CardFields[0].Model",equalTo("SmokeProcess_127"));
		JsonPath jp = new JsonPath(response.asString());
	 }

	// @Test(priority = 11)
	public void getProgressDetails() {
		System.out.println("************getMyItemList************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.when().get("process/1/AcJwbAAFemos/" + processId + "/myitems/p5/10");
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		// .and().body("CardFields[0].Model",equalTo("SmokeProcess_127"));
		JsonPath jp = new JsonPath(response.asString());
		String modelValue = jp.get("CardFields[0].Model").toString();
		System.out.println("Value:" + modelValue);
		assertThat(modelValue, equalTo(processId));
		// Assert.assertEquals(modelValue, prcessId1, "ProcessName from API response
		// doesn't match Value from getMyItem");
	}

	// @Test(priority = 12)
	public void getItemDetailsOfWFstep() {
		System.out.println("************getMyItemList************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.when().get("process/1/AcJwbAAFemos/" + processId + "/myitems/p5/10");
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		// .and().body("CardFields[0].Model",equalTo("SmokeProcess_127"));
		JsonPath jp = new JsonPath(response.asString());
		String modelValue = jp.get("CardFields[0].Model").toString();
		System.out.println("Value:" + modelValue);
		assertThat(modelValue, equalTo(processId));
		// Assert.assertEquals(modelValue, prcessId1, "ProcessName from API response
		// doesn't match Value from getMyItem");
	}

	// @Test(priority = 13)
	public void rejectItem() {
		System.out.println("************getMyItemList************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.when().get("process/1/AcJwbAAFemos/" + processId + "/myitems/p5/10");
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		// .and().body("CardFields[0].Model",equalTo("SmokeProcess_127"));
		JsonPath jp = new JsonPath(response.asString());
		String modelValue = jp.get("CardFields[0].Model").toString();
		System.out.println("Value:" + modelValue);
		assertThat(modelValue, equalTo(processId));
		// Assert.assertEquals(modelValue, prcessId1, "ProcessName from API response
		// doesn't match Value from getMyItem");
	}

	// @Test(priority = 14)
	public void submitItem() {
		System.out.println("************getMyItemList************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.when().get("process/1/AcJwbAAFemos/" + processId + "/myitems/p5/10");
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		// .and().body("CardFields[0].Model",equalTo("SmokeProcess_127"));
		JsonPath jp = new JsonPath(response.asString());
		String modelValue = jp.get("CardFields[0].Model").toString();
		System.out.println("Value:" + modelValue);
		assertThat(modelValue, equalTo(processId));
		// Assert.assertEquals(modelValue, prcessId1, "ProcessName from API response
		// doesn't match Value from getMyItem");
	}

	// @Test(priority = 15)
	public void addAttachmentasUserToFormField() {
		System.out.println("************getMyItemList************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.when().get("process/1/AcJwbAAFemos/" + processId + "/myitems/p5/10");
		
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		// .and().body("CardFields[0].Model",equalTo("SmokeProcess_127"));
		JsonPath jp = new JsonPath(response.asString());
		String modelValue = jp.get("CardFields[0].Model").toString();
		System.out.println("Value:" + modelValue);
		assertThat(modelValue, equalTo(processId));
		// Assert.assertEquals(modelValue, prcessId1, "ProcessName from API response
		// doesn't match Value from getMyItem");
	}

	// @Test(priority = 16)
	public void addImgasUserToFormField() {
		System.out.println("************getMyItemList************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.when().get("process/1/AcJwbAAFemos/" + processId + "/myitems/p5/10");
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		// .and().body("CardFields[0].Model",equalTo("SmokeProcess_127"));
		JsonPath jp = new JsonPath(response.asString());
		String modelValue = jp.get("CardFields[0].Model").toString();
		System.out.println("Value:" + modelValue);
		assertThat(modelValue, equalTo(processId));
		// Assert.assertEquals(modelValue, prcessId1, "ProcessName from API response
		// doesn't match Value from getMyItem");
	}

	 //@Test(priority = 7)
	public void deleteProcessItem() {
		System.out.println("************deleteProcessItem************");
		// Response response = REQUEST.when().delete("/process/1/AcJwbAAFemos/admin/" +
		// processId + "/" + processInstId);
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).when()
				.delete("/process/1/AcJwbAAFemos/admin/" + processId + "/" + processInstId);
		// response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("deleteProcessItem.json"));
		response.then().log().all().log().body().assertThat().statusCode(200).log().body().extract().asString()
				.equalsIgnoreCase("Success" + ":" + "Delete successfully");

	}

	//@Test(priority = 4)
	public void updateProcessItemDetails() {
		System.out.println("************updateProcessItemDetails************");
		jsonAsMap.put("Name", "SmokeProcess");
		jsonAsMap.put("Untitled_Field_1", "This field is updated via API");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).contentType(ContentType.JSON).body(jsonAsMap)
				.when().put("/process/1/AcJwbAAFemos/admin/" + processId + "/" + processInstId);
		response.then().log().body().assertThat().statusCode(200).and().log().body().extract().asString()
				.contains(processInstId);
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("updateProcessItem.json"));

	}

	 @Test(priority = 5)
	public void addAttachmenttoFormFieldAsAdmin() {
		System.out.println("************addAttachmenttoFormFieldAsAdmin************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).contentType("multipart/form-data")
				.multiPart("file", new File("src/test/resources/SBI.pages")).log().all().when()
				.post("/process/1/AcJwbAAFemos/admin/" + processId + "/" + processInstId
						+ "/ThisIsAttach/attachment");
		/*Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType("multipart/form-data")
				.multiPart("file", new File("src/test/resources/SBI.pages")).when()
				.post("/process/1/AcJwbAAFemos/admin/SmokeProcess_45/PkvN_uPm0B0m/Untitled_Field_13/attachment");*/
		response.then().log().body().assertThat().statusCode(200).and().log().body().extract().asString().contains("Attach");

	}

	 @Test(priority = 6)
	public void addImageToFormFieldAsAdmin() {
		System.out.println("************addImagetoFormFieldAsAdmin************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).contentType("multipart/form-data")
				.multiPart("file", new File("src/test/resources/sun.jpg")).accept("application/json").log().all().when()
				.post("/process/1/AcJwbAAFemos/admin/" + processId + "/" + processInstId + "/ThisIsImage/image");
		/*Response response = REQUEST.header("X-Api-Key", apiKeyAdmin).log().all().contentType("multipart/form-data")
				.multiPart("file", new File("src/test/resources/sun.jpg")).accept("application/json").when()
				.post("/process/1/AcJwbAAFemos/admin/SmokeProcess_45/PkvN_uPm0B0m/Untitled_Field_5/image");*/
		response.then().log().body().assertThat().statusCode(200).and().log().body().extract().asString()
				.contains("sun.jpg");

	}

	// @Test(priority = 4)
	public void getItemList() {
		System.out.println("************getMyItemList************");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.when().get("process/1/AcJwbAAFemos/" + processId + "/myitems/p5/10");
		
		System.out.println("responseHeaders:" + response.getHeaders());
		response.then().assertThat().statusCode(200).and().log().body();
		// .and().body("CardFields[0].Model",equalTo("SmokeProcess_127"));
		JsonPath jp = new JsonPath(response.asString());
		String modelValue = jp.get("CardFields[0].Model").toString();
		System.out.println("Value:" + modelValue);
		assertThat(modelValue, equalTo(processId));
		// Assert.assertEquals(modelValue, prcessId1, "ProcessName from API response
		// doesn't match Value from getMyItem");
	}

	//@Test(priority = 17)
	public void archiveProcess() {
		// Response response = REQUEST.header("X-Api-Key",
		// apiKey).when().put("flow/1/AcJwbAAFemos/process" + processId + "/archive");
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.replaceHeader("X-Api-Key", apiKeyAdmin).log().all().when()
				.put("flow/1/AcJwbAAFemos/process/" + processId + "/archive");
		response.then().and().log().body().assertThat().statusCode(200).and().log().body();
	}

	//@Test(priority = 18)
	public void deleteProcess() {
		// Response response = REQUEST.when().delete("flow/1/AcJwbAAFemos/process" +
		// processId);
		FilterableRequestSpecification REQUEST = (FilterableRequestSpecification) RestAssured.given();
		Response response = REQUEST.replaceHeader("X-Api-Key", apiKeyAdmin).log().all().when()
				.delete("flow/1/AcJwbAAFemos/process/" + processId);
		response.then().assertThat().statusCode(200).and().log().body();
	}

	@AfterTest
	public void tearDown() throws IOException, InterruptedException {
		driver.quit();
	}
}
