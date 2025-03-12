package org.nda.hiber;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RaccoonRepo extends JpaRepository<Raccoon, String> {

    @Query(value="MERGE INTO RACCOON (NAME, FAT, CLEAN, HEADCOUNT, VERSION)" +
            " KEY (NAME) VALUES (:#{#entity.name}, :#{#entity.fat}, :#{#entity.clean}, :#{#entity.headCount}, :#{#entity.version});",
    nativeQuery = true)
    @Modifying
    void upsert(Raccoon entity);

}
