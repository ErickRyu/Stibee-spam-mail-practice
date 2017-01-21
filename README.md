# Spam mail practice V0.1
This project is a program that takes a title and text from Google Sheets and sends it out.


# Tutorials what I saw
* [Sending email via Gmail SMTP example](http://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/)
* [Google Sheets Java Quickstart](https://developers.google.com/sheets/api/quickstart/java)


# How to use
* Enter your `gmail` and `password` in the `MailSender.java`
* Allow lesssecureapps [here](https://www.google.com/settings/security/lesssecureapps) ([see also](https://support.google.com/accounts/answer/6010255))
* Follow google sheets java quickstart Step 1. You should download `client_secret.json` and store at `src/main/resources`
* `$ gradle -q run`

