package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Пользователь.
 */
@Entity
public class User {

    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * Дата и время регистрации.
     */
    private Date regTime;

    /**
     * Номер сессии.
     */
    private String sessionId;

    /**
     * Имя.
     */
    private String name;

    public User() {
    }

    public User(int id, Date regTime, String sessionId, String name) {
        this.id = id;
        this.regTime = regTime;
        this.sessionId = sessionId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
