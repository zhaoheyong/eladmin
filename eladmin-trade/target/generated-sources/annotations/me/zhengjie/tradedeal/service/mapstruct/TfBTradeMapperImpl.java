package me.zhengjie.tradedeal.service.mapstruct;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import me.zhengjie.tradedeal.domain.TfBTrade;
import me.zhengjie.tradedeal.service.dto.TfBTradeDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-03T17:25:09+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_91 (Oracle Corporation)"
)
@Component
public class TfBTradeMapperImpl implements TfBTradeMapper {

    @Override
    public TfBTrade toEntity(TfBTradeDto dto) {
        if ( dto == null ) {
            return null;
        }

        TfBTrade tfBTrade = new TfBTrade();

        tfBTrade.setExecMonth( dto.getExecMonth() );
        tfBTrade.setExecDay( dto.getExecDay() );
        tfBTrade.setStatus( dto.getStatus() );
        tfBTrade.setExecDesc( dto.getExecDesc() );
        tfBTrade.setId( dto.getId() );

        return tfBTrade;
    }

    @Override
    public TfBTradeDto toDto(TfBTrade entity) {
        if ( entity == null ) {
            return null;
        }

        TfBTradeDto tfBTradeDto = new TfBTradeDto();

        tfBTradeDto.setExecMonth( entity.getExecMonth() );
        tfBTradeDto.setExecDay( entity.getExecDay() );
        tfBTradeDto.setStatus( entity.getStatus() );
        tfBTradeDto.setExecDesc( entity.getExecDesc() );
        tfBTradeDto.setId( entity.getId() );

        return tfBTradeDto;
    }

    @Override
    public List<TfBTrade> toEntity(List<TfBTradeDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<TfBTrade> list = new ArrayList<TfBTrade>( dtoList.size() );
        for ( TfBTradeDto tfBTradeDto : dtoList ) {
            list.add( toEntity( tfBTradeDto ) );
        }

        return list;
    }

    @Override
    public List<TfBTradeDto> toDto(List<TfBTrade> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<TfBTradeDto> list = new ArrayList<TfBTradeDto>( entityList.size() );
        for ( TfBTrade tfBTrade : entityList ) {
            list.add( toDto( tfBTrade ) );
        }

        return list;
    }
}
