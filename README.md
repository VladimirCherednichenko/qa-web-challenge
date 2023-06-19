## UI Tests

### To run tests:
1. Install needed packages with next command (one time setup):
#### mvn install
2. Start tests execution with next command:
#### mvn test

### To generate reports:
1. Install allure 
#### brew install allure
for macos, for other platforms read https://docs.qameta.io/allure-report/
2. Generate reports with next command:
#### allure serve TestAutomation/allure-results/