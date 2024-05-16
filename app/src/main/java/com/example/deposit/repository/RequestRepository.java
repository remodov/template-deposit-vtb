package com.example.deposit.repository;

import com.example.deposit.entity.RequestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;



@Repository
public interface RequestRepository extends CrudRepository<RequestEntity, UUID> {
}
