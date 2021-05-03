package backend.nomad.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Store {

    @Id
    @GeneratedValue
    @Column(name = "store_id")
    private Long storeId;

    private String storeName;

    private String storeAddress;

    private String storeCall;

    private String sotreOpen;

}
