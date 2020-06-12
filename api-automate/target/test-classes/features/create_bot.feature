#Author: Vinay Kumar Pawar
#Last Updated: 12 June 2020
Feature: Create an automated bot to fowrard email from one email account
  		 to other email account using automate.io bots

  Scenario Outline: Create a Gmail Bot on automate.io using API
    Given base url <host> logs generated in <log-file> file
    When hit post method for login with arguments email: <from-email> and password: <password>
    Then response code should be 200 ok and status will be "success"
    When hit post method for Creating Bot to forward new email to <to-email>
    Then response code should be 200 ok and status will be "success" and message as "Bot successfully stored."
    When hit Get method to enable bot we created in previous step
    Then response code should be 200 and status will be "success" and message as "Bot successfully enabled."
    And logout the session and verify response code should be 200 ok and status will be "success" and message as "Session expired!"

    Examples: 
      | host                      | log-file          | from-email             | password       | to-email              |
      | "https://api.automate.io" | "TestLogSelenium" | "loginemail@gmail.com" | "YourPassword" | "Youremail@gmail.com" |
