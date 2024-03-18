package com.example;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MySimpleTelegramBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "test_converter123_bot";
    }

    public MySimpleTelegramBot(TelegramBotsApi telegramBotsApi) throws TelegramApiException {
        super("6739929275:AAEoBxB4SlpNoRRhlMk-SmOwUKMvmtvQEog");
        telegramBotsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            if (message.hasText()) {
                String text = message.getText();
                SendMessage sendMessage = new SendMessage(String.valueOf(chatId), text);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
