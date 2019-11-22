package com.example.wowebackand.dao;

/**
 * this class will help to to make different operation at the samae time
 * @param <T>
 * @param <D>
 */
public interface DbIpml<T,D>
{
    public void operation(T t, D d);
}
