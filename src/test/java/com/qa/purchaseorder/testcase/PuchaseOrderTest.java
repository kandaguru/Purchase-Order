package com.qa.purchaseorder.testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.purchaseorder.base.POTestBase;
import com.qa.purchaseorder.page.HomePage;
import com.qa.purchaseorder.page.LoginPage;
import com.qa.purchaseorder.util.TestUtil;

public class PuchaseOrderTest extends POTestBase {

	LoginPage loginPage;
	HomePage homePage;

	public PuchaseOrderTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException {

		intialize();
		loginPage = new LoginPage();
		loginPage.login();
		homePage = loginPage.enter2FA();

		while (loginPage.returnErrorMsg()) {

			homePage = loginPage.enter2FA();

		}

	}

	@Test(dataProvider = "data")
	public void fill_Form_Test(Map<Object, Object> map) throws InterruptedException, IOException {

		/*******************************************************
		 * Value for each field is fetched from test-data excel * to the data-provider
		 * from where the test-data is fed * to the test cases. *
		 ********************************************************/

		Map<Object, Object> map1 = map;

		String onBehalfRdBtnValue = (String) map
				.get("Are you making this request on behalf of someone else?(onBehalf)");
		String supervisorUpiValue = (String) map.get("Please provide your supervisor's UPI/Username(supervisorUPI)");
		String requestorRdBtnValue = (String) map.get("The requestor is(isStudent)");

		String facultyServiceValue = (String) map
				.get("Select the department, faculty or service division this request is for:(departmentFaculty)");
		String BocRdBtnValue = (String) map.get("Is this request for BOC(typeOfRequest)");
		String bocAccNumberValue = (String) map.get("Enter BOC customer account number(bocCustomerAccount)");
		String supplierNameValue = (String) map.get("Supplier name(supplierName)");
		String supplierNumberValue = (String) map.get("Supplier number(supplierNumber)");
		String currencyValue = (String) map.get("Request currency(requestCurrency)");
		String requestToBeChargedValue = (String) map.get("Is the request to be charged to a(requestToBeChargedTo)");
		String splitCCValue = (String) map.get(
				"Do you want to split this purchase order across cost centres, account codes or projects?(costCentreSplit)");

		String supressPOValue = (String) map.get("Suppress PO price from supplier?(suppressPoAmount)");
		String ccCodeValue = (String) map.get("Cost centre code(costCentre)");

		String phdProjectCodeValue = (String) map.get("Project code(phdProjCode)");
		String deptProjectCodeValue = (String) map.get("Project code(deptProjCode)");
		String researchProjectcodeValue = (String) map.get("Project code(researchProjCode)");

		String phdaccCodeValue = (String) map.get("Account code(accountCodePress)");
		String accCodeValue = (String) map.get("Account code(accountCode)");

		String internalUoaProductCodeValue = (String) map.get("Internal UOA Product Code(internalProductCode)");

		String date = (String) map.get("Date goods/services are needed(dateNeeded)");
		String datearr[] = date.split("/");

		String dd = datearr[0];
		String mm = datearr[1];
		String yyyy = datearr[2];

		/*****************************************************************
		 * converting number of items from string(example : 2.0) to int * and again back
		 * to String value (example : 2 ) *
		 *****************************************************************/
		int numberOfItemsInt = 0;
		String numberOfItemsStringValue = null;
		String numberOfItemsString = (String) map.get("How many line items are you requesting?(numberOfLineItems)");
		String buildingNumberValue = (String) map.get("Delivery building number(deliveryBuildingNumber)");
		String roomNumberValue = (String) map.get("Delivery room number(deliveryRoomNumber)");
		String deliveryAddress1Value = (String) map.get("Delivery address line 1(deliveryAddress1)");
		String deliveryAddress2Value = (String) map.get("Delivery address line 2(deliveryAddress2)");
		String deliveryCityValue = (String) map.get("Delivery city(deliveryCity)");
		String deliveryPostCodeValue = (String) map.get("Delivery post code(deliveryPostCode)");

		String supplierAdditionalDocLocation = (String) map
				.get("Upload additional documents for the supplier(Upload1)");
		String additionalDocUploadLocation = (String) map.get("Upload additional documents(upload2)");
		String supplierNotesValue = (String) map.get("Supplier notes(supplierNotes)");

		String moreThanSixDocLocValue = (String) map
				.get("Please attach a supporting document if there are more than 6 items(moreThanSixFile)");

		String splitCommentsValue = (String) map
				.get("Enter any additional notes about the requested split(splitComments)");

		String splitDetailFileLocValue = (String) map
				.get("Upload details of the split you require(costCentreSPlitUpload)");

		String supervisorAprovalDocValue = (String) map.get("Upload supervisors approval(supvApproval)");

		/**********************************************
		 * Logic to handle more than 6 items scenario*
		 **********************************************/

		if (!(numberOfItemsString
				.equalsIgnoreCase("Please attach a supporting document if there are more than 6 items"))) {

			numberOfItemsInt = Integer.parseInt(numberOfItemsString);

		}

		else {
			numberOfItemsStringValue = numberOfItemsString;
			numberOfItemsInt = 7; // set a random number more than six
		}

		/***************************************************************
		 * Enter the Requester Details * And branch based on the value *
		 ***************************************************************/

		homePage.RadioBtn_click(onBehalfRdBtnValue);

		if (onBehalfRdBtnValue.equalsIgnoreCase("yes")) {

			homePage.enterOnBehalfDetails(); // enter the on-behalf details upon selecting yes in the previous step

		}

		else {

			log.info("*************On behalf module had to be included**************");
		}

		/*************************************
		 * select the requester radio button *
		 *************************************/

		homePage.RadioBtn_click(requestorRdBtnValue);

		if (requestorRdBtnValue.equalsIgnoreCase("student")) {

			homePage.enterSupervisorUpi(supervisorUpiValue);

		}

		/*******************************
		 * Select the faculty services *
		 *******************************/

		homePage.selectDropDown("faculty service", facultyServiceValue);

		/*******************************************
		 * BOC yes or NO Radio Button value And Branch based on the Value selected *
		 *******************************************/

		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

			homePage.RadioBtn_click("Boc" + BocRdBtnValue);

			if (BocRdBtnValue.equalsIgnoreCase("yes")) {

				homePage.enterBocAccNumber(bocAccNumberValue);

			}

			else {

				homePage.enterSupplierDetails(supplierNameValue, supplierNumberValue);

			}
		} catch (Exception e) {

			homePage.enterSupplierDetails(supplierNameValue, supplierNumberValue);

		} finally {

			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
		}

