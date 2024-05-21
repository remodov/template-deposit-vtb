package ru.vtb.ppcd.example.repository;

import ru.vtb.ppcd.example.entity.RequestEntity;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RequestRepository extends CrudRepository<RequestEntity, UUID> {
}
