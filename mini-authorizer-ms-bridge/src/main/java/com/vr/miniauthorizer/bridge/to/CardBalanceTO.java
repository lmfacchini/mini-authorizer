package com.vr.miniauthorizer.bridge.to;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel("CardBalance")
@AllArgsConstructor
public class CardBalanceTO implements Serializable {

    private BigDecimal value;

}
