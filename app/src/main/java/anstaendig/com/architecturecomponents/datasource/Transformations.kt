package anstaendig.com.architecturecomponents.datasource

import anstaendig.com.architecturecomponents.entities.Person

fun PersonData.toPerson(): Person = Person(this.name)