		/******************************************
		 * Enter Currency Value from the drop down*
		 ******************************************/

		homePage.selectDropDown("currency", currencyValue);

		/*******************************
		 * Enter Purchase Order Details*
		 *******************************/

		/**************************************
		 * Enter the Request to be Charged to ko* Department/Service Division * Research
		 * Project * PhD Press Account *
		 **************************************/

		homePage.RadioBtn_click(requestToBeChargedValue);

		/********************
		 * Suppress PO value*
		 ********************/

		if (supressPOValue.equalsIgnoreCase("yes")) {

			homePage.selectCheckBox();
		}
		homePage.RadioBtn_click("splitCC" + splitCCValue);

		/*************************************************
		 * split this purchase order across cost centres, account codes or projects? *
		 * And branch based on the condition *
		 *************************************************/

		if (splitCCValue.equalsIgnoreCase("yes")) {

			homePage.enterSplitDetails(splitCommentsValue, splitDetailFileLocValue);

		}

		else {

			/********************************************************
			 * Number of Items and Adding the details of each item: Supplier product code *
			 * Description * Quantity * Unit price (excl. GST) *
			 ********************************************************/

			homePage.selectDropDown("item details", numberOfItemsString);

			homePage.itemDetails(numberOfItemsInt, map1, numberOfItemsStringValue, moreThanSixDocLocValue);

			switch (requestToBeChargedValue) {

			case "Research Project":
				homePage.enterCostCentreDetails(requestToBeChargedValue, ccCodeValue, researchProjectcodeValue,
						accCodeValue, internalUoaProductCodeValue);
				break;

			case "Department/Service Division":
				homePage.enterCostCentreDetails(requestToBeChargedValue, ccCodeValue, deptProjectCodeValue,
						accCodeValue, internalUoaProductCodeValue);
				break;

			case "PhD PReSS Account":

				homePage.enterCostCentreDetails(requestToBeChargedValue, ccCodeValue, phdProjectCodeValue,
						phdaccCodeValue + " ", internalUoaProductCodeValue);
				break;

			}

		}

		/*************************
		 * Enter Date of Delivery*
		 *************************/

		homePage.enterdate(dd, mm, yyyy);

		/*************************
		 * Enter Delivery address*
		 *************************/

		homePage.enterDeliveryAddress(buildingNumberValue, roomNumberValue, deliveryAddress1Value,
				deliveryAddress2Value, deliveryCityValue, deliveryPostCodeValue);

		/****************************************************************************
		 * check what file to upload if count greater than 6 in the test* data excel *
		 *****************************************************************************/

		/******************************
		 * Additional Document Uploads*
		 ******************************/

		if (!(supplierAdditionalDocLocation.trim().equals(""))) {

			homePage.additionalSupplierDocUpload(supplierAdditionalDocLocation);

		}
		if (!(additionalDocUploadLocation.trim().equals(""))) {

			homePage.addtionalDocUpload(additionalDocUploadLocation);
		}

		if (!(supplierNotesValue.trim().equals(""))) {

			homePage.enterSupplierNotes(supplierNotesValue);
		}

		if (!(supervisorAprovalDocValue.trim().equals(""))) {

			homePage.supervisorDocUpload(supervisorAprovalDocValue);
		}

		homePage.acknowledgeAndSubmit();

	}

	@DataProvider
	public Object[][] data() throws IOException {
		
		String path=System.getProperty("user.dir");

		File file = new File(path+"\\src\\main\\java\\com\\qa\\purchaseorder\\testdata\\ExcelData.xlsx");
		FileInputStream fis = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(prop.getProperty("sheetname"));
		wb.close();

		int lastRowNum = sheet.getLastRowNum();
		log.debug("Total number of Rows in TestData File=====>" + lastRowNum);

		int lastCellNum = sheet.getRow(0).getLastCellNum();

		Object[][] obj = new Object[lastRowNum][1];

		for (int i = 0; i < lastRowNum; i++) {
			Map<Object, Object> datamap = new HashMap<Object, Object>();
			for (int j = 0; j < lastCellNum; j++) {

				datamap.put(sheet.getRow(0).getCell(j) + "".toString(), sheet.getRow(i + 1).getCell(j) + "".toString());
			}
			obj[i][0] = datamap;

		}
		return obj;
	}

	//@AfterMethod
	public void tearDown() {

		driver.quit();
		driver = null;
	}
}
