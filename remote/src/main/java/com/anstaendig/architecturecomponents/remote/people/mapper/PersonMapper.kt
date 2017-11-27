package com.anstaendig.architecturecomponents.remote.people.mapper

import com.anstaendig.architecturecomponents.data.people.model.PersonDataModel
import com.anstaendig.architecturecomponents.remote.Mapper
import com.anstaendig.architecturecomponents.remote.people.model.PersonRemoteModel

class PersonMapper : Mapper<PersonRemoteModel, PersonDataModel> {

  override fun mapToDataModel(remoteModel: PersonRemoteModel): PersonDataModel {
    return PersonDataModel(
        remoteModel.id,
        remoteModel.name,
        remoteModel.birthYear,
        remoteModel.eyeColor,
        remoteModel.gender,
        remoteModel.hairColor,
        remoteModel.height,
        remoteModel.mass,
        remoteModel.skinColor,
        remoteModel.homeworld,
        remoteModel.films,
        remoteModel.species,
        remoteModel.starships,
        remoteModel.vehicles,
        remoteModel.url,
        remoteModel.created,
        remoteModel.edited
    )
  }
}
