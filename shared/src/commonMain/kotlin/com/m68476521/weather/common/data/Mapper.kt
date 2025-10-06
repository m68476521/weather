package com.m68476521.weather.common.data

interface Mapper<Domain, Model> {
    fun mapToDomainOrNull(model: Model?): Domain?
    fun mapFromDomain(domain: Domain): Model
}
