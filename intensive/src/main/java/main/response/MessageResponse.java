package main.response;

/**
 * Формат для вывода сообщения.
 */
public class MessageResponse {

    /**
     * Имя.
     */
    private String name;

    /**
     * Время отправки.
     */
    private String time;

    /**
     * Текст.
     */
    private String text;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
