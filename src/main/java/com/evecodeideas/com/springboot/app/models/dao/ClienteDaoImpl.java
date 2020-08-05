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
//    @Transactional(readOnly = true) //Envuelve el metodo en una transaccion
    public List<Cliente> findAll() {
        return entityManager.createQuery("from Cliente").getResultList();
    }

    @Override
//    @Transactional Se mueven a la clase service
    public void save(Cliente cliente) {
        if(cliente.getId() != null && cliente.getId() >0){
            entityManager.merge(cliente);//actualiza los datos existentes
        }else{
            entityManager.persist(cliente);
        }
    }

    @Override
//    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        return entityManager.find(Cliente.class, id);
    }

    @Override
//    @Transactional
    public void delete(Long id) {
//        Cliente cliente = findOne(id);
        entityManager.remove(findOne(id));
    }

}
