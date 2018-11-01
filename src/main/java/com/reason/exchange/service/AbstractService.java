package com.reason.exchange.service;

import java.util.List;

public abstract class AbstractService<T> {
    
    abstract public T save(T t);
    abstract public T getOne(int id);
    abstract public boolean update(T t);
    abstract public boolean delete(T t);
    abstract public List<T> getAll();
}
