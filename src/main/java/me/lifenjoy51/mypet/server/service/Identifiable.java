package me.lifenjoy51.mypet.server.service;

/**
 * Created by lifenjoy51 on 2015-08-15.
 */
public interface Identifiable<T> {
    
    public T getId();
    public void setId(T id);
    
    public String getName();
    public void setName(String name);
    
}
