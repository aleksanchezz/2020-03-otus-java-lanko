package ru.otus.department;

import org.junit.jupiter.api.Test;
import ru.otus.atm.ATM;
import ru.otus.atm.ATMImpl;
import ru.otus.atm.logic.SimpleWithdrawManager;

import static org.assertj.core.api.Assertions.assertThat;

class DepartmentTest {

    @Test
    public void testCollectionPipeLine() throws Exception {
        ATM atm1 = new ATMImpl();
        atm1.setWithdrawAlgorithm(new SimpleWithdrawManager());

        ATM atm2 = new ATMImpl();
        atm1.setWithdrawAlgorithm(new SimpleWithdrawManager());

        ATM atm3 = new ATMImpl();
        atm1.setWithdrawAlgorithm(new SimpleWithdrawManager());

        Department department = new Department();
        department.addATM(atm1);
        department.addATM(atm2);
        department.addATM(atm3);

        department.loadZeroState();

        assertThat(department.checkBalance()).isEqualTo(InitialState.calculateStateBalance(InitialState.zeroBillState) * 3);
    }

}