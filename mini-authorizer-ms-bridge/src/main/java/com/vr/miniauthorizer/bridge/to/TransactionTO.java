package com.vr.miniauthorizer.bridge.to;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("Transaction")
public class TransactionTO extends CardTO{

    private BigDecimal value;
}
