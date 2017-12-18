package com.hellokoding.mail;

public class Main {
    public static void main(String[] args) {
        String fromUserEmail = args[0];
        String fromUserAccessToken = args[1];
        String toUserEmail = args[2];

        SendingMailThroughGmailSMTPServer sendingMailThroughGmailSMTPServer = new SendingMailThroughGmailSMTPServer();
        sendingMailThroughGmailSMTPServer.sendMail(fromUserEmail, fromUserAccessToken, toUserEmail, "Sending Email Through Gmail SMTP Server with Java Mail API and OAuth 2 Authorization", "Hi,\n\rThis is a programmatic email.");
    }
}
