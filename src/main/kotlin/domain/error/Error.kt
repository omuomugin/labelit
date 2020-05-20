package domain.error

sealed class Error {
    object GitHubLabelFetchFailed : Error()
    object GitHubLabelAddFailed : Error()
    object GitHubLabelDeleteFailed : Error()
    object GitHubLabelUpdateFailed : Error()
}