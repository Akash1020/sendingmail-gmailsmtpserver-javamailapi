package com.hellokoding.mail;


import com.sun.mail.smtp.SMTPTransport;
import com.sun.mail.util.BASE64EncoderStream;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendingMailThroughGmailSMTPServer {
    Session session;

    SMTPTransport connectToSmtpServer(String host, int port, String userEmail,String userAccessToken) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.sasl.enable", "false");
        session = Session.getInstance(props);

        SMTPTransport transport = new SMTPTransport(session, null);
        transport.connect(host, port, userEmail, null);

        byte[] response = String.format("user=%s\1auth=Bearer %s\1\1", userEmail, userAccessToken).getBytes();
        response = BASE64EncoderStream.encode(response);

        transport.issueCommand("AUTH XOAUTH2 " + new String(response),
                235);

        return transport;
    }

    void sendMail(String fromUserEmail, String fromUserAccessToken, String toEmails, String subject, String body) {
        try {
            SMTPTransport smtpTransport = connectToSmtpServer("smtp.gmail.com",587, fromUserEmail, fromUserAccessToken);

            MimeMessage message = new MimeMessage(session);
            DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
            message.setSender(new InternetAddress(fromUserEmail));
            message.setSubject(subject);
            message.setDataHandler(handler);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmails));
            smtpTransport.sendMessage(message, message.getAllRecipients());


        } catch (MessagingException e) {
            Logger.getLogger(SendingMailThroughGmailSMTPServer.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

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
