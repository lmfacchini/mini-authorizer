package com.vr.miniauthorizer.bridge.controller;


import com.vr.miniauthorizer.bridge.constants.TransactionStatus;
import com.vr.miniauthorizer.bridge.to.CardBalanceTO;
import com.vr.miniauthorizer.bridge.to.CardTO;
import com.vr.miniauthorizer.bridge.to.TransactionTO;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api
public interface AuthorizerController {



    @ApiOperation("Create new card.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful creation."),
            @ApiResponse(code = 422, message = "Card already exists."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    @PostMapping("/card")
    ResponseEntity<CardTO> card(@RequestBody CardTO to);

    @ApiOperation("Get card balance.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Obtaining successfully."),
            @ApiResponse(code = 404, message = "Card does not exist."),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    @GetMapping("/card/{number}")
    ResponseEntity<CardBalanceTO> balance(
            @ApiParam("Card number.")
            @PathVariable("number") String number
    );

    @ApiOperation("Perform a Transaction.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Transaction performed successfully."),
            @ApiResponse(code = 422, message = "INSUFFICIENT_FUNDS|INVALID_PASSWORD|NONEXISTENT_CARD"),
            @ApiResponse(code = 500, message = "Internal error.")
    })
    @PostMapping("/transaction")
    ResponseEntity<TransactionStatus> transaction(@RequestBody TransactionTO transaction);


}
