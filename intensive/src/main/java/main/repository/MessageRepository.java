package main.repository;

import main.model.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий для доступа к таблице с данными о сообщениях.
 */
public interface MessageRepository extends CrudRepository<Message, Integer> {
}
