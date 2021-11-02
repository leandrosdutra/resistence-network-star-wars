package com.resistence.network.application.service;

import com.resistence.network.application.dto.RebeldeDto;
import com.resistence.network.application.dto.request.AtualizarLocalizacaoRequest;
import com.resistence.network.application.dto.request.RebeldeRequest;
import com.resistence.network.application.transformer.ItemTransformer;
import com.resistence.network.application.transformer.LocalizacaoTransformer;
import com.resistence.network.application.transformer.RebeldeTransformer;
import com.resistence.network.application.util.ConstantsUtil;
import com.resistence.network.domain.Item;
import com.resistence.network.domain.Localizacao;
import com.resistence.network.domain.Rebelde;
import com.resistence.network.domain.RebeldeItem;
import com.resistence.network.domain.exceptions.ErrorMessage;
import com.resistence.network.domain.exceptions.NotFoundException;
import com.resistence.network.infrastructure.repository.LocalizacaoRepository;
import com.resistence.network.infrastructure.repository.RebeldeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RebeldeService {

    private static final Logger logger = LoggerFactory.getLogger(RebeldeService.class);

    private final RebeldeRepository rebeldeRepository;
    private final LocalizacaoRepository localizacaoRepository;
    private final ItemService itemService;

    public RebeldeService(RebeldeRepository rebeldeRepository, LocalizacaoRepository localizacaoRepository, ItemService itemService) {
        this.rebeldeRepository = rebeldeRepository;
        this.localizacaoRepository = localizacaoRepository;
        this.itemService = itemService;
    }

    public RebeldeDto buscarRebelde(Long oid){
        logger.debug("Buscando o rebelde de oid {}", oid);
        return rebeldeRepository.findById(oid)
                                .map(RebeldeTransformer::toDto)
                                .orElseThrow(() -> new NotFoundException(ErrorMessage.MSG_002.getDescription()));
    }

    public List<RebeldeDto> listarRebeldes(){
        logger.debug("Listando todos os rebeldes");
        return rebeldeRepository.findAll().stream()
                                          .map(RebeldeTransformer::toDto)
                                          .sorted(Comparator.comparing(RebeldeDto::getOid))
                                          .collect(Collectors.toList());
    }

    public RebeldeDto inserirRebelde(RebeldeRequest rebeldeRequest){
        logger.debug("Pesquisando os itens cadastrados na base de dados {}", rebeldeRequest);
        Map<String, Item> itemMap = itemService.listarItens().stream().collect(Collectors.toMap(Item::getNome, Function.identity()));
        logger.debug("Validando a existência dos items a serem trocados");
        if(!itemService.validarItensTrocados(rebeldeRequest.getInventario(), itemMap)){
            throw new NotFoundException(ErrorMessage.MSG_013.getDescription());
        }
        logger.debug("Inserindo rebelde na base de dados {}", rebeldeRequest);
        Rebelde rebelde = rebeldeRepository.save(RebeldeTransformer.toEntity(rebeldeRequest));
        logger.debug("Inserindo localização do rebelde na base de dados {}", rebeldeRequest.getLocalizacao());
        Localizacao localizacao = localizacaoRepository.save(LocalizacaoTransformer.toEntity(rebeldeRequest.getLocalizacao(), rebelde));
        logger.debug("Inserindo itens do rebelde na base de dados {}", rebeldeRequest);
        List<RebeldeItem> itensRebeldeList = itemService.salvarItens(rebeldeRequest.getInventario()
                                                                                   .stream()
                                                                                   .map(it -> ItemTransformer.toEntity(it.getQuantidade(), itemMap, it.getNome(), rebelde))
                                                                                   .collect(Collectors.toList()));
        return RebeldeTransformer.toDto(rebelde, localizacao, itensRebeldeList);
    }

    public RebeldeDto atualizarLocalizacao(AtualizarLocalizacaoRequest request){
        logger.debug("Atualizando localização do rebelde na base de dados {}", request);
        Rebelde rebelde = rebeldeRepository.findById(request.getOidRebelde())
                                                   .orElseThrow(() -> new NotFoundException(ErrorMessage.MSG_002.getDescription()));
        rebelde.getLocalizacao().setLongitude(request.getLocalizacao().getLongitude());
        rebelde.getLocalizacao().setLatitude(request.getLocalizacao().getLatitude());
        rebelde.getLocalizacao().setNome(request.getLocalizacao().getNome());
        return RebeldeTransformer.toDto(rebeldeRepository.save(rebelde));
    }

    public RebeldeDto relatarTraicao(Long oidRebelde){
        logger.debug("Relatando traição do rebelde {} na base de dados", oidRebelde);
        Rebelde rebelde = rebeldeRepository.findById(oidRebelde)
                                           .orElseThrow(() -> new NotFoundException(ErrorMessage.MSG_002.getDescription()));
        rebelde.setQtdTraicoes(rebelde.incrementarQtdTraicoes(ConstantsUtil.QTD_INCREMENTO));
        return RebeldeTransformer.toDto(rebeldeRepository.save(rebelde));
    }

}