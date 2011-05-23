package bestever;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

    private List<EmailRecipient> recipients;

    private String fromEmail;

    private String fromName;

    public MailSender(String fromEmail, String fromName) {
        this.fromEmail = fromEmail;
        this.fromName = fromName;
    }

    public void setRecipients(List<EmailRecipient> recipients) {
        this.recipients = recipients;
    }

    public void sendMail(Story story) {

        Properties props = new Properties();

        Session session = Session.getDefaultInstance(props, null);

        try {
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(fromEmail, fromName));

            // Create addresses
            List<EmailRecipient> activeEmails = getActiveRecipients(recipients);
            List<InternetAddress> addresses = makeAddresses(activeEmails);
            String addressString = makeAddressString(activeEmails);
            msg.setRecipients(Message.RecipientType.TO, addresses.toArray(new Address[0]));

            msg.setSubject("Best Ever Story of the Week");

            String body = createBody(story) + "Sent to: " + addressString;
            msg.setText(body);

            Transport.send(msg);

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    private String createBody(Story story) {
        StringBuilder builder = new StringBuilder();

        builder.append("This week's story is submitted by ").append(story.getSubmitter()).append(": \n\n");

        builder.append(story.getStory()).append("\n\n");

        return builder.toString();

    }

    private String makeAddressString(List<EmailRecipient> addresses) {
        StringBuilder builder = new StringBuilder();

        for (EmailRecipient email : addresses) {
            builder.append(email.getName()).append(" <").append(email.getEmail()).append(">,");
        }

        String temp = builder.toString();
        return temp.substring(0, temp.length() - 1);
    }

    private List<EmailRecipient> getActiveRecipients(List<EmailRecipient> recipients) throws Exception {

        List<EmailRecipient> addresses = new ArrayList<EmailRecipient>();

        for (EmailRecipient recipient : recipients) {

            if (recipient.getActive().equals("Y")) {
                addresses.add(recipient);
            }

        }

        return addresses;
    }

    private List<InternetAddress> makeAddresses(List<EmailRecipient> recipients) throws Exception {

        List<InternetAddress> addresses = new ArrayList<InternetAddress>();

        for (EmailRecipient recipient : recipients) {
            addresses.add(new InternetAddress(recipient.getEmail(), recipient.getName()));
        }

        return addresses;
    }

}
