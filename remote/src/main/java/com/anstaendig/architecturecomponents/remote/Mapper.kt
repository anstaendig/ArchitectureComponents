package com.anstaendig.architecturecomponents.remote

interface Mapper<in R, out D> {

  fun mapToDataModel(remoteModel: R): D
}
