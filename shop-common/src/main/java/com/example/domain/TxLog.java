package com.example.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
@Entity(name = "shop_txlog")
@Data
public class TxLog {
    @Id
    private String txId;
    private Date createdate;

}
