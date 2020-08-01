package com.evecodeideas.com.springboot.app.models.dao;

import com.evecodeideas.com.springboot.app.models.entity.Cliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClienteDaoImpl implements IClienteDao {

    @PersistenceContext //
    private EntityManager entityManager;//operaciones a la bd

    @Override
    @Transactional(readOnly = true) //Envuelve el metodo en una transaccion
    public List<Cliente> findAll() {
        return entityManager.createQuery("from Cliente").getResultList();
    }

    @Override
    @Transactional
    public void save(Cliente cliente) {
        entityManager.persist(cliente);
    }

}
