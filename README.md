# Sending Email Through Gmail SMTP Server with Java Mail API and OAuth 2 Authorization

## Guide
https://hellokoding.com/sending-email-through-gmail-smtp-server-with-java-mail-api-and-oauth-2-authorization/

## What you'll need
- JDK 1.7+
- Maven 3+
- OAuth Access Token of your Google Account. Check out more details on How to get the access token at [OAuth2DotPyRunThrough](https://github.com/google/gmail-oauth2-tools/wiki/OAuth2DotPyRunThrough)

## Stack
- Java Mail API
- OAuth2 Authorization Flow

## Run
`mvn clean package exec:java -Dexec.args="{FROM_USER_NAME} {FROM_USER_FULLNAME} {FROM_USER_ACCESSTOKEN} {TO_USER_EMAIL}"`


