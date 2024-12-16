package com.metbetestimate.betestimate.repositories;

import java.util.List;

public interface Repository<T>{
    List<T> listFromQuery(String query);
    List<T> List();
    Object get(int index);
    void create(T t);
    void update(T t,int index);
    void delete(int index);

}