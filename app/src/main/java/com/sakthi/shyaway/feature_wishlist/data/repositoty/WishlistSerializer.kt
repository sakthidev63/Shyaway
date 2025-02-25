package com.sakthi.shyaway.feature_wishlist.data.repositoty

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.sakthi.shyaway.feature_wishlist.domain.model.Wishlist
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object WishlistSerializer : Serializer<List<Wishlist>> {
    override val defaultValue: List<Wishlist> = emptyList()

    override suspend fun readFrom(input: InputStream): List<Wishlist> {
        return try {
            Json.decodeFromString(
                deserializer = ListSerializer(Wishlist.serializer()),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            throw CorruptionException("Cannot read wishlist data", e)
        }
    }

    override suspend fun writeTo(t: List<Wishlist>, output: OutputStream) {
        output.write(
            Json.encodeToString(ListSerializer(Wishlist.serializer()), t).encodeToByteArray()
        )
    }
}
