package com.anstaendig.architecturecomponents.datasource

import com.anstaendig.architecturecomponents.entities.Person

fun PersonData.toPerson(): Person = Person(name)
