/*
 * Copyright (c) 2012 Evident Solutions Oy
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

package fi.evident.dalesbred.instantiation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static fi.evident.dalesbred.utils.Require.requireNonNull;

/**
 * A set of {@link Coercion}s.
 */
public final class Coercions {

    private final List<Coercion<?,?>> loadCoercions = new ArrayList<Coercion<?,?>>();
    private final List<Coercion<?,?>> storeCoercions = new ArrayList<Coercion<?,?>>();

    @Nullable
    public <S,T> Coercion<S,T> findCoercionFromDbValue(@NotNull Class<S> source, @NotNull Class<T> target) {
        for (Coercion<?,?> coercion : loadCoercions)
            if (coercion.canCoerce(source, target))
                return coercion.cast(source, target);

        return null;
    }

    public <S,T> void registerLoadConversion(@NotNull Coercion<S, T> coercion) {
        loadCoercions.add(requireNonNull(coercion));
    }

    public <S,T> void registerStoreConversion(@NotNull Coercion<S, T> coercion) {
        storeCoercions.add(requireNonNull(coercion));
    }

    @Nullable
    public <T> Coercion<T,Object> findCoercionToDb(@NotNull Class<T> type) {
        for (Coercion<?,?> coercion : storeCoercions)
            if (coercion.canCoerce(type, Object.class))
                return coercion.cast(type, Object.class);

        return null;
    }
}
