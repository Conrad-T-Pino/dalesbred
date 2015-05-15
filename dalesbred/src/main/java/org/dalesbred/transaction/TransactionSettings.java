/*
 * Copyright (c) 2015 Evident Solutions Oy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.dalesbred.transaction;

import org.dalesbred.Database;
import org.jetbrains.annotations.NotNull;

import static java.util.Objects.requireNonNull;

/**
 * Contains all the settings that can be configured for individual-transactions.
 *
 * @see Database#withTransaction(TransactionSettings, TransactionCallback)
 */
public final class TransactionSettings {

    @NotNull
    private Propagation propagation = Propagation.REQUIRED;

    @NotNull
    private Isolation isolation = Isolation.DEFAULT;

    @NotNull
    public Propagation getPropagation() {
        return propagation;
    }

    /**
     * Sets the default transaction propagation to use.
     */
    public void setPropagation(@NotNull Propagation propagation) {
        this.propagation = requireNonNull(propagation);
    }

    @NotNull
    public Isolation getIsolation() {
        return isolation;
    }

    /**
     * Set the default isolation level to use.
     */
    public void setIsolation(@NotNull Isolation isolation) {
        this.isolation = isolation;
    }

    @NotNull
    @Override
    public String toString() {
        return "[propagation=" + propagation + ", isolation=" + isolation + ']';
    }
}
