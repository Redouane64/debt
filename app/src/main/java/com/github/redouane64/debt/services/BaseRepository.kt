package com.github.redouane64.debt.services

interface BaseRepository<TEntity> {
    fun create(entity: TEntity);
    fun delete(id: Int);
    fun getAll() : Array<TEntity>;
}