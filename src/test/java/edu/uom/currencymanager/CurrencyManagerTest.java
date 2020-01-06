package edu.uom.currencymanager;

import edu.uom.currencymanager.currencies.Currency;
import edu.uom.currencymanager.currencies.CurrencyDatabase;
import edu.uom.currencymanager.currencies.ExchangeRate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CurrencyManagerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getExchangeRate(){
        try {
            CurrencyManager currencyManager = new CurrencyManager();
            Object object = currencyManager.getExchangeRate("EUR", "AUD");
            assertTrue(object instanceof ExchangeRate);
        }catch (Exception ex){
            System.err.println(ex);
        }
    }


    @Test
    void deleteCurrencyWithCode() {
        try {
            CurrencyManager currencyManager = new CurrencyManager();
            currencyManager.addCurrency("AKH", "Akhil", true);
            currencyManager.deleteCurrencyWithCode("AKH");
            CurrencyDatabase currencyDatabase = currencyManager.currencyDatabase;
            List<edu.uom.currencymanager.currencies.Currency> currencyList = currencyDatabase.getMajorCurrencies();
            boolean isFound = false;
            int count = currencyList.size();
            for (int i=0;i<count;i++){
                edu.uom.currencymanager.currencies.Currency currency = currencyList.get(i);
                if(currency.code=="AKH") {
                    isFound = true;
                    break;
                }
            }
            assertFalse(isFound);
        }catch (Exception ex){
            System.err.println(ex);
        }
    }

    @Test
    void addCurrency() {
        try {
            CurrencyManager currencyManager = new CurrencyManager();
            currencyManager.addCurrency("AKH", "Akhil", true);
            CurrencyDatabase currencyDatabase = currencyManager.currencyDatabase;
            List<edu.uom.currencymanager.currencies.Currency> currencyList = currencyDatabase.getMajorCurrencies();
            boolean isAdded = false;
            int count = currencyList.size();
            for (int i=0;i<count;i++) {
                edu.uom.currencymanager.currencies.Currency currency = currencyList.get(i);
                if (currency.code == "AKH") {
                    isAdded = true;
                    break;
                }
            }
            assertTrue(isAdded);
            currencyManager.deleteCurrencyWithCode("AKH");
        }catch (Exception ex){
            System.err.println(ex);
        }
    }

    @Test
    void getMajorCurrencyRates() {
        try {
            CurrencyManager currencyManager = new CurrencyManager();
            CurrencyDatabase currencyDatabase = currencyManager.currencyDatabase;
            List<Currency> majorCurrencies = currencyDatabase.getMajorCurrencies();
            int countMajorCurrencies = majorCurrencies.size();
            List<ExchangeRate> majorCurrencyRates = currencyManager.getMajorCurrencyRates();
            int countMajorCurrencyExchangeRates = majorCurrencyRates.size();
            assertEquals(countMajorCurrencies * (countMajorCurrencies - 1), countMajorCurrencyExchangeRates);
        }catch (Exception ex){
            System.err.println(ex);
        }
    }

}