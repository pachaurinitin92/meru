package com.meru.orders.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meru.orders.entity.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long>{

}
