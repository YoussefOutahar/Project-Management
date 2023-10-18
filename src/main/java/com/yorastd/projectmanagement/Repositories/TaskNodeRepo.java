package com.yorastd.projectmanagement.Repositories;

import com.yorastd.projectmanagement.Models.TaskTreeModel.TaskNode;
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
public class TaskNodeRepo implements JpaRepository<TaskNode,Long> {
    @Override
    public void flush() {

    }

    @Override
    public <S extends TaskNode> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends TaskNode> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<TaskNode> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public TaskNode getOne(Long aLong) {
        return null;
    }

    @Override
    public TaskNode getById(Long aLong) {
        return null;
    }

    @Override
    public TaskNode getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends TaskNode> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends TaskNode> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends TaskNode> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends TaskNode> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends TaskNode> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends TaskNode> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends TaskNode, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends TaskNode> S save(S entity) {
        return null;
    }

    @Override
    public <S extends TaskNode> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<TaskNode> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<TaskNode> findAll() {
        return null;
    }

    @Override
    public List<TaskNode> findAllById(Iterable<Long> longs) {
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
    public void delete(TaskNode entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends TaskNode> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<TaskNode> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<TaskNode> findAll(Pageable pageable) {
        return null;
    }
}
