package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repsoitory.ItemRepository;
import jpabook.jpashop.repsoitory.MemberRepository;
import jpabook.jpashop.repsoitory.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //주문
    @Transactional
    public Long order(Long memberid, Long itemId, int count) {

        //멤버 및 아이템 조회
        Member member = memberRepository.findOne(memberid);
        Item item = itemRepository.findOne(itemId);

        //배송 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        //주문 상품 생성
        OrderItem orderitem = OrderItem.createOrderitem(item, item.getPrice(), count);



        //주문생성
        Order order = Order.createOrder(member, delivery, orderitem);
        orderRepository.save(order);

        return order.getId();

    }

    //취소
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티를 조회
        Order order = orderRepository.findOne(orderId);
        //취소
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        List<Order> all = orderRepository.findAllByString(orderSearch);
        return all;
    }


}
