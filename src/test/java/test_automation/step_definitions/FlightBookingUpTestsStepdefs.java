package test_automation.step_definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import test_automation.CookiesHelper;
import test_automation.pages.*;

import java.util.List;
import java.util.Map;

public class FlightBookingUpTestsStepdefs {
    MainPageObject mainPageObject;
    DiscoverNewDestinationsPageObject discoverNewDestinationsPageObject;
    FlightDetailsPageObject flightDetailsPageObject;
    LogInFormPageObject logInFormPageObject;
    CookiesHelper cookiesHelper;
    CheckoutPageObject checkoutPageObject;

    public FlightBookingUpTestsStepdefs(
            MainPageObject mainPageObject,
            DiscoverNewDestinationsPageObject discoverNewDestinationsPageObject,
            FlightDetailsPageObject flightDetailsPageObject,
            LogInFormPageObject logInFormPageObject,
            CookiesHelper cookiesHelper,
            CheckoutPageObject checkoutPageObject)
    {
        this.discoverNewDestinationsPageObject = discoverNewDestinationsPageObject;
        this.mainPageObject = mainPageObject;
        this.flightDetailsPageObject = flightDetailsPageObject;
        this.logInFormPageObject = logInFormPageObject;
        this.cookiesHelper = cookiesHelper;
        this.checkoutPageObject = checkoutPageObject;
    }

    @Given("Navigate to main page and select flight to anywhere in current month")
    public void navigateToMainPageAndSelectFlightToAnywhereInCurrentMonth() {
        mainPageObject.navigateToMainPage();
        mainPageObject.agreeToCookies();
        mainPageObject.selectOneWayTrip();
        mainPageObject.fillInDeparture("Vilnius");
        mainPageObject.selectAnyDestination();
        mainPageObject.selectNextMonthInFlexibleDates();
        mainPageObject.selectMondayInFlexibleDates();
        mainPageObject.clickApply();
        mainPageObject.clickDone();
        mainPageObject.agreeWithTermsOfUse();
        mainPageObject.pressSearchBtn();
    }

    @And("Open first possible destination country")
    public void openFirstPossibleDestinationCountry() {
        discoverNewDestinationsPageObject.expandFirstCountryInResults();
        discoverNewDestinationsPageObject.pressViewDestination();
        discoverNewDestinationsPageObject.closeFirstTab();
    }

    @And("Open first possible destination city")
    public void openFirstPossibleDestinationCity() {
        discoverNewDestinationsPageObject.pressFirstCityViewFlight();
        discoverNewDestinationsPageObject.closeFirstTab();
    }

    @And("Open first possible flight and proceed to checkout")
    public void openFirstPossibleFlightAndProceedToCheckout() {
        flightDetailsPageObject.chooseFirstFlightInTheList();
        flightDetailsPageObject.pressContinueForBasicPrice();
        flightDetailsPageObject.pressContinueWithValueFare();
        flightDetailsPageObject.pressLogInLater();
        flightDetailsPageObject.enterPassengerDetails("Volodymyr", "Cherednichenko", "Mr");
        flightDetailsPageObject.clickContiniueFlowButton();
        flightDetailsPageObject.selectSeatsLater();
        flightDetailsPageObject.clickContinueInBagsSection();
        flightDetailsPageObject.clickContinueInExtrasSectionAirportAndTrip();
        flightDetailsPageObject.clickContinueInExtrasSectionTransport();
        flightDetailsPageObject.clickGoToCheckout();
    }
    @Given("Navigate to main page")
    public void navigateToMainPage() {
        mainPageObject.navigateToMainPage();
        mainPageObject.agreeToCookies();
    }

    @And("Select flight to anywhere from Vilnius in current month")
    public void selectFlightToAnywhereFromVilniusInCurrentMonth() {
        //mainPageObject.agreeToCookies();
        mainPageObject.selectOneWayTrip();
        mainPageObject.fillInDeparture("Vilnius");
        mainPageObject.selectAnyDestination();
        mainPageObject.selectNextMonthInFlexibleDates();
        mainPageObject.selectMondayInFlexibleDates();
        mainPageObject.clickApply();
        mainPageObject.clickDone();
        mainPageObject.agreeWithTermsOfUse();
        mainPageObject.pressSearchBtn();
    }

