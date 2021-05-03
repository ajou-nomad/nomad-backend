package backend.nomad.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Menu {

    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Long menuId;

    private Store store;

    private String menuName;

}
