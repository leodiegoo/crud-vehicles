package br.com.crud.vehicles.controller;

import br.com.crud.vehicles.model.dto.ResponseDTO;
import br.com.crud.vehicles.service.CrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
public abstract class CrudController<T, PK> {

    protected abstract CrudService<T, PK> getService();

    @GetMapping
    public ResponseEntity findAll() {
        ResponseDTO response = new ResponseDTO();
        try {
            response.setModel(getService().buscarTodos());
            response.setSuccess(ResponseDTO.BOOLEAN_TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            String message = "Ocorreu um erro ao buscar os dados";
            log.error(message, e);
            response.setMessage(message);
            response.setSuccess(ResponseDTO.BOOLEAN_FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody T entidade) {
        ResponseDTO response = new ResponseDTO();
        try {
            response.setModel(getService().salvar(entidade));
            response.setSuccess(ResponseDTO.BOOLEAN_TRUE);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            String message = "Ocorreu um erro ao salvar os dados";
            log.error(message, e);
            response.setMessage(message);
            response.setSuccess(ResponseDTO.BOOLEAN_FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable PK id) {
        ResponseDTO response = new ResponseDTO();
        try {
            T t = getService().buscarPorId(id);
            if (t != null) {
                response.setModel(t);
                response.setSuccess(ResponseDTO.BOOLEAN_TRUE);
                return ResponseEntity.ok(response);
            }
            response.setSuccess(ResponseDTO.BOOLEAN_FALSE);
            response.setMessage("Registro não encontrado");
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            String message = "Ocorreu um erro ao buscar";
            log.error(message, e);
            response.setMessage(message);
            response.setSuccess(ResponseDTO.BOOLEAN_FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable PK id) {
        ResponseDTO response = new ResponseDTO();
        try {
            if (getService().buscarPorId(id) != null) {
                response.setSuccess(ResponseDTO.BOOLEAN_TRUE);
                response.setMessage("Registro deletado com sucesso");
                getService().deletar(id);
                return ResponseEntity.ok(response);
            }
            response.setSuccess(ResponseDTO.BOOLEAN_FALSE);
            response.setMessage("Registro (" + id + ") não encontrado");
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            String message = "Ocorreu um erro tentar deletar registro";
            log.error(message, e);
            response.setMessage(message);
            response.setSuccess(ResponseDTO.BOOLEAN_FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable PK id, @Valid @RequestBody T entidade) {
        ResponseDTO response = new ResponseDTO();
        try {
            if (getService().buscarPorId(id) != null) {
                response.setSuccess(ResponseDTO.BOOLEAN_TRUE);
                response.setModel(getService().put(id, entidade));
                return ResponseEntity.ok(response);
            }
            response.setMessage("Registro (" + id + ") não encontrado");
            response.setSuccess(ResponseDTO.BOOLEAN_FALSE);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            String message = "Ocorreu um erro ao tentar atualizar registro";
            log.error(message, e);
            response.setMessage(message);
            response.setSuccess(ResponseDTO.BOOLEAN_FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
