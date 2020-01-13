package com.veselov.alex.racecar.service.telegram;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bot extends TelegramLongPollingBot {

    @Getter
    @Value("${telegram.bot.botname}")
    String botUsername;

    @Getter
    @Value("${telegram.bot.token}")
    String botToken;

    @Getter
    @Value("${telegram.bot.chatId}")
    String chatId;

    /**
     * It works when a new message is came.
     *
     * @param update user message data
     */
    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        this.sendMsg(update.getMessage().getChatId().toString(), message);
    }

    /**
     * It sends messages.
     *
     * @param chatId
     * @param message
     */
    public synchronized void sendMsg(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        log.info("Telegram bot sends to chat -> {}, this message -> {}", chatId, message);
        sendMessage.setChatId(this.chatId);
        sendMessage.setText(message);
        try {
            super.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Exception: {}, {}", e.toString(), e.getMessage());
        }
    }
}
