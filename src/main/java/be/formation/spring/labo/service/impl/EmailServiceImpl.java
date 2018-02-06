package be.formation.spring.labo.service.impl;

import be.formation.spring.labo.model.entity.Booking;
import be.formation.spring.labo.model.entity.Event;
import be.formation.spring.labo.model.entity.Ticket;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class EmailServiceImpl {

    private static final String PATH = "C:\\Users\\forma903\\tmp\\";
    private JavaMailSender emailSender;
    private TemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    public void sendConfirmation(Booking booking) {

        MimeMessage message = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(booking.getUser().getEmail());
            helper.setSubject("Confirmation de votre commande - " + booking.getEvent().getTitle());
            String content = build(booking);
            helper.setText(content, true);

            for (Ticket ticket : booking.getTickets()) {
                final InputStreamSource imageSource = new ByteArrayResource(generateQRCode(ticket.getUuid()));
                helper.addInline(ticket.getUuid().toString(), imageSource, "image/png");
            }

            emailSender.send(message);

        } catch (MessagingException | MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }

    public void sendCancellation(Booking booking) {

        MimeMessage message = emailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(booking.getUser().getEmail());
            helper.setSubject("Annulation de votre commande - " + booking.getEvent().getTitle());
            String content = "L'événement " + booking.getEvent().getTitle() + " a malheureusement été annulé. Le remboursement sera fait sur votre compte dans les 300 jours ouvrables. Toutes nos excuses pour le désagrément.";
            helper.setText(content, false);

            emailSender.send(message);

        } catch (MessagingException | MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }

    private String build(Booking booking) {
        List<String> imageResourceNames = new ArrayList<>();

        int i = 0;
        for (Ticket ticket : booking.getTickets()) {
            generateQRCode(ticket.getUuid());
            imageResourceNames.add(ticket.getUuid().toString());
//            FileSystemResource file = new FileSystemResource(new File(PATH + ticket.getUuid() + ".png"));
//            helper.addAttachment(booking.getEvent().getTitle() + "_" + i + ".png", file);
        }

        Context context = new Context();
        context.setVariable("booking", booking);
        context.setVariable("imageResourceNames", imageResourceNames);
        return templateEngine.process("mailTemplate", context);
    }

    private byte[] generateQRCode(UUID uuid) {
        ByteArrayOutputStream bout = QRCode.from(uuid.toString())
                        .withSize(250, 250)
                        .to(ImageType.PNG)
                        .stream();
        return bout.toByteArray();

//        try {
//            OutputStream out = new FileOutputStream( PATH + uuid + ".png");
//            bout.writeTo(out);
//            out.flush();
//            out.close();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
    }
}
