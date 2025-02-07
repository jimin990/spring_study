package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.Exception.NotEnoughStockException;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repsoitory.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class OrderServiceTest {


    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() {
        //given
        //멤버 생성
        Member member = createmember();

        //아이템 생성
        Book book = createBook("book", 10000, 100);
        //when

        int orderCount = 10;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //then

        Order order = orderRepository.findOne(orderId);
        Assertions.assertEquals(OrderStatus.ORDER, order.getStatus(), "상품 주문시 상태는 ORDER");
        Assertions.assertEquals(1,order.getOrderItems().size(), "주문한 상품 종류는 정확한가?");
        Assertions.assertEquals(100000,order.getTotalPrice(), "주문 가격은 상품 X 수량 이다");
        Assertions.assertEquals(90, book.getStockQuantity(),"주문 수량 만큼 재고가 줄어야한다.");

    }

    @Test
    public void 주문취소() {
        //given
        //멤버 생성
        Member member = createmember();
        //아이템 생성
        Book book = createBook("book", 10000, 100);

        int orderCount = 10;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        assertEquals(90,book.getStockQuantity(),"주문하고 재고가 줄었는가?");
        //when
        orderService.cancelOrder(orderId);

        //then
        Order order = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.CANCEL,order.getStatus(),"주문 상태가 취소 되었는가?");
        assertEquals(100,book.getStockQuantity(),"주문전 재고량과 같은가?");


    }

    @Test
    public void 상품주문_재고수량초과() {
        //given
        //멤버 생성
        Member member = createmember();
        //아이템 생성
        Book book = createBook("book", 10000, 100);

        //when
        Executable executable = () -> {
            int orderCount = 101;
            Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        };

        // then
        NotEnoughStockException exception = assertThrows(NotEnoughStockException.class, executable);
        assertEquals("need more stock", exception.getMessage());

    }

    private Member createmember() {
        Member member = new Member();
        member.setName("name");
        Address address = new Address("city", "street", "zipcode");
        member.setAdress(address);
        em.persist(member);
        return member;
    }

    private Book createBook(String book1, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(book1);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);

        em.persist(book);
        return book;
    }


}