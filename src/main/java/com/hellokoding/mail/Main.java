package com.hellokoding.mail;

public class Main {
    public static void main(String[] args) {
        String fromUserEmail = args[0];
        String fromUserAccessToken = args[1];
        String toUserEmail = args[2];
        String subject = args[3];
        String body = args[4];

        SendingMailThroughGmailSMTPServer sendingMailThroughGmailSMTPServer = new SendingMailThroughGmailSMTPServer();
        sendingMailThroughGmailSMTPServer.sendMail(fromUserEmail, fromUserAccessToken, toUserEmail, subject, body);
    }
}
