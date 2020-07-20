package com.example.dao;

import com.example.domain.TxLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxLogDao extends JpaRepository<TxLog,String > {
}
