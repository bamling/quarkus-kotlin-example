package com.github.bamling

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId

data class Fruit(@JsonIgnore val id: ObjectId? = null, val name: String = "", val description: String = "") {

    @JsonCreator
    constructor(@JsonProperty("name") name: String = "",
                @JsonProperty("description") description: String = "") : this(null, name, description)
}