    @Given("Set rid cookie")
    public void setLoginCookies() {
        // to avoid need to verify login each time by entering code from email we use cookie that will last year
        mainPageObject.navigateToMainPage();
        cookiesHelper.deleteRidCookie();
        cookiesHelper.addCookieWithRid();
    }

    @And("Fill out fields on checkout page and press pay")
    public void fillOutFieldsOnCheckoutPageAndPressPay(DataTable table) {
        checkoutPageObject.declineInsurance();
        List<Map<String, String>> tableList = table.asMaps(String.class, String.class);
        tableList.forEach((tableMap) ->
        {
            String phoneNumber = tableMap.get("phoneNumber");
            String cardNumber = tableMap.get("cardNumber");
            String expiryDate = tableMap.get("expiryDate");
            String securityCode = tableMap.get("securityCode");
            String cardholderName = tableMap.get("cardholderName");
            String firstAddress = tableMap.get("firstAddress");
            String secondAddress = tableMap.get("secondAddress");
            String city = tableMap.get("city");
            String country = tableMap.get("country");
            String postcode = tableMap.get("postcode");
            checkoutPageObject.enterPhoneNumber(phoneNumber);
            checkoutPageObject.enterCardDetails(
                    cardNumber, expiryDate,
                    securityCode, cardholderName,
                    firstAddress, secondAddress,
                    city, country, postcode);
        });
        //checkoutPageObject.enterCardDetails();
        checkoutPageObject.chooseCurrencyEuro();
        checkoutPageObject.acceptTermsOfUse();
        checkoutPageObject.pressPay();
    }

    @Then("Payment error is displayed")
    public void paymentErrorIsDisplayed() {
        checkoutPageObject.validatePaymentErrorMessageExistence();
    }

    @And("Payment error msg contains {string}")
    public void paymentErrorMsgContains(String msg) {
        checkoutPageObject.validatePaymentErrorMsg(msg);
    }

    @And("Fill out login with userName {string} and password {string} and press login")
    public void fillOutLoginWithUserNameAndPasswordAndPressLogin(String userName, String password) {
        logInFormPageObject.loginWithCredentials(userName,password);
    }

    @And("Select flight to anywhere in month and day of week")
    public void selectFlightToAnywhereInMonthAndDayOfWeek(DataTable table) {
        mainPageObject.selectOneWayTrip();

        List<Map<String, String>> tableList = table.asMaps(String.class, String.class);
        tableList.forEach((tableMap) ->
        {
            String month = tableMap.get("month");
            String dayOfWeek = tableMap.get("dayOfWeek");
            String departureCity = tableMap.get("departureCity");

            mainPageObject.fillInDeparture(departureCity);
            mainPageObject.selectAnyDestination();
            mainPageObject.selectMonthAndDayOfWeekInFlexibleDates(month, dayOfWeek);
        });

        mainPageObject.clickApply();
        mainPageObject.clickDone();
        mainPageObject.agreeWithTermsOfUse();
        mainPageObject.pressSearchBtn();
    }

    @And("Open first possible flight and proceed to checkout with passenger info")
    public void openFirstPossibleFlightAndProceedToCheckoutWithPassengerInfo(DataTable table) {
        flightDetailsPageObject.chooseFirstFlightInTheList();
        flightDetailsPageObject.pressContinueForBasicPrice();
        flightDetailsPageObject.pressContinueWithValueFare();
        flightDetailsPageObject.pressLogInLater();
        List<Map<String, String>> tableList = table.asMaps(String.class, String.class);
        tableList.forEach((tableMap) ->
        {
            String firstName = tableMap.get("firstName");
            String lastName = tableMap.get("lastName");
            String title = tableMap.get("title");

            flightDetailsPageObject.enterPassengerDetails(firstName, lastName, title);
        });

        flightDetailsPageObject.clickContiniueFlowButton();
        flightDetailsPageObject.selectSeatsLater();
        flightDetailsPageObject.clickContinueInBagsSection();
        flightDetailsPageObject.clickContinueInExtrasSectionAirportAndTrip();
        flightDetailsPageObject.clickContinueInExtrasSectionTransport();
        flightDetailsPageObject.clickGoToCheckout();
    }
}
