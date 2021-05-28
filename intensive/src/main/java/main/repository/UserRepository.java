package main.repository;

import main.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий для доступа к таблице с данными о пользователях.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Найти пользователя по номеру сессии.
     *
     * @param sessionId id сессии
     * @return пользователь
     */
    User getBySessionId(String sessionId);
}
