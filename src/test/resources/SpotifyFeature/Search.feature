Feature: Verify Search Functinality

  Scenario: Test Search API With Valid Data
    Given GET a search track payload
      | songname  | type  |
      | Tum Se Hi | track |
    When user calls with Get request
    Then API executes with status code 200
