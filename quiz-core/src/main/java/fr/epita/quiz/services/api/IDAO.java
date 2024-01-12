package fr.epita.quiz.services.api;

import fr.epita.quiz.datamodel.Choice;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public interface IDAO<T> {
    void create(T entity);
    void update(T entity);
    void delete(T entity);
    List<T> search(T entity);
    T getById(Object id);// could be Seriazable
}
