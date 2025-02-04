package hello.item_service.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {


    //동시성 문제때문애 멀티쓰레드 환경에서는 ConcurrentHashMap를 사용해야한다.
    private static Map<Long, Item> store = new HashMap<>(); //static 시용

    //마찬가지로 AtomicLong같은 것을 사용한다.
    private static long sequence = 0L; //static 사용

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(),item);

        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    /**
     ArrayList로 감싸서 보내는 것의 장점
     직접보내게 되면 store 값이 변할 우려가 있기 때문에 안전하게 감싸서 보낸다.
     */
    public List<Item> findAll() {
        return new ArrayList< >(store.values());
    }

    public void  update(Long id, Item updateParam) {
        Item findItem = store.get(id);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
