package com.resistence.network.application.service;

import com.resistence.network.application.builder.ItemBuilder;
import com.resistence.network.application.builder.ItemRequestBuilder;
import com.resistence.network.application.dto.request.ItemRequest;
import com.resistence.network.domain.Item;
import com.resistence.network.domain.RebeldeItem;
import com.resistence.network.infrastructure.repository.ItemRepository;
import com.resistence.network.infrastructure.repository.RebeldeItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private RebeldeItemRepository rebeldeItemRepository;

    @InjectMocks
    private ItemService itemService;

    private final Map<String, Item> itemMap = new HashMap<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        itemMap.put("Arma", ItemBuilder.umItem().comNome("Arma").comPontuacao(4).now());
        itemMap.put("Municao", ItemBuilder.umItem().comNome("Municao").comPontuacao(3).now());
        itemMap.put("Agua", ItemBuilder.umItem().comNome("Agua").comPontuacao(2).now());
        itemMap.put("Comida", ItemBuilder.umItem().comNome("Comida").comPontuacao(1).now());
    }

    @Test
    void testListarItensRetornoSemElementos(){
        when(itemRepository.findAll()).thenReturn(Collections.emptyList());
        Assertions.assertTrue(itemService.listarItens().isEmpty());
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    void testListarItensRetornoComDoisElementos(){
        when(itemRepository.findAll()).thenReturn(List.of(new Item(), new Item()));
        List<Item> itemList = itemService.listarItens();
        Assertions.assertFalse(itemList.isEmpty());
        Assertions.assertEquals(2, itemList.size());
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    void testSalvarItensComSucesso(){
        List<RebeldeItem> rebeldeItemList = List.of(new RebeldeItem(), new RebeldeItem());
        when(rebeldeItemRepository.saveAll(any())).thenReturn(rebeldeItemList);
        Assertions.assertEquals(2, itemService.salvarItens(rebeldeItemList).size());
        verify(rebeldeItemRepository, times(1)).saveAll(any());
    }

    @Test
    void validarItensTrocadosComSucesso(){
        List<ItemRequest> itemRequestList = List.of(ItemRequestBuilder.umItemRequest().comNome("Arma").comQuantidade(2).now(),
                                                    ItemRequestBuilder.umItemRequest().comNome("Comida").comQuantidade(6).now());
        Assertions.assertTrue(itemService.validarItensTrocados(itemRequestList, itemMap));
    }

    @Test
    void validarItensTrocadosComFalha(){
        List<ItemRequest> itemRequestList = List.of(ItemRequestBuilder.umItemRequest().comNome("Faca").comQuantidade(2).now(),
                                                    ItemRequestBuilder.umItemRequest().comNome("Comida").comQuantidade(6).now());
        Assertions.assertFalse(itemService.validarItensTrocados(itemRequestList, itemMap));
    }

}