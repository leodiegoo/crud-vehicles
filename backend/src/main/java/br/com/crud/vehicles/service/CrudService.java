package br.com.crud.vehicles.service;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T, PK> extends Serializable {

    void salvar(T entidade);

    void deletar(PK id);

    T buscarPorId(PK id);

    List<T> buscarTodos();

}
