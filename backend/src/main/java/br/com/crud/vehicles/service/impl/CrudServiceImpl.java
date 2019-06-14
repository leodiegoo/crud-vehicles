package br.com.crud.vehicles.service.impl;

import br.com.crud.vehicles.service.CrudService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class CrudServiceImpl<T, PK> implements CrudService<T, PK> {

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
    public void salvar(T entidade) {
        getRepository().save(entidade);
    }

    protected abstract JpaRepository<T, PK> getRepository();
}
