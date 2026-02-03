package org.nda.hiber;

import org.nda.hiber.ord.Body;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BodyRepo extends JpaRepository<Body, Integer>, JpaSpecificationExecutor<Body> {
}
