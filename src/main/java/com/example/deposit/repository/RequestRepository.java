package com.example.deposit.repository;

import com.example.deposit.entity.RequestEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * RequestRepository.
 */
@Repository
public interface RequestRepository extends CrudRepository<RequestEntity, UUID> {
}
