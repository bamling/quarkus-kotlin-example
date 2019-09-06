package com.github.bamling

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import org.bson.Document
import org.bson.types.ObjectId
import javax.annotation.PostConstruct
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class FruitService {

    @Inject
    lateinit var mongoClient: MongoClient

    private lateinit var collection: MongoCollection<Document>

    fun add(fruit: Fruit): Fruit {
        val id = ObjectId()

        collection.insertOne(Document(mapOf(
                "_id" to id,
                "name" to fruit.name,
                "description" to fruit.description
        )))

        return fruit.copy(id = id, name = fruit.name, description = fruit.description)
    }

    fun list(): List<Fruit> = collection.find().map(fun(document): Fruit {
        return Fruit(
                document.getObjectId("_id"),
                document.getString("name"),
                document.getString("description")
        )
    }).toList()

    fun find(id: String): Fruit? = collection.find(eq("_id", ObjectId(id))).first()?.let {
        Fruit(
                it.getObjectId("_id"),
                it.getString("name"),
                it.getString("description")
        )
    }

    @PostConstruct
    fun initialiseCollection() {
        collection = mongoClient.getDatabase("fruit").getCollection("fruit")
    }
}