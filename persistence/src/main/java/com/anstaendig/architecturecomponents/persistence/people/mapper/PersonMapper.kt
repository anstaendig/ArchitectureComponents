package com.anstaendig.architecturecomponents.persistence.people.mapper

import com.anstaendig.architecturecomponents.data.people.model.PersonDataModel
import com.anstaendig.architecturecomponents.persistence.Mapper
import com.anstaendig.architecturecomponents.persistence.people.model.PersonPersistenceModel

class PersonMapper : Mapper<PersonPersistenceModel, PersonDataModel> {

  override fun mapToDataModel(persistenceModel: PersonPersistenceModel): PersonDataModel {
    return PersonDataModel(
        persistenceModel.id,
        persistenceModel.name,
        persistenceModel.birthYear,
        persistenceModel.eyeColor,
        persistenceModel.gender,
        persistenceModel.hairColor,
        persistenceModel.height,
        persistenceModel.mass,
        persistenceModel.skinColor,
        persistenceModel.homeworld,
        persistenceModel.films,
        persistenceModel.species,
        persistenceModel.starships,
        persistenceModel.vehicles,
        persistenceModel.url,
        persistenceModel.created,
        persistenceModel.edited
    )
  }

  override fun mapToPersistenceModel(dataModel: PersonDataModel): PersonPersistenceModel {
    return PersonPersistenceModel(
        dataModel.id,
        dataModel.name,
        dataModel.birthYear,
        dataModel.eyeColor,
        dataModel.gender,
        dataModel.hairColor,
        dataModel.height,
        dataModel.mass,
        dataModel.skinColor,
        dataModel.homeworld,
        dataModel.films,
        dataModel.species,
        dataModel.starships,
        dataModel.vehicles,
        dataModel.url,
        dataModel.created,
        dataModel.edited
    )
  }
}
