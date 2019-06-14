package br.com.crud.vehicles.service.impl;

import br.com.crud.vehicles.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public abstract class CrudServiceImpl<T, PK> implements CrudService<T, PK> {

    protected abstract JpaRepository<T, PK> getRepository();

    @Autowired
    private EntityManager entityManager;

    @Override
    public T buscarPorId(PK id) {
        Optional<T> t = getRepository().findById(id);
        return t.orElse(null);
    }

    @Override
    public List<T> buscarTodos() {
        return getRepository().findAll();
    }

    @Override
    public void deletar(PK id) {
        getRepository().delete(buscarPorId(id));
    }

    @Override
    @Transactional
    public T salvar(T entidade) {
        T saved = getRepository().saveAndFlush(entidade);
        entityManager.refresh(saved);
        return saved;
    }
}
