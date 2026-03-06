![CI](https://github.com/AbdUlrahmanElBetar/ToDo_APIs_RestAssured_Project/actions/workflows/main.yml/badge.svg)
# ToDo_APIs_RestAssured_Project

API Automation Testing Framework for ToDo APIs using **Java**, **Rest Assured**, **TestNG**, **Maven**, **Allure Reports**, and **GitHub Actions**.

## Overview

This project is a simple API automation testing framework designed to validate core ToDo API functionalities and user-related operations.

It covers common API scenarios such as:

- Create ToDo
- Update ToDo
- Edit ToDo
- Delete ToDo
- User Registration
- User Login
- Response Validation
- Error Message Validation

The framework is built with a reusable structure to support maintainability, scalability, and easy execution in both local and CI environments.

## Tech Stack

- Java
- Maven
- Rest Assured
- TestNG
- Allure Reports
- GitHub Actions

## Project Structure

```text
src/test/java/com/qacart/todo
├── apis
├── base
├── config
├── data
├── models
├── steps
└── testCases

```

---

## Generate Allure Report

After running the tests, you can generate the report using:

```bash
allure serve allure-results
```
