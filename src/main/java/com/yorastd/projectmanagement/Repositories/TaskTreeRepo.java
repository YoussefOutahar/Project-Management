package com.yorastd.projectmanagement.Repositories;

import com.yorastd.projectmanagement.Models.TaskTreeModel.TaskTree;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class TaskTreeRepo implements JpaRepository<TaskTree,Long> {
    @Override
    public void flush() {

    }

    @Override
    public <S extends TaskTree> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends TaskTree> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<TaskTree> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public TaskTree getOne(Long aLong) {
        return null;
    }

    @Override
    public TaskTree getById(Long aLong) {
        return null;
    }

    @Override
    public TaskTree getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends TaskTree> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends TaskTree> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends TaskTree> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends TaskTree> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TaskTree> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends TaskTree> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends TaskTree, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends TaskTree> S save(S entity) {
        return null;
    }

    @Override
    public <S extends TaskTree> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<TaskTree> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<TaskTree> findAll() {
        return null;
    }

    @Override
    public List<TaskTree> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(TaskTree entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends TaskTree> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<TaskTree> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<TaskTree> findAll(Pageable pageable) {
        return null;
    }
}