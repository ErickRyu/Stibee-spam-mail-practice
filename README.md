# Spam mail practice V0.3
It takes keywords and emails from Google Sheets and combinates keywords to make subject and text and sends them out.


# Tutorials what I saw
* [Sending email via Gmail SMTP example](http://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/)
* [Google Sheets Java Quickstart](https://developers.google.com/sheets/api/quickstart/java)


# How to use
* Enter your `gmail` and `password` in the `mail.properties`
* Allow lesssecureapps [here](https://www.google.com/settings/security/lesssecureapps) ([see also](https://support.google.com/accounts/answer/6010255))
* Set recipents email address at [spreadsheet](https://docs.google.com/spreadsheets/d/1hm5SOn4Sch8ulN-IlF1nDFvjyVVZ_g309CwaMIYCYbs/edit?usp=sharing)
* Follow google sheets java quickstart Step 1. You should download `client_secret.json` and store at `src/main/resources`
* `$ gradle -q run`

# Development Journal
* [Development Journal - workflowy](https://workflowy.com/s/GS0s.GIfH18Wb9z)
