package br.com.crud.vehicles.service.impl;

import br.com.crud.vehicles.repository.JOOQRepository;
import br.com.crud.vehicles.service.CrudService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class CrudServiceImpl<T, PK> implements CrudService<T, PK> {

    protected abstract JOOQRepository<T, PK> getRepository();

    @Override
    public T buscarPorId(PK id) {
        T t = getRepository().findById(id);
        return t;
    }

    @Override
    public List<T> buscarTodos() {
        return getRepository().findAll();
    }

    @Override
    public void deletar(PK id) {
        getRepository().delete(id);
    }

    @Override
    @Transactional
    public T salvar(T entidade) {
        T saved = getRepository().add(entidade);
        return saved;
    }
}
