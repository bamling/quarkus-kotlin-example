package com.github.bamling

import com.fasterxml.jackson.annotation.JsonIgnore
import org.bson.types.ObjectId

data class Fruit(@JsonIgnore var id: ObjectId? = null, var name: String = "", var description: String = "")