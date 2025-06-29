# OrangeHRM Automated Testing Framework

Automated Testing Framework for OrangeHRM using Java, Cucumber, TestNG, Selenium WebDriver, and Maven.

## Overview

This project is a Java-based automated testing framework using Cucumber, TestNG, Selenium WebDriver, and Maven. It supports BDD-style test development, generates rich test reports, and integrates with GitHub Actions for CI/CD.

---

## Project Structure

```
src/
 └── test/
     ├── java/
     │    ├── features/                # Cucumber feature files
     │    ├── hooks/                   # Cucumber hooks (setup/teardown)
     │    ├── runner/                  # Test runner classes (e.g., testRunner.java, ciTestRunner.java)
     │    ├── stepDefinitions/         # Step definition classes
     │    └── utils/                   # Utility classes (DriverManager, ConfigReader, ScreenshotUtil, etc.)
     └── resources/
          ├── Config/                  # Configuration files (config.properties)
          └── testingData/             # Test data files (e.g., EmployeeDetails.json)
.github/
 └── workflows/
     └── ci.yml                        # GitHub Actions workflow for CI
test-output/
 └── SparkReport/                      # Generated test reports and screenshots
pom.xml                                # Maven project file
README.md                              # Project documentation
```

---

## Technologies & Tools

- **Java**: Main programming language
- **Maven**: Build and dependency management
- **Selenium WebDriver**: Browser automation
- **Cucumber**: BDD framework for writing feature files
- **TestNG**: Test execution and reporting
- **ExtentReports**: Rich HTML test reporting
- **GitHub Actions**: CI/CD pipeline for automated test execution
- **JSON**: Test data management

---

## How to Run

**Locally:**
```sh
mvn clean test
```
or to run a specific runner:
```sh
mvn clean test -Dtest=runner.ciTestRunner
```

**On GitHub Actions:**
- Tests are triggered automatically via `.github/workflows/ci.yml`.

---

## Key Features

- BDD-style test development with Cucumber
- Parallel or sequential test execution
- Automatic screenshot capture on test steps
- Rich HTML reporting with ExtentReports
- Easy configuration via `config.properties`
- CI integration with GitHub Actions

---



## License

This project is licensed under the MIT License.