package com.quasarfire.quasar.repositories;

import com.quasarfire.quasar.models.AllianceTopSecretModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SatelliteRepository extends CrudRepository <AllianceTopSecretModel, Long> {

}
