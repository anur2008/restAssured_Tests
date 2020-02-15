package org.kissflow.qa.process;

import java.util.HashMap;

import org.kissflow.qa.form.TextFieldDefn;
import org.kissflow.qa.utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class ProcessFormEditor extends org.kissflow.qa.utils.KissflowPage {

	@FindBy(xpath = "(//label[text()='Text'])[last()]")
	WebElement TextFieldButton;

	@FindBy(xpath = "//p[text()='Text']")
	WebElement FieldTypeText;

	@FindBy(xpath = "(//input[@name = 'Name'])[last()]")
	WebElement TextFieldName;

	@FindBy(xpath = "//input[@placeholder='Field Id']")
	WebElement FieldId;

	@FindBy(xpath = "(//button[text()='Done'])[last()]")
	WebElement DoneButton;

	@FindBy(xpath = "//label[text()='Untitled field']")
	WebElement UntitledField;

	@FindBy(xpath = "(//button[text()='Delete'])[last()]")
	WebElement DeleteButton;

	@FindBy(xpath = "//button[text()='Cancel']")
	WebElement CancelButton;

	@FindBy(xpath = "//button[contains(text(),'Add table')]")
	WebElement AddTable;

	public ProcessFormEditor() {
		PageFactory.initElements(driver, this);
	}

	public TextFieldDefn createCommonField(String FiledType, String FieldName) throws InterruptedException {
		TextFieldDefn tfd = new TextFieldDefn();
		Utils.isElementLoaded(driver, TextFieldButton);

		TextFieldButton.click();
		Utils.isElementLoaded(driver, FieldTypeText);
		FieldTypeText.click();
		String firstpart = "//label[@class ='punnai-label' and text()='";
		String lastpart = "']";
		String finalpart = firstpart + FiledType + lastpart;
		System.out.println(finalpart);
		Utils.isElementLoaded(driver, driver.findElement(By.xpath(finalpart)));
		driver.findElement(By.xpath(finalpart)).click();
		Utils.isElementLoaded(driver, TextFieldName);
		TextFieldName.click();

		System.out.println("FieldId:" + FieldId.getAttribute("value"));
		System.out.println("FiedName:" + TextFieldName.getAttribute("value"));

		Actions actions = new Actions(driver);
		actions.doubleClick(TextFieldName).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		actions.doubleClick(TextFieldName).perform();
		actions.sendKeys(Keys.BACK_SPACE);
	    actions.doubleClick(FieldId).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		actions.doubleClick(FieldId).perform();
		actions.sendKeys(Keys.BACK_SPACE);
		TextFieldName.sendKeys(FieldName);
		FieldId.sendKeys(FieldName);
		System.out.println("FieldIdBeforesendingkeys:" + FieldId.getAttribute("value"));
		System.out.println("FieldId1:" + FieldId.getAttribute("value"));

		System.out.println("FiedName1:" + TextFieldName.getAttribute("value"));
		return tfd;
	}

	@Step("Create field")
	public void createField(String FiledType, String FieldName, HashMap<String, String> map) throws Exception {
		switch (FiledType) {
		case "Text":
			TextFieldDefn field1 = this.createCommonField(FiledType, FieldName);
			field1.setExtraParams(map);
			break;
		case "Text Area":
			TextFieldDefn textAreafield1 = this.createCommonField(FiledType, FieldName);
			textAreafield1.setExtraParams(map);
			break;
		case "Number":
			TextFieldDefn field2 = this.createCommonField(FiledType, FieldName);
			field2.setExtraParams(map);
			break;
		case "Currency":
			TextFieldDefn currencyfield2 = this.createCommonField(FiledType, FieldName);
			currencyfield2.setExtraParams(map);
			break;
		case "Date":
			TextFieldDefn field3 = this.createCommonField(FiledType, FieldName);
			field3.setExtraParams(map);
			break;
		case "Date & Time":
			TextFieldDefn fieldDateTime = this.createCommonField(FiledType, FieldName);
			fieldDateTime.setExtraParams(map);
			break;

		case "Yes/No":
			TextFieldDefn fieldYesNo = this.createCommonField(FiledType, FieldName);
			fieldYesNo.setExtraParams(map);
			break;

		case "Image":
			TextFieldDefn fieldImage = this.createCommonField(FiledType, FieldName);
			fieldImage.setExtraParams(map);
			break;

		case "Rating":
			TextFieldDefn fieldRating = this.createCommonField(FiledType, FieldName);
			fieldRating.setExtraParams(map);
			break;

		case "Dropdown":
			TextFieldDefn fieldDropdown = this.createCommonField(FiledType, FieldName);
			fieldDropdown.setExtraParams(map);
			break;

		case "Checkbox / Multi-select":
			TextFieldDefn fieldcheckbox = this.createCommonField(FiledType, FieldName);
			fieldcheckbox.setExtraParams(map);
			break;

		case "Slider":
			TextFieldDefn fieldSlider = this.createCommonField(FiledType, FieldName);
			fieldSlider.setExtraParams(map);
			break;

		case "User":
			TextFieldDefn fieldUser = this.createCommonField(FiledType, FieldName);
			fieldUser.setExtraParams(map);
			break;

		case "Checklist":
			TextFieldDefn fieldChecklist = this.createCommonField(FiledType, FieldName);
			fieldChecklist.setExtraParams(map);
			break;

		case "Attachment":
			TextFieldDefn fieldAttachment = this.createCommonField(FiledType, FieldName);
			fieldAttachment.setExtraParams(map);
			break;

		case "Signature":
			TextFieldDefn fieldSignature = this.createCommonField(FiledType, FieldName);
			fieldSignature.setExtraParams(map);
			break;

		case "Lookup":
			TextFieldDefn fieldLookup = this.createCommonField(FiledType, FieldName);
			fieldLookup.setExtraParams(map);
			break;

		case "Remote Lookup":
			TextFieldDefn fieldRemoteLookup = this.createCommonField(FiledType, FieldName);
			fieldRemoteLookup.setExtraParams(map);
			break;

		case "Aggregation":
			TextFieldDefn fieldAgg = this.createCommonField(FiledType, FieldName);
			fieldAgg.setExtraParams(map);
			break;
		default:
			break;
		}
		takeScreenshot();

		String attValue = FieldId.getAttribute("value");

		System.out.println("id:" + FieldId.getAttribute("value"));
		/*
		 * if(FieldId.getAttribute("value").contains("Untitled_Field")) {
		 * FieldId.click(); System.out.println("***Entering if loop*****");
		 * waitForElementTextEqualsString(FieldId,FieldName,driver);
		 * 
		 * if(attValue==FieldName){ }*/
		
		
		

		if (!attValue.contains("Untitled_Field"))
			Utils.isElementLoaded(driver, DoneButton);
		DoneButton.click();

	}

	public void waitForElementTextEqualsString(WebElement element, String expectedString, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		ExpectedCondition<Boolean> elementTextEqualsString = arg0 -> element.getAttribute("value")
				.equals(expectedString);
		wait.until(elementTextEqualsString);
	}

	public void clearUntitledField() {
		try {
			if (UntitledField.isDisplayed()) {
				UntitledField.click();
				DeleteButton.click();
				Utils.isElementLoadedSec(driver, CancelButton);
				DeleteButton.click();
			}

			else {
				System.out.println("");
			}
		}

		catch (Exception e) {
			System.out.println("");
		}
	}

	public void addTable() {
		Utils.isElementLoaded(driver, AddTable);
		System.out.println("***adding table*****");
		AddTable.click();

	}
}
