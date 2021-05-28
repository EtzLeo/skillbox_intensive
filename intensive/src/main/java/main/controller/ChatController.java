package main.controller;

import main.model.Message;
import main.model.User;
import main.repository.MessageRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Контроллер чата.
 */
@RestController
public class ChatController {

    /**
     * Репозиторий для доступа к таблице с данными о пользователях.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Репозиторий для доступа к таблице с данными о сообщениях.
     */
    @Autowired
    private MessageRepository messageRepository;

    /**
     * Получение статуса авторизации.
     *
     * @return успешно или нет
     */
    @GetMapping(path = "/api/auth")
    public HashMap<String, Boolean> authorise() {
        HashMap<String, Boolean> response = new HashMap<>();
        String sessionId = getSessionId();
        User user = userRepository.getBySessionId(sessionId);
        response.put("result", user != null);
        return response;
    }

    /**
     * Создание пользователя при логине.
     *
     * @param request информация о запросе
     * @return ответ на запрос
     */
    @PostMapping(path = "/api/users")
    public HashMap<String, Boolean> addUser(HttpServletRequest request) {
        HashMap<String, Boolean> response = new HashMap<>();
        String sessionId = getSessionId();

        String name = request.getParameter("name");

        User user = new User();
        user.setName(name);
        user.setRegTime(new Date());
        user.setSessionId(sessionId);
        userRepository.save(user);

        response.put("result", true);
        return response;
    }

    /**
     * Получение списка пользователей.
     *
     * @return список пользователей
     */
    @GetMapping(path = "/api/users")
    public HashMap<String, List> getUsersList() {
        HashMap<String, List> response = new HashMap<>();
        List<User> usersList = new ArrayList<>();
        userRepository.findAll().forEach(usersList::add);

        response.put("users", usersList);
        return response;
    }

    /**
     * Отправка сообщения.
     *
     * @param request информация о запросе
     * @return ответ на запросЫ
     */
    @PostMapping(path = "/api/messages")
    public HashMap<String, Boolean> sendMessage(HttpServletRequest request) {
        HashMap<String, Boolean> response = new HashMap<>();
        String sessionId = getSessionId();

        String text = request.getParameter("text");

        Message message = new Message();
        message.setText(text);
        message.setDeliveringTime(new Date());
        message.setUserId(userRepository.getBySessionId(sessionId).getId());
        messageRepository.save(message);

        response.put("result", true);
        return response;
    }

    /**
     * Возвращает id текущего сеанса.
     *
     * @return id текущего сеанса
     */
    private String getSessionId() {
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }
}
