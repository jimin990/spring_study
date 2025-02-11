package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;


    /**
     * enum을 사용할때 주의!
     * enumerated를 붙혀주고, enumType을 string으로 바꿔줘야한다.
     * 기본타입이 ordinary인데 이건 숫자로 입력이 되기 때문에 중간에 값이 들어가면, 원래 3인 상태가 밀려나서 망할 수 있다
     */
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP

}
