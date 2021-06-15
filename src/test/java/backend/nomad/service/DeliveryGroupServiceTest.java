package backend.nomad.service;

import backend.nomad.domain.group.DeliveryGroup;
import backend.nomad.domain.group.DeliveryGroupRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DeliveryGroupServiceTest {

    @Autowired
    DeliveryGroupService deliveryGroupService;


    @Test
    public void 그룹생성() {
        DeliveryGroup deliveryGroup = new DeliveryGroup();
        deliveryGroup.setBuildingName("팔달관");

        Long deliveryGroupId = deliveryGroupService.save(deliveryGroup);

        if (deliveryGroupService.findById(deliveryGroupId) != null) {
            System.out.println("그룹 생성 완료");
            System.out.println("장소: " + deliveryGroup.getBuildingName());
        }

        assertEquals(deliveryGroup, deliveryGroupService.findById(deliveryGroupId));
    }

    @Test
    public void 그룹삭제() {
        DeliveryGroup deliveryGroup = new DeliveryGroup();
        deliveryGroup.setBuildingName("팔달관");

        Long deliveryGroupId = deliveryGroupService.save(deliveryGroup);

        deliveryGroupService.delete(deliveryGroup);

        assertEquals(null, deliveryGroupService.findById(deliveryGroupId));
    }

}