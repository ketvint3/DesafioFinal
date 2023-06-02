package com.Desafio.Final.Diamond.repositories.criteria.param;

import com.Desafio.Final.Diamond.models.enu.ViagemEnum;
import lombok.Data;

@Data
public class ViagemFilterParam {
    private ViagemEnum statusViagem;
}
