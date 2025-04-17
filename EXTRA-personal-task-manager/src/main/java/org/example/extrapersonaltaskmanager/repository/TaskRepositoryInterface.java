package org.example.extrapersonaltaskmanager.repository;

import org.example.extrapersonaltaskmanager.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositoryInterface extends CrudRepository<TaskEntity, Integer> {

}
