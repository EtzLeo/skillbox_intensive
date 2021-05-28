package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Сообщение.
 */
@Entity
public class Message {

    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * Время отправки.
     */
    private Date deliveringTime;

    /**
     * Идентификатор пользователя.
     */
    private int userId;

    /**
     * Содержимое.
     */
    private String text;

    public Message() {
    }

    public Message(int id, Date deliveringTime, int userId, String text) {
        this.id = id;
        this.deliveringTime = deliveringTime;
        this.userId = userId;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDeliveringTime() {
        return deliveringTime;
    }

    public void setDeliveringTime(Date deliveringTime) {
        this.deliveringTime = deliveringTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
