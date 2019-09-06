package com.github.bamling

import com.fasterxml.jackson.annotation.JsonIgnore
import org.bson.types.ObjectId

data class Fruit(
        @JsonIgnore val id: ObjectId? = null,
        val name: String = "",
        val description: String = ""
)