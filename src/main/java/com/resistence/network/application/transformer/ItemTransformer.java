package com.resistence.network.application.transformer;

import com.resistence.network.application.dto.ItemRebeldeDto;
import com.resistence.network.domain.Item;
import com.resistence.network.domain.Rebelde;
import com.resistence.network.domain.RebeldeItem;
import java.util.Map;

public class ItemTransformer {

    private ItemTransformer() {
    }

    public static RebeldeItem toEntity(int quantidade, Map<String, Item> itemMap, String nomeItem, Rebelde rebelde){
        var rebeldeItem = new RebeldeItem();
        rebeldeItem.setOidRebelde(rebelde);
        rebeldeItem.setOidItem(itemMap.get(nomeItem));
        rebeldeItem.setQuantidade(quantidade);
        return rebeldeItem;
    }

    public static ItemRebeldeDto toDto(RebeldeItem rebeldeItem){
        var itemRebeldeDto = new ItemRebeldeDto();
        itemRebeldeDto.setNome(rebeldeItem.getOidItem().getNome());
        itemRebeldeDto.setQuantidade(rebeldeItem.getQuantidade());
        return itemRebeldeDto;
    }

}