package hello.item_service.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);
        //when
        Item saveItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(saveItem);
    }

    @Test
    void findAll() {
        //given
        Item itemA = new Item("itemA", 1000, 1);
        Item itemB = new Item("itemB", 2000, 2);

        itemRepository.save(itemA);
        itemRepository.save(itemB);
        //when

        List<Item> all = itemRepository.findAll();
        //then
        assertThat(all.size()).isEqualTo(2);
        assertThat(all).contains(itemA,itemB);
    }


    @Test
    void update() {
        //given
        Item itemA = new Item("itemA", 1000, 1);
        Item savedItem = itemRepository.save(itemA);
        //when
        Item updateParam = new Item("updat eItem", 2000, 2);
        itemRepository.update(itemA.getId(),updateParam);
        //then
        assertThat(itemA.getItemName()).isEqualTo(updateParam.getItemName());
    }

    @Test
    void clearStore() {
    }
}