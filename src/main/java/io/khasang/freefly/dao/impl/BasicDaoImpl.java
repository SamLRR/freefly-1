package io.khasang.freefly.dao.impl;

import io.khasang.freefly.dao.BasicDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
public class BasicDaoImpl<T> implements BasicDao<T> {

    private final Class<T> entityClass;

    @Autowired
    protected SessionFactory sessionFactory;

    public BasicDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T create(T entity) {
        getSession().save(entity);
        return entity;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T getById(long id) {
        return getSession().get(entityClass, id);
    }

    @Override
    public List<T> getList() {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(root);

        return getSession().createQuery(criteriaQuery).list();
    }

    @Override
    public T update(T entity) {
        getSession().update(entity);
        return entity;
    }

    @Override
    public T delById(long id) {
        T entity = getById(id);
        if (entity == null) {
            return null;
        } else {
            getSession().delete(entity);
            return entity;
        }
    }
}
