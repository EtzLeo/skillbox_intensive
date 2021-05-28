package main.controller;

import main.model.Message;
import main.model.User;
import main.repository.MessageRepository;
import main.repository.UserRepository;
import main.response.AuthResponse;
import main.response.MessageResponse;
import main.response.AddMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
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

    private static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");

    /**
     * Получение статуса авторизации.
     *
     * @return успешно или нет
     */
    @GetMapping(path = "/api/auth")
    public AuthResponse authorise() {
        AuthResponse response = new AuthResponse();
        String sessionId = getSessionId();
        User user = userRepository.getBySessionId(sessionId);
        response.setResult(user!=null);
        if(user!=null){
            response.setName(user.getName());
        }
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
     * Получение списка сообщений.
     *
     * @return список сообщений
     */
    @GetMapping(path = "/api/messages")
    public HashMap<String, List> getMessages() {
        HashMap<String, List> response = new HashMap<>();
        List<MessageResponse> messages = new ArrayList<>();
        messageRepository.findAll().forEach(message -> {
            MessageResponse messageResponse = new MessageResponse();
            messageResponse.setTime(formatter.format(message.getDeliveringTime()));
            messageResponse.setName(userRepository.getBySessionId(getSessionId()).getName());
            messageResponse.setText(message.getText());
            messages.add(messageResponse);
        });

        response.put("messages", messages);
        return response;
    }


    /**
     * Отправка сообщения.
     *
     * @param request информация о запросе
     * @return ответ на запросЫ
     */
    @PostMapping(path = "/api/messages")
    public AddMessageResponse sendMessage(HttpServletRequest request) {
        AddMessageResponse response = new AddMessageResponse();
        String sessionId = getSessionId();

        String text = request.getParameter("text");
        Date time = new Date();

        Message message = new Message();
        message.setText(text);
        message.setDeliveringTime(time);
        message.setUserId(userRepository.getBySessionId(sessionId).getId());
        messageRepository.save(message);

        response.setResult(true);
        response.setTime(formatter.format(time));
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
