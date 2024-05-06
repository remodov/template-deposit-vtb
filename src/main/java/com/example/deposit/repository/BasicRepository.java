package com.example.deposit.repository;

import com.example.deposit.entity.BasicEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BasicRepository extends CrudRepository<BasicEntity, UUID> {

    public BasicEntity findBasicEntityByMessage(String message);
}
