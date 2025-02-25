package io.github.phongnv.restful_spring_boot.infrastruture.services;


import io.github.phongnv.restful_spring_boot.infrastruture.entities.BaseEntity;
import io.github.phongnv.restful_spring_boot.infrastruture.exceptions.AppException;
import io.github.phongnv.restful_spring_boot.infrastruture.exceptions.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
public abstract class BaseService<T extends BaseEntity> {

    private final JpaRepository<T, Serializable> repository;

    protected abstract String getNotFoundMessage();


    public JpaRepository<T, Serializable>assignGetRepository(){
        return repository;
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public List<T> saveMany(List<T> entity){
        return repository.saveAll(entity);
    }

    public T update(T entity, Serializable id) {
        T updateEntity = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NotFound, getNotFoundMessage()));

        BeanUtils.copyProperties(entity, updateEntity, "id", "createdAt", "updatedAt");
        return repository.saveAndFlush(updateEntity);
    }

    public <S extends T> List<S> saveAll(List<S> entities) {
        return repository.saveAll(entities);
    }

    public T findOneByIdOrFail(Serializable id) {
        return repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NotFound, getNotFoundMessage()));
    }

    public boolean existsById(Serializable id) {
        return repository.existsById(id);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public List<T> findAllById(List<Serializable> ids) {
        return repository.findAll();
    }

    public long count() {
        return repository.count();
    }

    public void deleteById(Serializable id) {
        if (!existsById(id))  {
            throw new AppException(ErrorCode.NotFound, getNotFoundMessage());
        }
        repository.deleteById(id);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public void deleteAll(List<? extends T> entities) {
        repository.deleteAll(entities);
    }

    public void softRemoveOneByIdOrFail(Serializable id) {
        T obj = repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NotFound, getNotFoundMessage()));
        obj.setDeleted_at(new Timestamp(System.currentTimeMillis()));
        repository.save(obj);
    }

    public void softRemoveManyByIdsOrFail(List<Serializable> ids) {
        List<T> entities = repository.findAllById(ids);
        if (entities.isEmpty()) {
            throw new AppException(ErrorCode.NotFound, getNotFoundMessage());
        }
        entities.forEach(entity -> entity.setDeleted_at(new Timestamp(System.currentTimeMillis())));
        repository.saveAll(entities);
    }

}