package am.onlinesite.onlinesitecommon.service;

import am.onlinesite.onlinesitecommon.model.Transaction;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import java.util.List;

public interface TransactionService {

    Charge charge(Transaction transaction) throws StripeException;

    List<Transaction> getTransactions();
}
