package com.epam.university.spring.enote.repository.jpa;

import com.epam.university.spring.enote.model.Notepad;
import com.epam.university.spring.enote.repository.GenericDao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Getter
@Setter
@Repository
@Transactional
public class JpaNotepadRepositoryImpl extends JpaAbstractGenericDao<Notepad> implements GenericDao<Notepad> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean delete(Notepad entity) {
        return entityManager.createNamedQuery(Notepad.DELETE)
                .setParameter("id", entity.getId())
                .executeUpdate() != 0;
    }

    @Override
    public List<Notepad> getAll() {
        return entityManager.createNamedQuery(Notepad.ALL_SORTED, Notepad.class).getResultList();
    }

    @Override
    public boolean deleteAll() {
        return entityManager.createNamedQuery(Notepad.DELETE_ALL)
                .executeUpdate() != 0;
    }
}