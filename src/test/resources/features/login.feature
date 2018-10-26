@Api @BO
Feature: To test the login of bomaster

@login
Scenario Outline: To validate login with database
When login is performed through api with <username> and <password>
Then verify sessionId of <username> from database

Examples:
| username | password |
| bomaster | 12345678 |