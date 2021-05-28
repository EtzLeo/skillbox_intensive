package main.response;

/**
 * Формат для аутентификации.
 */
public class AuthResponse {

    /**
     * Результат.
     */
    private Boolean result;

    /**
     * Имя.
     */
    private String name;

    public AuthResponse() {
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
