package com.vr.miniauthorizer.bridge.to;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("Card")
public class CardTO implements Serializable {

    private String number;

    private String password;
}
