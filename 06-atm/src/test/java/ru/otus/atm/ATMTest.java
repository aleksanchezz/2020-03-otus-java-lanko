package ru.otus.atm;

import org.junit.jupiter.api.Test;
import ru.otus.logic.SimpleWithdrawManager;
import ru.otus.money.Bill;
import ru.otus.money.Bills;
import ru.otus.operation.*;
import ru.otus.operation.model.OperationResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ATMTest {
    @Test
    public void testChainOfOperations() {
        ATM atm = new ATM();
        atm.setWithdrawAlgorithm(new SimpleWithdrawManager());

        List<Bill> billsToDeposit = new ArrayList<>(
                Arrays.asList(
                        Bills.THOUSAND_RUBLES,
                        Bills.FIVE_HUNDREDS_RUBLES,
                        Bills.HUNDRED_RUBLES,
                        Bills.HUNDRED_RUBLES,
                        Bills.HUNDRED_RUBLES,
                        Bills.HUNDRED_RUBLES
                )
        );
        ATMOperation balanceOperation = new Balance();

        OperationResult depositOperationResult = atm.operation(new Deposit(billsToDeposit));
        assertThat(depositOperationResult.getStatus()).isEqualTo(Status.SUCCESS);

        OperationResult balanceOperationResult = atm.operation(balanceOperation);
        assertThat(depositOperationResult.getBalance())
                .isEqualTo(balanceOperationResult.getBalance())
                .isEqualTo(1_900);

        OperationResult withdrawOperationResult = atm.operation(new Withdraw(800));
        assertThat(withdrawOperationResult.getStatus()).isEqualTo(Status.SUCCESS);

        assertThat(withdrawOperationResult.getBills()).containsExactly(
                Bills.FIVE_HUNDREDS_RUBLES,
                Bills.HUNDRED_RUBLES,
                Bills.HUNDRED_RUBLES,
                Bills.HUNDRED_RUBLES
        );

        balanceOperationResult = atm.operation(balanceOperation);
        assertThat(withdrawOperationResult.getBalance())
                .isEqualTo(balanceOperationResult.getBalance())
                .isEqualTo(1_100);

        withdrawOperationResult = atm.operation(new Withdraw(2_100));
        assertThat(withdrawOperationResult.getStatus()).isEqualTo(Status.FAILURE);
        assertThat(withdrawOperationResult.getMessage()).contains("ATM is about to get empty");
        assertThat(withdrawOperationResult.getBalance()).isEqualTo(1_100);

        withdrawOperationResult = atm.operation(new Withdraw(333));
        assertThat(withdrawOperationResult.getStatus()).isEqualTo(Status.FAILURE);
        assertThat(withdrawOperationResult.getMessage()).contains("Requested amount is wrong");
        assertThat(withdrawOperationResult.getBalance()).isEqualTo(1_100);
    }
}
