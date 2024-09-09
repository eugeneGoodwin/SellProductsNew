package com.vortex.soft.sellproductsnew.data.mapper.common

interface CommonMapper<D, J, S> : JsonMapper<D, J>, SqlMapper<D, S>, DataMapper<J, S>

interface LayerMapper<D, J, S> : JsonMapper<D, J>, SqlMapper<D, S>

interface SqlMapper<D, S> {
    fun mapSqlToDomain(type: S): D

    fun mapDomainToSql(type: D): S
}

interface JsonMapper<D, J> {
    fun mapJsonToDomain(type: J): D

    fun mapDomainToJson(type: D): J
}

interface DataMapper<J, S> {
    fun mapSqlToJson(type: S): J

    fun mapJsonToSql(type: J): S
}

interface ComplexSqlMapper<D, S, S1> {
    fun mapSqlToDomain(type: S, type1: List<S1>): D

    fun mapDomainToSql(type: D): Pair<S, List<S1>>
}