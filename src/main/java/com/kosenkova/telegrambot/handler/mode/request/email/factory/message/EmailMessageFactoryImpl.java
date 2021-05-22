package com.kosenkova.telegrambot.handler.mode.request.email.factory.message;

import com.kosenkova.telegrambot.model.email.EmailData;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
public class EmailMessageFactoryImpl implements EmailMessageFactory {

    private static final String MESSAGE_PATTERN = """
            Заявка:
                Клиент: %s
                Номер телефона: %s
                Email: %s
            """;

    @Value("${email.recipient.address}")
    private String recipientEmail;

    private final JavaMailSender javaMailSender;

    @Override
    @SneakyThrows
    public MimeMessage build(EmailData emailData) {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        String text = String.format(MESSAGE_PATTERN, emailData.getName(), emailData.getNumber(), emailData.getEmail());
        helper.setFrom("noreply@bot.com");
        helper.setTo(recipientEmail);
        helper.setSubject("Заявка клиента");
        helper.setText(text);

        if (emailData.getFile() != null) {
            String fileExtension = FilenameUtils.getExtension(emailData.getFile().getName());
            helper.addAttachment("Техническое задание." + fileExtension, emailData.getFile());
        }

        return message;
    }
}
