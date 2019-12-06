package com.wup.cc.service;

import com.wup.cc.model.ExchangeRate;
import com.wup.cc.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    ExchangeRateRepository exchangeRateRepository;

    public void deleteAll() {
        exchangeRateRepository.deleteAll();
    }

    public List<ExchangeRate> listAll() {
        return exchangeRateRepository.findAll();
    }

    public SingleConnectionDataSource setUpDb() {
        SingleConnectionDataSource ds = new SingleConnectionDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/wupapi?useSSL=false&allowPublicKeyRetrieval=true");
        ds.setUsername("root");
        ds.setPassword("");
        return ds;
    }

    public String createTable() {
        try {
            SingleConnectionDataSource ds = setUpDb();
            JdbcTemplate jt = new JdbcTemplate(ds);
            jt.execute("CREATE TABLE exchange_rate (\n" +
                    "id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,\n" +
                    "base VARCHAR(500) NOT NULL,\n" +
                    "type INT(6) NOT NULL,\n" +
                    "date DATE NOT NULL,\n" +
                    "chf DECIMAL(10,5) NOT NULL,\n" +
                    "usd DECIMAL(10,5) NOT NULL,\n" +
                    "eur DECIMAL(10,5) NOT NULL,\n" +
                    "cad DECIMAL(10,5) NOT NULL,\n" +
                    "gbp DECIMAL(10,5) NOT NULL\n" +
                    ")");
            ds.destroy();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return "Db created";
    }

    public List<ExchangeRate> populateTable() {
        if (isAlreadyPopulate()) {
            return null;
        }
            try {
                SingleConnectionDataSource ds = setUpDb();
                JdbcTemplate jt = new JdbcTemplate(ds);

                jt.execute("INSERT INTO\n" +
                        "exchange_rate (base, type, date, chf, usd, eur, cad, gbp )\n" +
                        "VALUES\n" +
                        "('eur', 1, '19-12-05', 1.1798,1.1094,1,1.461,0.8447),\n" +
                        "('chf', 1, '19-12-05', 1,1.01185,0.91207,1.33254,0.77043),\n" +
                        "('usd', 1, '19-12-05', 0.98828,1,0.90138,1.3169280,0.761402),\n" +
                        "('cad', 1, '19-12-05',0.75044,0.75934,0.684462,1,0.57816),\n" +
                        "('gbp', 1, '19-12-05', 1.29797,1.31336,1.18385,1.72960,1),\n" +
                        "('eur', 2, '19-12-05', 1.1498,1.0094,1,1.361,0.7447),\n" +
                        "('chf', 2, '19-12-05', 1,1.001,0.87207,1.22254,0.6043),\n" +
                        "('usd', 2, '19-12-05', 0.78828,1,0.85138,1.22269,0.56602),\n" +
                        "('cad', 2, '19-12-05',0.67044,0.65934,0.34562,1,0.44316),\n" +
                        "('gbp', 2, '19-12-05', 1.19797,1.11336,1.01385,1.55960,1)");
                ds.destroy();
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        return listAll();

    }

    private boolean isAlreadyPopulate() {
        return (exchangeRateRepository.findByBaseAndType("eur", 1) instanceof ExchangeRate);
    }
}
