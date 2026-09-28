# Java Selenium E-commerce Automation Framework

A maintainable UI test automation framework built with **Java, Selenium WebDriver, TestNG, and Maven**, designed to automate the complete e-commerce workflow of the [SauceDemo](https://www.saucedemo.com/) application.

The framework follows the **Page Object Model (POM)** design pattern and includes reusable components for browser management, configuration, test data management, reporting, screenshots, cross-browser execution, and Continuous Integration.

---

## 📌 Project Overview

This project demonstrates how I designed and implemented an end-to-end Selenium automation framework for an e-commerce application.

The framework automates the following user journey:

**Login → Products → Cart → Checkout → Checkout Overview → Order Confirmation**

The objective was not only to automate individual test cases, but to build a structured, reusable, and maintainable automation framework using industry-style QA automation practices.

### Key Highlights

* 40 automated test cases
* Java + Selenium WebDriver
* TestNG test framework
* Maven build and dependency management
* Page Object Model (POM)
* Reusable `BasePage` and `BaseTest`
* `DriverFactory` for browser management
* Individual JSON test-data files
* External configuration using `config.properties`
* Jackson for JSON data processing
* ExtentReports integration
* Screenshot capture for failed tests
* Chrome, Edge, and Firefox support
* Git and GitHub version control
* GitHub Actions CI pipeline
* 40/40 tests passing in CI

---

## 🛠️ Tech Stack

| Technology              | Purpose                         |
| ----------------------- | ------------------------------- |
| Java                    | Programming language            |
| Selenium WebDriver      | Web UI automation               |
| TestNG                  | Test execution and assertions   |
| Maven                   | Dependency and build management |
| Page Object Model       | Maintainable framework design   |
| Jackson                 | Reading JSON test data          |
| ExtentReports           | Test execution reporting        |
| Git & GitHub            | Version control                 |
| GitHub Actions          | Continuous Integration          |
| Chrome / Edge / Firefox | Cross-browser testing           |

---

## 🧪 Application Under Test

**SauceDemo** is a sample e-commerce web application used for practicing and demonstrating web automation.

The framework covers the main shopping workflow from authentication through order completion.

---

# 📋 Test Coverage

The framework currently contains **40 automated test cases**.

### 🔐 Login — 8 Tests

Covers:

* Valid login
* Invalid username
* Invalid password
* Invalid username and password
* Locked-out user
* Empty username validation
* Empty password validation
* Login error messages and navigation

### 🛍️ Products — 11 Tests

Covers:

* Product listing
* Product details
* Product selection
* Product sorting
* Name sorting
* Price sorting
* Add product to cart
* Cart badge validation
* Product-related UI validations

### 🛒 Cart — 7 Tests

Covers:

* Cart navigation
* Added product validation
* Product price validation
* Product description validation
* Multiple products in cart
* Removing products
* Continue shopping functionality

### 💳 Checkout — 5 Tests

Covers:

* Checkout page navigation
* Valid customer information
* First name validation
* Last name validation
* Postal code validation

### 📦 Checkout Overview — 6 Tests

Covers:

* Checkout overview page
* Selected product validation
* Product description
* Product price
* Payment information
* Shipping information
* Order total calculation
* Order completion
* Cancel navigation

### ✅ Order Confirmation — 3 Tests

Covers:

* Order confirmation page
* Order confirmation message
* Back Home navigation
* Generate PDF order functionality

---

# 🏗️ Framework Architecture

The framework follows the **Page Object Model (POM)** design pattern.

All framework and test classes are organised under `src/test/java`, keeping the automation implementation together with the test suite.

```text
SeleniumEcommerceAutomation
│
├── .github
│   └── workflows
│       └── maven-tests.yml
│
├── src
│   └── test
│       │
│       ├── java
│       │   │
│       │   ├── base
│       │   │   ├── BasePage.java
│       │   │   └── BaseTest.java
│       │   │
│       │   ├── pages
│       │   │   ├── LoginPage.java
│       │   │   ├── ProductPage.java
│       │   │   ├── CartPage.java
│       │   │   ├── CheckoutPage.java
│       │   │   ├── CheckoutOverviewPage.java
│       │   │   └── OrderConfirmationPage.java
│       │   │
│       │   ├── tests
│       │   │   ├── LoginTest.java
│       │   │   ├── ProductPageTest.java
│       │   │   ├── CartPageTest.java
│       │   │   ├── CheckoutPageTest.java
│       │   │   ├── CheckoutOverviewPageTest.java
│       │   │   └── OrderConfirmationPageTest.java
│       │   │
│       │   └── utils
│       │       ├── ConfigReader.java
│       │       ├── DriverFactory.java
│       │       ├── JsonDataReader.java
│       │       ├── ScreenshotUtil.java
│       │       ├── ExtentReportManager.java
│       │       └── ExtentTestListener.java
│       │
│       └── resources
│           ├── testdata
│           │   ├── validLoginTest.json
│           │   ├── invalidUserNameAndValidPassword.json
│           │   ├── selectBikeLightTest.json
│           │   ├── cartBadgeUpdatesAfterAddingProduct.json
│           │   ├── verifyProductIsDisplayedInCart.json
│           │   └── ...
│           │
│           └── config
│               └── config.properties
│
└── pom.xml
```

---

# 🧩 Framework Components

## BasePage

`BasePage` contains reusable Selenium operations shared across page classes.

Examples include:

* Clicking elements
* Entering text
* Retrieving text
* Retrieving multiple element values
* Checking element visibility
* Explicit wait handling

This reduces duplicate Selenium code and keeps page classes focused on application-specific behaviour.

---

## Page Objects

Each major application page has its own Page Object.

```text
LoginPage
    ↓
ProductPage
    ↓
CartPage
    ↓
CheckoutPage
    ↓
CheckoutOverviewPage
    ↓
OrderConfirmationPage
```

Page classes encapsulate:

* Locators
* Page-specific actions
* Navigation
* Reusable page-level methods

This keeps the test classes readable and separates test intent from UI implementation details.

---

## BaseTest

`BaseTest` provides common test lifecycle functionality.

It is responsible for:

* Creating the WebDriver through `DriverFactory`
* Opening the application
* Maximising the browser
* Providing reusable valid-login functionality
* Closing the browser after each test
* Initialising ExtentReports
* Flushing the test report after execution

Each test receives a **new WebDriver session**, providing test isolation and ensuring that browser/session state does not carry over between tests.

---

## DriverFactory

`DriverFactory` centralises WebDriver creation and browser configuration.

The framework supports:

* Chrome
* Edge
* Firefox

The selected browser is controlled through the external configuration file.

For GitHub Actions, Chrome runs in **headless mode** with CI-specific browser options so the tests can execute reliably in the Linux environment.

---

# 📂 Test Data Management

Test data is maintained separately from the test implementation using **individual JSON files**.

Instead of maintaining one large shared test-data file, each test has its own JSON file where test-specific data is required.

Example:

```text
src/test/resources/testdata/
│
├── validLoginTest.json
├── invalidUserNameAndValidPassword.json
├── invalidUserNameAndInvalidPassword.json
├── selectBikeLightTest.json
├── cartBadgeUpdatesAfterAddingProduct.json
├── verifyProductIsDisplayedInCart.json
├── verifyValidCheckoutInformationCanBeEntered.json
└── ...
```

Example JSON:

```json
{
  "bikeLight": "Sauce Labs Bike Light",
  "firstName": "Alex",
  "lastName": "Test",
  "postalCode": "0611"
}
```

This approach provides **test-level data isolation**, meaning changes to one test's data file do not unintentionally affect unrelated tests.

Jackson is used to read and process the JSON files.

---

# ⚙️ Configuration Management

Environment-related configuration is maintained separately in:

```text
src/test/resources/config/config.properties
```

Example:

```properties
baseUrl=https://www.saucedemo.com/
browser=chrome
```

This keeps configurable values such as the application URL and browser selection outside the test classes.

---

# 📊 Reporting

The framework integrates **ExtentReports** to provide test execution reporting.

The reporting implementation includes:

* Test execution status
* Passed and failed test information
* Test lifecycle integration
* Failure details
* Screenshot integration

A TestNG listener is used to integrate test execution with the reporting framework.

---

# 📸 Screenshot Handling

A reusable screenshot utility is included in the framework.

Screenshots can be captured when tests fail, providing useful evidence for:

* Failure investigation
* Debugging
* Test evidence
* Root-cause analysis

---

# 🌐 Cross-Browser Support

The framework supports multiple browsers through `DriverFactory`.

Supported browsers:

* Chrome
* Edge
* Firefox

The framework has been validated using these supported browsers.

---

# ⚙️ Continuous Integration

The project uses **GitHub Actions** to automatically execute the Maven test suite.

### CI Workflow

```text
Code Push / Pull Request
          ↓
    GitHub Actions
          ↓
         Maven
          ↓
   Selenium Tests
          ↓
        TestNG
          ↓
     Test Results
```

The CI workflow executes:

```bash
mvn clean test
```

Chrome runs in headless mode on the GitHub Actions Linux environment.

### CI Result

```text
Tests run: 40
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

---

# ▶️ How to Run the Tests

## Prerequisites

Make sure the following are installed:

* Java JDK
* Maven
* Git
* A supported browser

## Clone the Repository

```bash
git clone https://github.com/testwithAagy/Java-Selenium-Ecommerce-Automation.git
```

Navigate to the project:

```bash
cd Java-Selenium-Ecommerce-Automation
```

## Configure the Browser

Update:

```text
src/test/resources/config/config.properties
```

For example:

```properties
browser=chrome
```

## Run the Test Suite

Run all tests using Maven:

```bash
mvn clean test
```

---

# 📈 Test Execution Summary

| Module             |  Tests |
| ------------------ | -----: |
| Login              |      8 |
| Products           |     11 |
| Cart               |      7 |
| Checkout           |      5 |
| Checkout Overview  |      6 |
| Order Confirmation |      3 |
| **Total**          | **40** |

**Current CI result: 40/40 tests passing**

---

# 🎯 What This Project Demonstrates

This project demonstrates practical experience in:

* Web UI automation
* Selenium WebDriver
* Java test automation
* TestNG
* Maven
* Page Object Model
* Test data management
* Test-level JSON data isolation
* Configuration management
* Reusable framework components
* Explicit wait strategies
* Cross-browser testing
* Test reporting
* Screenshot capture
* Continuous Integration
* Git and GitHub
* GitHub Actions
* Debugging CI-specific browser issues

The framework was designed with a focus on **readability, reusability, maintainability, test isolation, and realistic QA automation practices** rather than simply creating individual Selenium scripts.

---

# 🚀 Future Improvements

Potential future enhancements include:

* Parallel test execution
* Browser selection through Maven parameters
* Data-driven testing using TestNG
* Additional API automation
* Enhanced reporting and test evidence
* Docker-based test execution
* CI browser matrix testing
* Integration with a test management system

---

# 👩‍💻 Author

**Aagy Paulose**

QA Engineer | Manual & Automation Testing | Selenium | Java | API Testing | BDD

GitHub: [@testwithAagy](https://github.com/testwithAagy)
