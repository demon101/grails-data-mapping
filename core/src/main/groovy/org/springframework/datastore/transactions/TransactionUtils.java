/* Copyright (C) 2010 SpringSource
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.datastore.transactions;

import org.springframework.datastore.core.Datastore;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Utility methods for Transactions
 *
 * @author Graeme Rocher
 * @since 1.0
 */
public class TransactionUtils {

    public static boolean isTransactionPresent(Datastore datastore) {
        return getTransaction(datastore) != null;
    }

    public static Transaction currentTransaction(Datastore datastore) {
        final Transaction transaction = getTransaction(datastore);
        if(transaction == null) {
            throw new NoTransactionException("No transaction started.");
        }
        return transaction;
    }

    public static Transaction getTransaction(Datastore datastore) {
        final SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.getResource(datastore);

        if(sessionHolder != null) {
            return sessionHolder.getTransaction();
        }
        return null;
    }


}
