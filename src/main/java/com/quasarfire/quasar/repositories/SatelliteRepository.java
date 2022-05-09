package com.quasarfire.quasar.repositories;

import com.quasarfire.quasar.models.AllianceTopSecretModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SatelliteRepository extends CrudRepository <AllianceTopSecretModel, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM alliance_top_secret ORDER BY id DESC LIMIT :limit")
    List<AllianceTopSecretModel> getLatestRecords(@Param("limit") int limit);
}
