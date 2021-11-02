package com.resistence.network.application.service;

import com.resistence.network.application.dto.DadosRelatorioDto;
import com.resistence.network.application.util.ConstantsUtil;
import com.resistence.network.domain.Rebelde;
import com.resistence.network.infrastructure.repository.RebeldeRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Predicate;

@Service
public class RelatorioService {

    private final RebeldeRepository rebeldeRepository;

    public RelatorioService(RebeldeRepository rebeldeRepository) {
        this.rebeldeRepository = rebeldeRepository;
    }

    public DadosRelatorioDto buscarDadosRelatorio(){
        List<Rebelde> rebeldeList = rebeldeRepository.findAll();
        return new DadosRelatorioDto(getPercentualTraidores(rebeldeList), getPercentualRebeldes(rebeldeList), getPontosPerdidosPorTraidores(rebeldeList));
    }

    private BigDecimal getPercentualTraidores(List<Rebelde> rebeldeList){
        return BigDecimal.valueOf(getQuantidadeTotalDeTraidores(rebeldeList))
                         .divide(BigDecimal.valueOf(getQuantidadeTotalDeRebeldes(rebeldeList)),
                                 new MathContext(ConstantsUtil.CASAS_DECIMAIS, RoundingMode.HALF_EVEN));
    }

    private BigDecimal getPercentualRebeldes(List<Rebelde> rebeldeList){
        return BigDecimal.valueOf(getQuantidadeTotalDeRebeldesNaoTraidores(rebeldeList))
                         .divide(BigDecimal.valueOf(getQuantidadeTotalDeRebeldes(rebeldeList)),
                                 new MathContext(ConstantsUtil.CASAS_DECIMAIS, RoundingMode.HALF_EVEN));
    }

    private int getPontosPerdidosPorTraidores(List<Rebelde> rebeldeList){
        return rebeldeList.stream()
                          .filter(Predicate.not(Rebelde::isTraidor))
                          .map(it -> it.getRebeldeItemList().stream()
                                                            .map(el -> el.getQuantidade() * el.getOidItem().getPontuacao())
                                                            .reduce(ConstantsUtil.VALOR_REDUCE, Integer::sum))
                          .reduce(ConstantsUtil.VALOR_REDUCE, Integer::sum);
    }

    private int getQuantidadeTotalDeRebeldes(List<Rebelde> rebeldeList){
        return rebeldeList.size();
    }

    private int getQuantidadeTotalDeRebeldesNaoTraidores(List<Rebelde> rebeldeList){
        return (int) rebeldeList.stream()
                                .filter(Predicate.not(Rebelde::isTraidor))
                                .count();
    }

    private int getQuantidadeTotalDeTraidores(List<Rebelde> rebeldeList){
        return (int) rebeldeList.stream()
                                .filter(Rebelde::isTraidor)
                                .count();
    }

}