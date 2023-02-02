package com.vr.miniauthorizer.core.repository;

import com.vr.miniauthorizer.domain.CardVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends JpaRepository<CardVO, Long> {
    CardVO findByNumber(String number);


    @Query(value = "select c.* from CRD_TB c where c.NMBR = :number FOR UPDATE", nativeQuery = true)
    CardVO findByNumberWithLock( @Param("number")String number);
}
