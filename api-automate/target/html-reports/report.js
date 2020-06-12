$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/java/features/create_bot.feature");
formatter.feature({
  "name": "Create an automated bot to fowrard email from one email account",
  "description": "  \t\t to other email account using automate.io bots",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Create a Gmail Bot on automate.io using API",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "base url \u003chost\u003e logs generated in \u003clog-file\u003e file",
  "keyword": "Given "
});
formatter.step({
  "name": "hit post method for login with arguments email: \u003cfrom-email\u003e and password: \u003cpassword\u003e",
  "keyword": "When "
});
formatter.step({
  "name": "response code should be 200 ok and status will be \"success\"",
  "keyword": "Then "
});
formatter.step({
  "name": "hit post method for Creating Bot to forward new email to \u003cto-email\u003e",
  "keyword": "When "
});
formatter.step({
  "name": "response code should be 200 ok and status will be \"success\" and message as \"Bot successfully stored.\"",
  "keyword": "Then "
});
formatter.step({
  "name": "hit Get method to enable bot we created in previous step",
  "keyword": "When "
});
formatter.step({
  "name": "response code should be 200 and status will be \"success\" and message as \"Bot successfully enabled.\"",
  "keyword": "Then "
});
formatter.step({
  "name": "logout the session and verify response code should be 200 ok and status will be \"success\" and message as \"Session expired!\"",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "host",
        "log-file",
        "from-email",
        "password",
        "to-email"
      ]
    },
    {
      "cells": [
        "\"https://api.automate.io\"",
        "\"TestLogSelenium\"",
        "\"vinaypawar0902@gmail.com\"",
        "\"Harharinfigar@1\"",
        "\"vkpvinay016@gmail.com\""
      ]
    }
  ]
});
formatter.scenario({
  "name": "Create a Gmail Bot on automate.io using API",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "base url \"https://api.automate.io\" logs generated in \"TestLogSelenium\" file",
  "keyword": "Given "
});
formatter.match({
  "location": "step_defs.CreateBotSteps.base_url_as_https_automate_io_logs_generated_in_file(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "hit post method for login with arguments email: \"vinaypawar0902@gmail.com\" and password: \"Harharinfigar@1\"",
  "keyword": "When "
});
formatter.match({
  "location": "step_defs.CreateBotSteps.hit_post_method_for_login_with_arguments_email_and_password(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "response code should be 200 ok and status will be \"success\"",
  "keyword": "Then "
});
formatter.match({
  "location": "step_defs.CreateBotSteps.response_code_should_be_ok_and_status_will_be_success(int,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "hit post method for Creating Bot to forward new email to \"vkpvinay016@gmail.com\"",
  "keyword": "When "
});
formatter.match({
  "location": "step_defs.CreateBotSteps.hit_post_method_for_Creating_Bot_to_forward_new_email_to(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "response code should be 200 ok and status will be \"success\" and message as \"Bot successfully stored.\"",
  "keyword": "Then "
});
formatter.match({
  "location": "step_defs.CreateBotSteps.response_code_should_be_ok_and_status_will_be_success_and_message_as_Bot_successfully_stored(int,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "hit Get method to enable bot we created in previous step",
  "keyword": "When "
});
formatter.match({
  "location": "step_defs.CreateBotSteps.get_method_to_enable_bot_we_created_in_previous_step()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "response code should be 200 and status will be \"success\" and message as \"Bot successfully enabled.\"",
  "keyword": "Then "
});
formatter.match({
  "location": "step_defs.CreateBotSteps.response_code_should_be_ok_and_status_will_be_success_and_message_as_Bot_successfully_enabled(int,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "logout the session and verify response code should be 200 ok and status will be \"success\" and message as \"Session expired!\"",
  "keyword": "And "
});
formatter.match({
  "location": "step_defs.CreateBotSteps.logout_the_session_and_verify_message_Session_expired(int,java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
});