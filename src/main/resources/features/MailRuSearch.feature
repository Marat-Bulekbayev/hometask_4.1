Feature: MailRu search test
Background:
  Given user opens mail.ru main page

Scenario Outline: MailRu search
  When user enters "<search_query>" and press search
  Then first search result contains "<search_query>"

  Examples:
    | search_query |
    |  iphone 12   |
    |  windows 11  |
    | cucumber.io  |
