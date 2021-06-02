package backend.nomad.domain.store;

import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.orderitem.OrderItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Menu {

    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Long menuId;

    private String menuName;


    private Double cost;
    private String description;
//    detail?
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

//    @OneToMany(mappedBy = "menu")
//    private List<OrderItem> orderItem = new ArrayList<>();

    public Menu(String menuName, Double cost, String description, Store store, String imgUrl) {
        this.menuName = menuName;
        this.cost = cost;
        this.description = description;
        this.store = store;
        this.imgUrl = imgUrl;
    }

    public Menu() {

    }

    public void addMenu(Store store) {
        this.store = store;
        store.getMenu().add(this);
    }
}

