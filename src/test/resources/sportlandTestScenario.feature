Feature: we need to find boys football shoes, and save information about them in .txt file

  Scenario: find correct shoes and save them
    Given shop "https://sportland.lv/" webpage
    When we click on side menu "PRODUKTI" tab
    Then we move mouse over zeni tab
    And choose from apavi list "Futbols"
    When sort by "Atlasīt"
    And select filters:
      | Futbola apavi cietam segumam |
      | Nike                         |
#    And select filters: "Futbola apavi cietam segumam"
#    And select filters: "Nike"
    Then check that all items has "NIKE" brand
    And check that all items is on sale
    And save information about shoes below 50eur in txt file

