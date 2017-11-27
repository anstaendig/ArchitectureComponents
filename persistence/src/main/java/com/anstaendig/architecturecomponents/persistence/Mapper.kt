package com.anstaendig.architecturecomponents.persistence

interface Mapper<P, D> {

  fun mapToDataModel(persistenceModel: P): D

  fun mapToPersistenceModel(dataModel: D): P
}
