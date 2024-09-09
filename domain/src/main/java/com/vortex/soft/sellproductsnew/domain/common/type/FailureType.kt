package com.vortex.soft.sellproductsnew.domain.common.type

/**
 * Base Class for handling errors/failures/exceptions.
 */

sealed class FailureType {
    object NetworkConnectionError : FailureType()
    object ServerError : FailureType()
    object AuthError : FailureType()
    object TokenError : FailureType()

    object EmailAlreadyExistError : FailureType()

    object ContactNotFoundError : FailureType()

    object NoSavedAccountsError : FailureType()

    object FilePickError : FailureType()

    object GenericError : FailureType()
    object UnknownError : FailureType()
    object DatabaseError : FailureType()
    object LocalDataError : FailureType()
    object ResponseError : FailureType()
    object ResponseStatusError : FailureType()
    object RequestError : FailureType()
    object PersistenceError : FailureType()
    object CartEmptyError : FailureType()
    object CurrentUserError : FailureType()

    object UnauthorizedError : FailureType()

    object PlayStoreError : FailureType()
    object ContentProviderError : FailureType()
    object PermissionError : FailureType()
    object DownloadFileError : FailureType()
    object FileNotCreatedError : FailureType()

    object TooManyRequestError : FailureType() {
        var failureData = FailureData()
        fun init(data: FailureData) { failureData = data }
    }

    object ResponseDetailsError : FailureType() {
        var failureData = FailureData()
        fun init(data: FailureData) { failureData = data }
    }

    object UnprocessibleEntityError : FailureType() {
        var mapError = mapOf<String, String>()
        var complexMapError = mapOf<String, Map<String, String> >()
        fun initMapError(map: Map<String, String>, complexMap: Map<String, Map<String, String>> = mapOf()) { mapError = map; complexMapError = complexMap }
    }
}