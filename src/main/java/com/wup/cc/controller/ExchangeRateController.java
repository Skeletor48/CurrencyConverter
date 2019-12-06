package com.wup.cc.controller;

import com.wup.cc.model.ExchangeRate;
import com.wup.cc.service.AdminService;
import com.wup.cc.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ExchangeRateController {

    @Autowired
    ExchangeRateService exchangeRateService;

    //  Endpoint to get the rates against a base foreign exchange
    @GetMapping("/rate/{base}/{type}")
    public String show(@PathVariable String base, @PathVariable int type) {
        return exchangeRateService.getExchangeRateRecord(base, type).toString();
    }

    //  Endpoint to convert from the base currency to a target currency with a specified amount
    @PostMapping("/rate/convert")
    public String convert(@RequestBody Map<String, String> body) {
        return exchangeRateService.getFormattedResponse(body);
    }

    //  Cheating endpoint using the javax.money package just for fun
    @PostMapping("/cheater")
    public float apiConvert(@RequestBody Map<String, String> body) {
        return exchangeRateService.cheatConvert(body);
    }

    //  A few administrative features
    @Autowired
    AdminService adminService;

    @PostMapping("/admin/create")
    public String create() {
        return adminService.createTable();
    }

    @PostMapping("/admin/populate")
    public List<ExchangeRate> populate() {
        return adminService.populateTable();
    }

    @DeleteMapping("/admin/deleteAll")
    public boolean deleteAll() {
        adminService.deleteAll();
        return true;
    }

    @GetMapping("/admin/checkAll")
    public List<ExchangeRate> listAll() {
        return adminService.listAll();
    }
}
