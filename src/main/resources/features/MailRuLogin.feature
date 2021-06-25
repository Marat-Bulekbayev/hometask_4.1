Feature: MailRu login test

Scenario: MailRu login
  Given user opens mail.ru main page
  When user performs authorization
  Then write new mail button is displayed
