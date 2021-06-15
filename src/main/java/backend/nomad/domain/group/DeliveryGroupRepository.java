package backend.nomad.domain.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DeliveryGroupRepository extends JpaRepository<DeliveryGroup, Long> {

    List<DeliveryGroup> findByGroupTypeAndOrderStatus(GroupType groupType, OrderStatus orderStatus);
    List<DeliveryGroup> findByStoreId(Long storeId);
    List<DeliveryGroup> findByOrderStatus(OrderStatus orderStatus);

    List<DeliveryGroup> findByOrderStatusAndStoreId(OrderStatus orderStatus, Long storeId);
    List<DeliveryGroup> findByOrderStatusOrOrderStatusOrOrderStatusOrOrderStatusOrOrderStatus(OrderStatus orderStatusA, OrderStatus orderStatusB, OrderStatus orderStatusC, OrderStatus orderStatusD, OrderStatus orderStatusE);
    List<DeliveryGroup> findByDayAndOrderStatus(Integer day, OrderStatus orderStatus);

}
