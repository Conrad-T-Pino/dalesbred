package fi.evident.dalesbred.results;

import fi.evident.dalesbred.DatabaseException;
import fi.evident.dalesbred.instantiation.Coercion;
import fi.evident.dalesbred.instantiation.InstantiatorRegistry;
import fi.evident.dalesbred.instantiation.NamedTypeList;
import fi.evident.dalesbred.utils.ResultSetUtils;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import static fi.evident.dalesbred.utils.Require.requireNonNull;

/**
 * ResultSetProcessor that expects results with two columns and creates map from them.
 */
public final class MapResultSetProcessor<K,V> implements ResultSetProcessor<Map<K,V>> {

    private final Class<K> keyType;
    private final Class<V> valueType;
    private final InstantiatorRegistry instantiatorRegistry;

    public MapResultSetProcessor(@NotNull Class<K> keyType,
                                 @NotNull Class<V> valueType,
                                 @NotNull InstantiatorRegistry instantiatorRegistry) {
        this.keyType = requireNonNull(keyType);
        this.valueType = requireNonNull(valueType);
        this.instantiatorRegistry = requireNonNull(instantiatorRegistry);
    }

    @Override
    public Map<K, V> process(@NotNull ResultSet resultSet) throws SQLException {
        Map<K,V> result = new LinkedHashMap<K,V>();

        NamedTypeList types = ResultSetUtils.getTypes(resultSet.getMetaData());
        if (types.size() != 2)
            throw new DatabaseException("Expected ResultSet with 2 columns, but got " + types.size() + " columns.");

        @SuppressWarnings("unchecked")
        Class<Object> keySource = (Class) types.getType(0);
        @SuppressWarnings("unchecked")
        Class<Object> valueSource = (Class) types.getType(1);

        Coercion<? super Object, ? extends K> keyCoercion = instantiatorRegistry.getCoercionFromDbValue(keySource, keyType);
        Coercion<? super Object, ? extends V> valueCoercion = instantiatorRegistry.getCoercionFromDbValue(valueSource, valueType);

        while (resultSet.next()) {
            K key = keyCoercion.coerce(resultSet.getObject(1));
            V value = valueCoercion.coerce(resultSet.getObject(2));
            result.put(key, value);
        }

        return result;
    }
}
