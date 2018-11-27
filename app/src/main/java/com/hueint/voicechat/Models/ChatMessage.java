package com.hueint.voicechat.Models;

import java.util.Date;

public class ChatMessage {
    private String messageTxt;
    private String messageUsr;
    private long messageTime;

    public ChatMessage(String messageTxt, String messageUsr) {
        this.messageTxt = messageTxt;
        this.messageUsr = messageUsr;
        messageTime = new Date().getTime();
    }

    public String getMessageTxt() {
        return messageTxt;
    }

    public void setMessageTxt(String messageTxt) {
        this.messageTxt = messageTxt;
    }

    public String getMessageUsr() {
        return messageUsr;
    }

    public void setMessageUsr(String messageUsr) {
        this.messageUsr = messageUsr;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
