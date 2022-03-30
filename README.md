<h1 align="center"> Credit Card Validation - API </h1>

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## ✅ Features

A simple credit card validation api that has and 2 endpoints: 

- `/credit-card-validation-api/credit-card/validateCreditCard` : To validate a credit card. You just need to send a request with the credit
  card number, that accepts both number formats, with white spaces and with
  no white spaces, and the expiring date. The API will return a 200 status
  if it's a valid card or 400 if not.


- `/credit-card-validation-api/credit-card/getSupportedCardIssuers` : To get all available credit card issuers.

This API is also part of a greater implementation of other two APIs that simulate a purchase, validate and notify the user about the process.

## ⚒️ Technologies

- Java 11
- Spring-Boot
- Maven
- Swagger
- JUnit
- RabbitMQ

## ☁️ Host

- AWS - Elastic Beanstalk

## 🔗 Links

- 📖 Swagger Documentation:
- 😄 My LinkedIn Profile: https://www.linkedin.com/in/gabriel-santos-20737b171
- 🔗 Related APIs: 
  * https://github.com/gabrielrabelomachadosantos/purchase-api
  * https://github.com/gabrielrabelomachadosantos/notifications-api

