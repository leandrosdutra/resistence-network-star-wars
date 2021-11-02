package com.resistence.network.application.service;

import com.resistence.network.application.dto.request.ItemRequest;
import com.resistence.network.domain.Item;
import com.resistence.network.domain.RebeldeItem;
import com.resistence.network.infrastructure.repository.ItemRepository;
import com.resistence.network.infrastructure.repository.RebeldeItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;
    private final RebeldeItemRepository rebeldeItemRepository;

    public ItemService(ItemRepository itemRepository, RebeldeItemRepository rebeldeItemRepository) {
        this.itemRepository = itemRepository;
        this.rebeldeItemRepository = rebeldeItemRepository;
    }

    public List<Item> listarItens(){
        logger.debug("Listando todos os itens");
        return itemRepository.findAll()
                             .stream()
                             .sorted(Comparator.comparing(Item::getPontuacao))
                             .collect(Collectors.toList());
    }

    public List<RebeldeItem> salvarItens(List<RebeldeItem> rebeldeItemList){
        logger.debug("Salvando todos os itens do rebelde");
        return rebeldeItemRepository.saveAll(rebeldeItemList);
    }

    public boolean validarItensTrocados(List<ItemRequest> itemRequestList, Map<String, Item> itemMap){
        return itemRequestList.stream()
                              .noneMatch(it -> it.isNaoExistente(itemMap));
    }

}