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

package fi.evident.dalesbred.dialects;

import org.jetbrains.annotations.NotNull;
import org.postgresql.util.PGobject;

import java.sql.SQLException;

import static fi.evident.dalesbred.utils.StringUtils.upperCamelToLowerUnderscore;

/**
 * Support for PostgreSQL.
 */
public class PostgreSQLDialect extends Dialect {

    @NotNull
    @Override
    protected Object createDatabaseEnum(@NotNull Enum<?> value) {
        try {
            PGobject object = new PGobject();
            object.setType(upperCamelToLowerUnderscore(value.getClass().getSimpleName()));
            object.setValue(value.name());
            return object;
        } catch (SQLException e) {
            throw convertException(e);
        }
    }
}
