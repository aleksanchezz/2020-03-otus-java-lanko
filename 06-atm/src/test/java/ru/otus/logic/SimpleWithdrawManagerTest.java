package ru.otus.logic;

import org.junit.jupiter.api.Test;
import ru.otus.exception.InvalidAmountRequested;
import ru.otus.exception.UnableToWithdrawMoney;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimpleWithdrawManagerTest {
    @Test
    public void testCashPrescriptionNumberOne() throws Exception {
        Map<Integer, AtomicInteger> givenBills = new HashMap<>();
        givenBills.put(5_000, new AtomicInteger(3));
        givenBills.put(1_000, new AtomicInteger(2));
        givenBills.put(100, new AtomicInteger(5));

        SimpleWithdrawManager cashLogicManager = new SimpleWithdrawManager()
                .setDenominationAndCount(givenBills);
        assertThat(cashLogicManager.getCurrentMaximalDenomination()).isEqualTo(5_000);
        assertThat(cashLogicManager.getCurrentMinimalDenomination()).isEqualTo(100);

        List<Integer> result = cashLogicManager.getPrescription(1_300);
        assertThat(result).containsExactly(1_000, 100, 100, 100);
    }

    @Test
    public void testCashPrescriptionNumberTwo() throws Exception {
        Map<Integer, AtomicInteger> givenBills = new HashMap<>();
        givenBills.put(2_000, new AtomicInteger(3));
        givenBills.put(1_000, new AtomicInteger(2));
        givenBills.put(100, new AtomicInteger(5));

        SimpleWithdrawManager cashLogicManager = new SimpleWithdrawManager()
                .setDenominationAndCount(givenBills);;
        assertThat(cashLogicManager.getCurrentMaximalDenomination()).isEqualTo(2_000);
        assertThat(cashLogicManager.getCurrentMinimalDenomination()).isEqualTo(100);

        List<Integer> result = cashLogicManager.getPrescription(3_200);
        assertThat(result).containsExactly(2_000, 1_000, 100, 100);
    }

    @Test
    public void testCashPrescriptionNumberThree() throws Exception {
        Map<Integer, AtomicInteger> givenBills = new HashMap<>();
        givenBills.put(2_000, new AtomicInteger(3));
        givenBills.put(1_000, new AtomicInteger(2));
        givenBills.put(100, new AtomicInteger(5));

        SimpleWithdrawManager cashLogicManager = new SimpleWithdrawManager()
                .setDenominationAndCount(givenBills);;
        assertThat(cashLogicManager.getCurrentMaximalDenomination()).isEqualTo(2_000);
        assertThat(cashLogicManager.getCurrentMinimalDenomination()).isEqualTo(100);

        List<Integer> result = cashLogicManager.getPrescription(3_200);
        assertThat(result).containsExactly(2_000, 1_000, 100, 100);
    }

    @Test
    public void testCashPrescriptionNumberFour() throws Exception {
        Map<Integer, AtomicInteger> givenBills = new HashMap<>();
        givenBills.put(5_000, new AtomicInteger(4));
        givenBills.put(2_000, new AtomicInteger(3));
        givenBills.put(1_000, new AtomicInteger(2));
        givenBills.put(500, new AtomicInteger(2));
        givenBills.put(100, new AtomicInteger(5));

        SimpleWithdrawManager cashLogicManager = new SimpleWithdrawManager()
                .setDenominationAndCount(givenBills);;
        assertThat(cashLogicManager.getCurrentMaximalDenomination()).isEqualTo(5_000);
        assertThat(cashLogicManager.getCurrentMinimalDenomination()).isEqualTo(100);

        List<Integer> result = cashLogicManager.getPrescription(8_800);
        assertThat(result).containsExactly(5_000, 2_000, 1_000, 500, 100, 100, 100);
    }

    @Test
    public void testCashPrescriptionNumberFive() throws Exception {
        Map<Integer, AtomicInteger> givenBills = new HashMap<>();
        givenBills.put(5_000, new AtomicInteger(4));
        givenBills.put(1_000, new AtomicInteger(2));
        givenBills.put(500, new AtomicInteger(2));
        givenBills.put(50, new AtomicInteger(8));

        SimpleWithdrawManager cashLogicManager = new SimpleWithdrawManager()
                .setDenominationAndCount(givenBills);
        assertThat(cashLogicManager.getCurrentMaximalDenomination()).isEqualTo(5_000);
        assertThat(cashLogicManager.getCurrentMinimalDenomination()).isEqualTo(50);

        List<Integer> result = cashLogicManager.getPrescription(6_700);
        assertThat(result).containsExactly(5_000, 1_000, 500, 50, 50, 50, 50);
    }

    @Test
    public void testCashPrescriptionWithWrongAmount() {
        Map<Integer, AtomicInteger> givenBills = new HashMap<>();
        givenBills.put(2_000, new AtomicInteger(2));
        givenBills.put(500, new AtomicInteger(2));
        givenBills.put(50, new AtomicInteger(8));

        SimpleWithdrawManager cashLogicManager = new SimpleWithdrawManager()
                .setDenominationAndCount(givenBills);;
        assertThat(cashLogicManager.getCurrentMaximalDenomination()).isEqualTo(2_000);
        assertThat(cashLogicManager.getCurrentMinimalDenomination()).isEqualTo(50);

        assertThrows(
                InvalidAmountRequested.class,
                () -> cashLogicManager.getPrescription(6_333)
        );
    }

    @Test
    public void testCashPrescriptionWithNotEnoughMoneyInATM() {
        Map<Integer, AtomicInteger> givenBills = new HashMap<>();
        givenBills.put(2_000, new AtomicInteger(2));
        givenBills.put(500, new AtomicInteger(2));
        givenBills.put(100, new AtomicInteger(1));

        SimpleWithdrawManager cashLogicManager = new SimpleWithdrawManager()
                .setDenominationAndCount(givenBills);
        assertThat(cashLogicManager.getCurrentMaximalDenomination()).isEqualTo(2_000);
        assertThat(cashLogicManager.getCurrentMinimalDenomination()).isEqualTo(100);

        assertThrows(
                UnableToWithdrawMoney.class,
                () -> cashLogicManager.getPrescription(6_300)
        );
    }
}