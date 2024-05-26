package org.example.ru.vtb.ppcd.example.repository

import org.example.ru.vtb.ppcd.example.entity.RequestEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RequestRepository : CrudRepository<RequestEntity, UUID>