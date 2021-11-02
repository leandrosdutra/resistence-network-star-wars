package com.resistence.network.application.processor;

import com.resistence.network.application.dto.request.NegociacaoRequest;
import com.resistence.network.application.service.ItemService;
import com.resistence.network.application.transformer.ItemTransformer;
import com.resistence.network.domain.Item;
import com.resistence.network.domain.Rebelde;
import com.resistence.network.domain.RebeldeItem;
import com.resistence.network.infrastructure.repository.RebeldeItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EntradaProcessor implements NegociacaoProcessor {

    private static final Logger logger = LoggerFactory.getLogger(EntradaProcessor.class);

    private final RebeldeItemRepository rebeldeItemRepository;
    private final ItemService itemService;

    public EntradaProcessor(RebeldeItemRepository rebeldeItemRepository, ItemService itemService) {
        this.rebeldeItemRepository = rebeldeItemRepository;
        this.itemService = itemService;
    }

    @Override
    public void processar(Rebelde primeiroRebelde, Rebelde segundoRebelde, NegociacaoRequest negociacaoRequest) {
        logger.debug("Buscando os itens na base de dados");
        Map<String, Item> itemMap = itemService.listarItens().stream().collect(Collectors.toMap(Item::getNome, Function.identity()));
        logger.debug("Efetuando a entrada dos itens da negociacao");
        negociacaoRequest.getItensPrimeiroRebeldeList()
                         .forEach(it -> {
                             Optional<RebeldeItem> optional = segundoRebelde.getRebeldeItemList()
                                                                            .stream()
                                                                            .filter(el -> el.getOidItem().getNome().equalsIgnoreCase(it.getNome()))
                                                                            .findAny();
                             optional.ifPresentOrElse(
                                     value -> rebeldeItemRepository.save(value.somarQuantidade(it.getQuantidade())),
                                     () -> rebeldeItemRepository.save(ItemTransformer.toEntity(it.getQuantidade(), itemMap, it.getNome(), segundoRebelde)));
                        });
        negociacaoRequest.getItensSegundoRebeldeList()
                         .forEach(it -> {
                             Optional<RebeldeItem> optional = primeiroRebelde.getRebeldeItemList()
                                                                             .stream()
                                                                             .filter(el -> el.getOidItem().getNome().equalsIgnoreCase(it.getNome()))
                                                                             .findAny();
                             optional.ifPresentOrElse(
                                     value -> rebeldeItemRepository.save(value.somarQuantidade(it.getQuantidade())),
                                     () -> rebeldeItemRepository.save(ItemTransformer.toEntity(it.getQuantidade(), itemMap, it.getNome(), primeiroRebelde)));
                         });


    }

}
