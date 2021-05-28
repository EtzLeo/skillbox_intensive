package main.response;

/**
 * Формат для добавления сообщения.
 */
public class AddMessageResponse {

    /**
     * Результат.
     */
    private boolean result;

    /**
     * Время.
     */
    private String time;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
