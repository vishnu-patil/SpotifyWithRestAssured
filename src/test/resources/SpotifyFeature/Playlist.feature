Feature: Validate Playlist API

  Scenario: verify if create playlist is working
    Given CREATE playlist api payload
    When user calls with POST http request
    Then API call executed with status code 201

  Scenario: verify if fetch playlist functionality is working
    Given GET playlist api payload
    When user calls with GET http request
    Then API call executed with status code 200

  Scenario: verify if update playlist functionality is working
    Given UPDATE playlist api payload
    When user calls with PUT http request
    Then API call execute with status code 200
