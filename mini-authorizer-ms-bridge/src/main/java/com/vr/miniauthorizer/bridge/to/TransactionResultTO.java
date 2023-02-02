package com.vr.miniauthorizer.bridge.to;

import com.vr.miniauthorizer.bridge.constants.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class TransactionResultTO implements Serializable {

    private int code;

    private TransactionStatus status;
}
